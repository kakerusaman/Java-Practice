package com.example.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {

    // 画面から入力してもらう
    @NotNull
    private String userId;

    //画面から入力してもらう
    @NotNull
    private String password;

    //画面から入力してもらう
    @NotNull
    private String email;

    //画面から入力してもらう
    @NotNull
    private String role;

}
