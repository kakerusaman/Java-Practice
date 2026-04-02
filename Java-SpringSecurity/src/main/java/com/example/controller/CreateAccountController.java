package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.form.*;

import jakarta.servlet.http.HttpSession;


@Controller
public class CreateAccountController {
    
    // これをする(@ModelAttribute)とModelに設定されるらしい
    @ModelAttribute
    public UserForm userForm(){
        return new UserForm();
    }

    // @GetMapping(value = "/createAccount", params = "test")
    // 上記のような書き方もできる valueはパスを設定していてparamsはもし同じURLでも処理を分けたい場合に可能。
    @GetMapping("/createAccount")
    public String createAccount(Model model){
        return "createAccount";
    }

    /*
    ModelAttribute  フォームからの入力値をバインド

    BindingResult バリデーション結果

    HttpSession セッション操作が必要な場合に必要
    ⭐️セッション管理のよくある使用例⭐️
    1.登録完了後に自動ログインさせたい
    2.入力内容を複数のページに渡って保持したい
    3.登録完了メッセージを一度だけ表示したい
    */
    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute UserForm userForm, BindingResult result, HttpSession session) {

        // エラーがある場合はif文の中に入る
        if (result.hasErrors()){
            return "redirect: createAccount";
        }
        
        return "hoge";
    }
    
}