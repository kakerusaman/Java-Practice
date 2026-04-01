package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.form.*;


@Controller
public class CreateAccountController {
    
    // これをする(@ModelAttribute)とModelに設定されるらしい
    @ModelAttribute
    public UserForm userForm(){
        return new UserForm();
    }

    @GetMapping("/createAccount")
    public String createAccount(Model model){

        return "createAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(@RequestBody String entity) {
        //TODO: process POST request
        
        return "hoge";
    }
    
}