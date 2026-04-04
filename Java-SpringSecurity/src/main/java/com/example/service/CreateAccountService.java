package com.example.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.common.Constants;
import com.example.form.UserForm;
import com.example.mapper.CreateAccountMapper;
import com.example.model.UserData;

@Service
public class CreateAccountService {

    @Autowired
    CreateAccountMapper createAccountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // 登録処理前のログインIDとメールアドレスの存在チェック
    public String existsLginIdorEmail(UserForm userForm){

        // ログインIDがすでに存在しているかチェックを行う
        UserData user = createAccountMapper.findLoginId(userForm.getLoginId());
        if (user != null){
            // 存在していた場合ログインIDの文字列を返す
            return Constants.LOGIN_ID;
        }
        // メールアドレスがすでに存在しているかチェックを行う
        user = createAccountMapper.findUserEmail(userForm.getEmail());
        if (user != null){
            //　存在していた場合にemailの文字列を返す
            return Constants.EMAIL;
        }
        // 存在していなかった場合は。。。
        return "OK";
    }

    // 登録処理を行う
    public void register(UserForm userForm){

        // 今日の日付でプレフィックス生成（例: 20260404）
        String prefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 当日の最大連番を取得
        Integer maxSeq = createAccountMapper.findMaxSequenceByDate(prefix);

        // 次の連番を計算（初日は001）
        int nextSeq = (maxSeq == null ? 0 : maxSeq) + 1;

        // 1日で作成できる最大のユーザー数が999のため
        if (nextSeq > 999) {
            throw new RuntimeException("本日の登録上限（999件）に達しました");
        }

        userForm.setId(prefix + String.format("%03d", nextSeq));
        
        // passwordをハッシュ化する
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));

        createAccountMapper.insertUser(userForm);
    }
}

