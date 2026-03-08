package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.form.UserDataForm;
import com.example.service.UserService;
import com.example.model.UserData;

import jakarta.servlet.http.HttpSession;



@Controller
public class LoginController {

    @Autowired
    UserService userService;
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDataForm form, HttpSession session) {
        //エラーだったらログイン画面に遷移

        UserData userdata = userService.checkLoginData(form.getUserId(),form.getPassword());
        List<UserData> userfindAllData = userService.findAll();
        if (userfindAllData != null){
            System.out.println("konnnitiha");
        }
        if (userdata != null){
            System.out.println("syoriseikou");
        } else {
            System.out.println("kubiokasiihito");
        }
        
        return "redirect:/mypage";
    }
    
}
