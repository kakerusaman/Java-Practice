package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMultiFactorAuthentication(authorities = {
    FactorGrantedAuthority.PASSWORD_AUTHORITY,
    FactorGrantedAuthority.OTT_AUTHORITY
})
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/login").permitAll()
                // パスワード認証済みのみ OTT 入力ページへアクセス可能
                .requestMatchers("/ott/**").hasAuthority("FACTOR_PASSWORD")
                // その他はパスワード＋OTT 両方を要求（@EnableMultiFactorAuthentication が自動適用）
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .oneTimeTokenLogin(ott -> ott
                .generationSuccessHandler((request, response, oneTimeToken) -> {
                    // 生成されたトークンをセッションに保存して画面に表示する
                    request.getSession().setAttribute("ott_token", oneTimeToken.getTokenValue());
                    response.sendRedirect(request.getContextPath() + "/ott/input");
                })
            );

        return http.build();
    }

}
