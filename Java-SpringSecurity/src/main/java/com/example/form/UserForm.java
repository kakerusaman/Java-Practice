package com.example.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {

    // ユーザーID
    private String id;

    // 画面から入力してもらう
    @NotNull
    private String loginId;

    //画面から入力してもらう
    @NotNull
    private String password;

    //画面から入力してもらう
    @NotNull
    private String email;

    //画面から入力してもらう
    @NotNull
    private String role;

    // ログインを間違えた回数
    private int loginFailureCount;

    // 削除フラグ
    private boolean isDeleted;

    // アカウント作成時間
    private LocalDateTime createdAt;

    // 最終更新時間
    private LocalDateTime updatedAt;

}
