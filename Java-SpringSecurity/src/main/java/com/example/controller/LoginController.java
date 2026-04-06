package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    
    // ログイン画面に遷移する
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
}
