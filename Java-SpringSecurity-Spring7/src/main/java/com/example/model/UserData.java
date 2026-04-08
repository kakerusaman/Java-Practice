package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData {
    // ユーザーID
    private String id;
    // ログインID
    private String loginId;
    // パスワード
    private String password;
    // メールアドレス
    private String email;
    // ロール
    private String role;
    // 認証失敗回数
    private int loginFailureCount;
    // アカウント削除フラグ
    private boolean isDeleted;
    // アカウント作成日
    private String createdAt;
    // 最終更新時間
    private String updatedAt;
}
