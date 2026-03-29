package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SecurityController {
    
    /*
    セキュリティ選択画面に遷移
    */
    @GetMapping("security")
    public String security(@RequestParam String param) {
        return "security/securityMenu";
    }
    
}
