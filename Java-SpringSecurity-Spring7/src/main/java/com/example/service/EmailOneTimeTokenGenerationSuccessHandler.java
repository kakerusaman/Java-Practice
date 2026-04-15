package com.example.service;

import java.io.IOException;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Service;

import com.example.mapper.LoginUserMapper;
import com.example.model.UserData;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmailOneTimeTokenGenerationSuccessHandler
        implements OneTimeTokenGenerationSuccessHandler {

    private final MailSender mailSender;
    private final LoginUserMapper loginUserMapper;
    private final OneTimeTokenGenerationSuccessHandler redirectHandler
            = new RedirectOneTimeTokenGenerationSuccessHandler("/ott/input");

    public EmailOneTimeTokenGenerationSuccessHandler(MailSender mailSender,
                                                     LoginUserMapper loginUserMapper) {
        this.mailSender = mailSender;
        this.loginUserMapper = loginUserMapper;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       OneTimeToken token) throws IOException, ServletException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(resolveEmailAddress(request));
        mail.setSubject("ワンタイムトークン");
        mail.setText("トークン: " + token.getTokenValue());
        mailSender.send(mail);

        redirectHandler.handle(request, response, token);
    }

    private String resolveEmailAddress(HttpServletRequest request) {
        // OTT生成時はパスワード認証前のためSecurityContextには情報がない
        // フォームのusernameパラメータからDBを検索してメールアドレスを取得する
        String username = request.getParameter("username");
        UserData user = loginUserMapper.findByLoginName(username);
        return user.getEmail();
    }
}
