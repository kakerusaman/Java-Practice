package com.example.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {

    // ユーザーID
    private String id;

    // 画面から入力してもらう
    @NotBlank
    @Size(min = 5, max = 20)
    private String loginId;

    //画面から入力してもらう
    @NotBlank
    // パスワードは強固なものにしたいけどまだ検証段階なので入力が面倒passayというJavaのライブラリがいい感じみたい
    private String password;

    //画面から入力してもらう
    @NotBlank
    // こちらも正規表現で行いたいけど検証が面倒になるため放置s
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
