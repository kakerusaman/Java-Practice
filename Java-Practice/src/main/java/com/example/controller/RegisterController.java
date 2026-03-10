package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.form.UserDataForm;


@Controller
public class RegisterController {
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String postMethodName(UserDataForm form) {
        //TODO: process POST request
        
        return "registerComplete";
    }
    
    
}
