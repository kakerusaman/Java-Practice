package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.form.UserDataForm;


@Controller
public class RegisterController {
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String postMethodName(UserDataForm form) {
        //登録処理
        
        return "registerComplete";
    }
    
    
}
