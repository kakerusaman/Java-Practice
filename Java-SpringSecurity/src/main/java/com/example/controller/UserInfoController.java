package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.form.UserInfo001Form;

@Controller
public class UserInfoController {

    @ModelAttribute
    public UserInfo001Form userInfo001Form(){
        return new UserInfo001Form();
    }

    @GetMapping("/userInfo/userInfo001")
    public String userInfo001(Model model){
        return "userInfo/userInfo001";
    }

    @PostMapping("/api/userInfo001/tempSave")
    @ResponseBody
    public ResponseEntity<Map<String, String>> tempSave(@RequestBody UserInfo001Form form) {
        // TODO: セッションやDBへの一時保存処理をここに実装する
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "一時保存しました");
        return ResponseEntity.ok(response);
    }

}
