package com.example.controller;

import java.nio.ReadOnlyBufferException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String login(Model model){
        UserDataForm userDataForm = new UserDataForm();
        model.addAttribute("userDataForm", userDataForm);
        return "login";
    }

    // @PostMapping("/login")
    // public String login(@Validated UserDataForm form, BindingResult result, HttpSession session) {
    //     //エラーだったらログイン画面に遷移
    //     if (result.hasErrors()){
    //         return "login";
    //     }

    //         // ユーザーデータを取得する
    //         UserData userdata = userService.findUser(form.getUserId());

    //     if (userdata != null){
    //         System.out.println("syoriseikou");
    //     } else {
            
    //     }
        
    //     return "redirect:/mypage";
    // }
    
}
