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

