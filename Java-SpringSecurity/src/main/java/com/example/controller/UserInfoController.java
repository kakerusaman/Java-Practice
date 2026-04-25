package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class UserInfoController {
    
    @GetMapping("/userInfo001")
    public String userInfo001(){
        return "userInfo001";
    }
    
}
