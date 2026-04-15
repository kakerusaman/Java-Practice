package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;

import com.example.service.EmailOneTimeTokenGenerationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMultiFactorAuthentication(authorities = {
    FactorGrantedAuthority.PASSWORD_AUTHORITY, // ①
    FactorGrantedAuthority.OTT_AUTHORITY
})
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
                .tokenGenerationSuccessHandler(ottHandler)
                .loginPage("/ott/input")      // FACTOR_OTT不足時のリダイレクト先（カスタム画面）
                .showDefaultSubmitPage(false) // Spring Securityのデフォルト画面を無効化
                .defaultSuccessUrl("/complete")
            );
        return http.build();
    }
}
