package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FortuneTellingController {

    @GetMapping("fortuneTelling")
    public String fortuneTelling() {
        return "fortuneTellling001";
    }
    
    
}
