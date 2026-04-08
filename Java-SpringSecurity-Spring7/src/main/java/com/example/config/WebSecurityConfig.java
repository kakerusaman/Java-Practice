package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManagerFactories;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //設定クラスであることを示す。@Beanで書いたものをspringのコンテナに登録できる
@EnableWebSecurity //springsecurityのWeb向けの機能を有効化する　設定を自動でインポート
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 多要素認証ようのコード
        var mfa = AuthorizationManagerFactories.multiFactor()
        .requireFactors(
            //　今回は多要素認証にパスワードとワンタイムトークンを必要とするようにします。
            FactorGrantedAuthority.PASSWORD_AUTHORITY,
            FactorGrantedAuthority.OTT_AUTHORITY
        )
        .build();
        http
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/createAccount", "/createAccount/confirm", "/createAccount/complete").permitAll();
                auth.requestMatchers("/login").permitAll();
                auth.requestMatchers("/register").permitAll();
                auth.requestMatchers("/public/**").permitAll();
                auth.requestMatchers("/css/**", "/js/**").permitAll();
                auth.requestMatchers("/admin/**").hasRole("ADMIN");
                auth.anyRequest().authenticated();
            })
            // パスワード認証メカニズム
            .formLogin(Customizer.withDefaults())
            // ワンタイムトークン認証のメカニズム
            .oneTimeTokenLogin(Customizer.withDefaults())
            
            .logout(logout -> logout
                .logoutSuccessUrl("/login")
            );
        return http.build();
    }

}
