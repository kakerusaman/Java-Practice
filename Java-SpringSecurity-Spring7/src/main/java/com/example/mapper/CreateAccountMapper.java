package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.form.UserForm;
import com.example.model.UserData;

@Mapper
public interface CreateAccountMapper {

    // ログインIDの存在チェック
    UserData findLoginId(@Param("loginId") String loginId);

    // メールアドレスの存在チェック
    UserData findUserEmail(@Param("email") String email);

    // 当日の連番チェック
    Integer findMaxSequenceByDate(@Param("prefix") String prefix);

    // アカウント登録
    void insertUser(@Param("form") UserForm userForm);
    
}
