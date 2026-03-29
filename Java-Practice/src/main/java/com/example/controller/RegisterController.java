package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.UserDataForm;
import com.example.service.UserService;


@Controller
public class RegisterController {

    @Autowired
    UserService userService;
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String postMethodName(UserDataForm form) {
        //登録処理
        userService.insertUser(form.getUserId(), form.getPassword());
        
        
        
        return "registerComplete";
    }
    
    
}
