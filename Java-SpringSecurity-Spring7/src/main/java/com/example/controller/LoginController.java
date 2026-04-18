package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /*
    POSTはSpringSecurityが自動的に行ってくれるため実装なし
    */

    @GetMapping("/")
    public String index() {
        return "redirect:/complete";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/ott/input")
    public String ottInput() {
        return "ottInput";
    }

    @GetMapping("/complete")
    public String complete() {
        return "complete";
    }

}
