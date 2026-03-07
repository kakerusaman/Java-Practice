package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.form.UserDataForm;



@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDataForm form) {
        //エラーだったらログイン画面に遷移
        System.out.println(form.getLoginName());
        System.out.println(form.getPassWord());
        
        return "mypage";
    }
    
}
