package com.example.service;

import java.io.IOException;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Service;

import com.example.model.UserData;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmailOneTimeTokenGenerationSuccessHandler
        implements OneTimeTokenGenerationSuccessHandler {
    
    private final MailSender mailSender;
    private final OneTimeTokenGenerationSuccessHandler redirectHandler
            = new RedirectOneTimeTokenGenerationSuccessHandler("/ott/sent");

    public EmailOneTimeTokenGenerationSuccessHandler(MailSender mailSender){
        this.mailSender = mailSender;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       OneTimeToken token) throws IOException, ServletException {

                                // トークンをメールで送信
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(resolveEmailAddress(request)); // ユーザーのメアドを取得
        mail.setSubject("ワンタイムトークン");
        mail.setText("トークン: " + token.getTokenValue());
        mailSender.send(mail);

        // 送信完了ページへリダイレクト
        redirectHandler.handle(request, response, token);
        }

     private String resolveEmailAddress(HttpServletRequest request) {
      // SecurityContext から認証済みユーザーを取得                                                                                                                                                                        
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();                                                                                                                                        
      // UserDetails からメールアドレスを取得                                                                                                                                                               
      UserData user = (UserData) auth.getPrincipal();                                                                                                                                                                      
      return user.getEmail();                                                                                                                                                                                              
  }      
}