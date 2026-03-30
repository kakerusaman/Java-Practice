package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    /*
    セキュリティ選択画面に遷移
    セッションに再認証フラグがなければログイン画面へリダイレクト
    */
    @GetMapping("/security")
    public String security(HttpSession session) {
        if (session.getAttribute("SECURITY_AUTHENTICATED") == null) {
            return "redirect:/security/login";
        }
        return "security/securityMenu";
    }

    /*
    再認証ログイン画面
    */
    @GetMapping("/security/login")
    public String securityLogin() {
        return "security/securityLogin";
    }

    /*
    再認証処理
    */
    @PostMapping("/security/login")
    public String securityLoginPost(
            @RequestParam String userId,
            @RequestParam String password,
            HttpSession session) {

        if (userService.verifyUser(userId, password)) {
            session.setAttribute("SECURITY_AUTHENTICATED", true);
            return "redirect:/security";
        }
        return "redirect:/security/login?error";
    }
}
