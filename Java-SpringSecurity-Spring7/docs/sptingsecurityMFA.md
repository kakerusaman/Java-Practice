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
                .requestMatchers("/ott/**").hasAuthority("FACTOR_PASSWORD") // ④
                .anyRequest().authenticated()                               // ⑤
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/complete") // ⑥
            )
            .oneTimeTokenLogin(ott -> ott
                .generationSuccessHandler(ottHandler)
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

**④ `.requestMatchers("/ott/**").hasAuthority("FACTOR_PASSWORD")`**

OTT 入力ページ（`/ott/**`）は `FACTOR_PASSWORD` を持つユーザー、つまり**パスワード認証済みのユーザーのみ**アクセスできます。
未認証のユーザーが直接 OTT ページへアクセスしようとしても弾かれます。

---

**⑤ `.anyRequest().authenticated()`**

上記以外の全リクエストに認証を要求します。
① で指定した `authorities` の全要素（パスワード＋OTT）が揃っていないとアクセスできません。
Spring Security が不足している権限に応じて自動的に適切なページへリダイレクトしてくれます。

---

**⑥ `.defaultSuccessUrl("/complete")`**

パスワード＋OTT の両認証が完了した後の最終的なリダイレクト先を指定します。
ここには OTT 入力ページではなく、最終目的地（今回は完了画面）を指定します。

途中の OTT へのリダイレクトは `@EnableMultiFactorAuthentication` が自動的に処理するため、手動で設定する必要はありません。

```
パスワード認証成功
    ↓
Spring Security が FACTOR_OTT の不足を検知 → 自動で /ott/input へリダイレクト
    ↓
OTT 認証成功
    ↓
defaultSuccessUrl の /complete へリダイレクト  ← ⑥ がここで機能する
```
