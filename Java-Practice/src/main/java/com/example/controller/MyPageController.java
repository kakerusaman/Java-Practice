package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MyPageController {
    
    @GetMapping("/mypage")
    public String myPage(){
        return "mypage";
    }
    
}
