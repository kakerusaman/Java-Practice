package com.example.controller;

import java.nio.ReadOnlyBufferException;
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

        UserData userdata = userService.findUser(form.getUserId());

        if (userdata != null){
            System.out.println("syoriseikou");
        } else {
            throw new ReadOnlyBufferException();
        }
        
        return "redirect:/mypage";
    }
    
}
