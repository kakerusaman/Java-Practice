# Spring Security 7 で MFA を実装する

Spring Security 7 において MFA（多要素認証）が簡単に実装できるようになったため、忘備録として残しておきます。

---

## 概要

Spring Security 7 では認証の際に、各認証メカニズムが対応する権限を自動的に `Authentication` に追加するようになりました。

:::note warn
**`Authentication` とは？**
誰がログインしていて、どんな権限を持っているかを持ち歩くオブジェクトです。
Spring Security はリクエストごとにこのオブジェクトを参照して、アクセスを許可するかどうかを判断します。
:::

認可ルールにあらかじめ必要な認証要素を記述しておくと、要素権限が不足した際に Spring Security がその権限を取得できるエンドポイントへ自動的にユーザーをリダイレクトしてくれます。これによってリダイレクトロジックを自前で書く必要がなくなりました。

今回はパスワードと OTT（ワンタイムトークン）の2つを必要とする構成にします。
OTT のトークンはメールアドレスに送信する形で実装します。

---

## ⭐️ 実装方法

今回は簡単に実装したいため `@EnableMultiFactorAuthentication` アノテーションを使用します。

### セキュリティ設定クラス

```java
@Configuration
@EnableWebSecurity
@EnableMultiFactorAuthentication(authorities = {
    FactorGrantedAuthority.PASSWORD_AUTHORITY,
    FactorGrantedAuthority.OTT_AUTHORITY
}) // ①
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
            EmailOneTimeTokenGenerationSuccessHandler ottHandler /* ② */) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/login").permitAll() // ③
                .requestMatchers("/ott/input")                              // ④
                    .access((authentication, context) -> new AuthorizationDecision(
                        authentication.get().getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("FACTOR_PASSWORD"))
                    ))
                .anyRequest().authenticated()                               // ⑤
            )
            .formLogin(form -> form
                .loginPage("/login") // ⑥
            )
            .oneTimeTokenLogin(ott -> ott
                .tokenGenerationSuccessHandler(ottHandler)
                .loginPage("/ott/input")           // FACTOR_OTT不足時のリダイレクト先
                .loginProcessingUrl("/login/ott")  // loginPage()設定で上書きされるため明示的に指定
                .showDefaultSubmitPage(false)      // Spring Securityのデフォルト画面を無効化
                .defaultSuccessUrl("/complete")
            );
        return http.build();
    }
}
```

---

### 各ポイントの解説

**① `@EnableMultiFactorAuthentication(authorities = { ... })`**

アプリ全体に要求する MFA の要素を定義します。
ここで指定した権限が揃っていないと、すべての認可ルールへの適用が自動的に行われます。
`PASSWORD_AUTHORITY` と `OTT_AUTHORITY` を指定することで、パスワード＋OTT の両方を要求する構成になります。

| 定数 | 意味 |
| --- | --- |
| `FactorGrantedAuthority.PASSWORD_AUTHORITY` | パスワード認証済みで付与される権限 |
| `FactorGrantedAuthority.OTT_AUTHORITY` | OTT 認証済みで付与される権限 |

---

**② `EmailOneTimeTokenGenerationSuccessHandler ottHandler`**

OTT が生成されたときの処理を担うハンドラーです。
引数として受け取ることで Spring の DI によって自動的に Bean が注入されます。
今回は生成されたトークンをユーザーのメールアドレスに送信する実装にしています。
メール送信後は OTT 入力ページへリダイレクトし、ユーザーがメールで受け取ったトークンを入力する形になります。

---

**③ `.requestMatchers("/css/**", "/js/**", "/login").permitAll()`**

CSS・JS・ログインページは認証なしでアクセスを許可します。
これがないとログインページ自体にアクセスできなくなるため必須です。

---

**④ `.requestMatchers("/ott/input").access(...)`**

OTT 入力ページ（`/ott/input`）は `FACTOR_PASSWORD` を持つユーザー、つまり**パスワード認証済みのユーザーのみ**アクセスできます。
未認証のユーザーが直接 OTT ページへアクセスしようとしても弾かれます。

`.hasAuthority("FACTOR_PASSWORD")` ではなく `.access(...)` を使っている理由は、`@EnableMultiFactorAuthentication` が `.hasAuthority()` に対して自動的に FACTOR_OTT の要件も付け加えてしまうためです。`.access()` にカスタムラムダを渡すと、その自動付与の仕組みが働かず、自分で書いた条件だけで判断してくれます。

---

**⑤ `.anyRequest().authenticated()`**

上記以外の全リクエストに認証を要求します。
① で指定した `authorities` の全要素（パスワード＋OTT）が揃っていないとアクセスできません。
Spring Security が不足している権限に応じて自動的に適切なページへリダイレクトしてくれます。

---

**⑥ `.loginPage("/login")`**

Spring Security のデフォルトのログインページを使わず、自作のログインページを使用することを指定します。
ここで指定した URL に対応するコントローラーと画面（JSP・Thymeleaf など）を自分で用意する必要があります。

---

## ⚠️ 実装時の注意点

### 1. `.loginPage()` を設定すると `loginProcessingUrl` が上書きされる

`oneTimeTokenLogin` で `.loginPage("/ott/input")` を設定すると、内部で `loginProcessingUrl` も `/ott/input` に変わってしまいます。
そのため POST `/login/ott`（トークン送信先）が Spring Security のフィルターに届かず、Spring MVC まで素通りして「No mapping for POST /login/ott」という警告が発生します。

`.loginProcessingUrl("/login/ott")` を明示的に追加することで解決できます。

```java
.oneTimeTokenLogin(ott -> ott
    .loginPage("/ott/input")
    .loginProcessingUrl("/login/ott") // 明示的に指定しないと /ott/input に上書きされる
)
```

---

### 2. `@EnableMultiFactorAuthentication` は `.hasAuthority()` にも MFA 要件を追加する

OTT 入力ページ（`/ott/input`）を `.hasAuthority("FACTOR_PASSWORD")` で守ろうとすると、`@EnableMultiFactorAuthentication` が自動的に FACTOR_OTT の要件も付け加えます。
結果として「FACTOR_OTT を取得するために /ott/input へ行きたいが、FACTOR_OTT がないと /ott/input に入れない」という無限リダイレクトループが発生します。

`.access()` にカスタムラムダを渡すと自動付与の仕組みが働かないため、FACTOR_PASSWORD だけで判断させることができます。

```java
// ❌ これだと FACTOR_OTT も自動的に要求されてしまいループする
.requestMatchers("/ott/input").hasAuthority("FACTOR_PASSWORD")

// ✅ .access() を使うと自分で書いた条件だけで判断してくれる
.requestMatchers("/ott/input")
    .access((authentication, context) -> new AuthorizationDecision(
        authentication.get().getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("FACTOR_PASSWORD"))
    ))
```

---

## まとめ

今回は Spring Security 7 での多要素認証を実装してみました。以前より楽に実装できるようになったようですが、今までの多要素認証を行ったことがないため、次回はどのくらい楽に実装できるようになったかを記事にできたらと思います。
