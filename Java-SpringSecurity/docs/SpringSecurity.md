# Spring Security

## 概要

Spring Securityは、Springベースのアプリケーションに認証・認可機能を提供するフレームワーク。

---

## Spring Securityのセットアップ方法

ステップ１
依存関係を追加する。
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>6.3.4</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>6.3.4</version>
</dependency>


WebsecurityConfigに以下のものを記述する
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
		return manager;
	}
}


## 多要素認証

仕組み：FactorGrantedAuthority
認証時に、Spring Securityの認証メカニズムが FactorGrantedAuthority を Authentication に自動追加します。例えばパスワードで認証すると FactorGrantedAuthority.PASSWORD_AUTHORITY が付与されます。 Spring
MFAを要求するには、次の2つが必要です：

複数要素を要求する認可ルールを定義する
各要素に対応する認証メカニズムを設定する

方法1: @EnableMultiFactorAuthentication（最も簡単）
アプリケーション全体にMFAを適用する場合：
java@EnableMultiFactorAuthentication(authorities = {
    FactorGrantedAuthority.PASSWORD_AUTHORITY,
    FactorGrantedAuthority.OTT_AUTHORITY
})
これにより、すべての認可ルールにパスワード＋ワンタイムトークン（OTT）の両方が自動要求されます。SecurityFilterChain側では通常通り書くだけです：
java@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults())
        .oneTimeTokenLogin(Customizer.withDefaults());
    return http.build();
}
Spring Securityはどの権限が欠けているかに応じて適切なエンドポイントにリダイレクトします。パスワードでログインした場合はOTTログインページへ、トークンでログインした場合はパスワードログインページへ自動遷移します。 Spring
方法2: AuthorizationManagerFactory（部分的にMFA適用）
一部のURLだけMFAを要求したい場合は、@EnableMultiFactorAuthentication(authorities = {}) で空にしておき、AuthorizationManagerFactory のインスタンスをBeanではなくローカル変数として作成し、選択的に適用します：
java@EnableMultiFactorAuthentication(authorities = {})
// ...
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    var mfa = AuthorizationManagerFactories.multiFactor()
        .requireFactors(
            FactorGrantedAuthority.PASSWORD_AUTHORITY,
            FactorGrantedAuthority.OTT_AUTHORITY
        )
        .build();
    http
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/admin/**").access(mfa.hasRole("ADMIN"))       // MFA必要
            .requestMatchers("/user/settings/**").access(mfa.authenticated()) // MFA必要
            .anyRequest().authenticated()                                     // MFA不要
        )
        .formLogin(Customizer.withDefaults())
        .oneTimeTokenLogin(Customizer.withDefaults());
    return http.build();
}
方法3: 有効期限の指定
認証からの経過時間に基づいてアクセス制御もできます：
javavar passwordIn30m = AuthorizationManagerFactories.multiFactor()
    .requireFactor(factor -> factor
        .passwordAuthority()
        .validDuration(Duration.ofMinutes(30)))
    .build();

var passwordInHour = AuthorizationManagerFactories.multiFactor()
    .requireFactor(factor -> factor
        .passwordAuthority()
        .validDuration(Duration.ofHours(1)))
    .build();

http.authorizeHttpRequests((authorize) -> authorize
    .requestMatchers("/admin/**").access(passwordIn30m.hasRole("ADMIN"))
    .requestMatchers("/user/settings/**").access(passwordInHour.authenticated())
    .anyRequest().authenticated()
);
方法4: ユーザーごとに動的にMFA適用（プログラマティックMFA）
特定ユーザーだけMFAを要求する場合、カスタム AuthorizationManager を作成します：
java@Component
class AdminMfaAuthorizationManager implements AuthorizationManager<Object> {
    @Override
    public AuthorizationResult authorize(Supplier<? extends Authentication> authentication, Object context) {
        if ("admin".equals(authentication.get().getName())) {
            return AllAuthoritiesAuthorizationManager.hasAllAuthorities(
                FactorGrantedAuthority.OTT_AUTHORITY,
                FactorGrantedAuthority.PASSWORD_AUTHORITY
            ).authorize(authentication, context);
        }
        return new AuthorizationDecision(true);
    }
}
これを DefaultAuthorizationManagerFactory に setAdditionalAuthorization で設定しBeanとして公開します。
方法5: RequiredAuthoritiesAuthorizationManager（組み込みサポート）
方法4と同じことをSpring Security組み込みの仕組みで実現できます：
java@Bean
RequiredAuthoritiesAuthorizationManager<Object> adminAuthorization() {
    MapRequiredAuthoritiesRepository authorities = new MapRequiredAuthoritiesRepository();
    authorities.saveRequiredAuthorities("admin", List.of(
        FactorGrantedAuthority.PASSWORD_AUTHORITY,
        FactorGrantedAuthority.OTT_AUTHORITY
    ));
    return new RequiredAuthoritiesAuthorizationManager<>(authorities);
}
動的なケースでは RequiredAuthoritiesRepository のカスタム実装を作り、DBからMFA有効/無効を判定することも可能です。
方法6: シンプルに hasAllAuthorities（最小構成）
最もシンプルな方法として hasAllAuthorities で複数要素を直接指定できます：
javahttp.authorizeHttpRequests((authorize) -> authorize
    .anyRequest().hasAllAuthorities(
        FactorGrantedAuthority.PASSWORD_AUTHORITY,
        FactorGrantedAuthority.OTT_AUTHORITY
    )
)
.formLogin(Customizer.withDefaults())
.oneTimeTokenLogin(Customizer.withDefaults());
ただしルールが多くなると重複が増えるため、その場合は @EnableMultiFactorAuthentication が推奨されます。