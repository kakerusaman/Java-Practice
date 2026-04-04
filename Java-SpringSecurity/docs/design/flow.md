# プロジェクトフロー図

## 画面遷移フロー

```mermaid
flowchart TD
    START([開始]) --> LOGIN_PAGE["GET /login\nlogin.jsp"]
    START --> REGISTER_PAGE["GET /register\nregister.jsp"]

    REGISTER_PAGE -->|POST| INSERT["UserService.insertUser\nDBにユーザー登録"]
    INSERT --> REGISTER_COMPLETE["registerComplete.jsp"]

    LOGIN_PAGE -->|"POST\nSpring Security処理"| AUTH{認証}
    AUTH -->|失敗| LOGIN_PAGE
    AUTH -->|成功| MYPAGE["GET /mypage\nmypage.jsp"]

    MYPAGE -->|外部API| EXTERNAL["GET /external\n未実装"]
    MYPAGE -->|占い遊び| FORTUNE["GET /fortuneTelling\nfortuneTelling001.jsp"]
    MYPAGE -->|何かやりたいことあれば| EXTERNAL
    MYPAGE -->|Spring Security系| SECURITY["GET /security\nsecurityMenu.jsp"]

    SECURITY --> MFA["GET /security/mfa\n多要素認証"]
    SECURITY --> PERSISTENCE["GET /security/persistence\n永続化"]
    SECURITY --> PASSKEY["GET /security/passkey\nパスキー"]
    SECURITY --> OTP["GET /security/otp\nワンタイムトークン"]
    SECURITY --> SESSION["GET /security/session\nセッション管理"]
    SECURITY --> REMEMBER["GET /security/remember-me\n自分を記憶"]
    SECURITY --> ANONYMOUS["GET /security/anonymous\n匿名"]
    SECURITY --> PREAUTH["GET /security/pre-auth\n事前認証"]

    style EXTERNAL fill:#f99,stroke:#f00
    style MFA fill:#f99,stroke:#f00
    style PERSISTENCE fill:#f99,stroke:#f00
    style PASSKEY fill:#f99,stroke:#f00
    style OTP fill:#f99,stroke:#f00
    style SESSION fill:#f99,stroke:#f00
    style REMEMBER fill:#f99,stroke:#f00
    style ANONYMOUS fill:#f99,stroke:#f00
    style PREAUTH fill:#f99,stroke:#f00
```

> 赤色：未実装

---

## 未実装・未完成一覧

| 問題 | 場所 |
|------|------|
| `return new String()` で画面なし | ExternalController |
| JSP名typo `fortuneTellling001`（lが3つ） | FortuneTellingController |
| `@RequestParam String param` が必須になっているのにmypage.jspから渡していない | SecurityController |
| `/security/**` 各画面未作成 | security/配下のJSP |

---

## コントローラー対応表

| URL | メソッド | コントローラー | 遷移先 |
|-----|---------|--------------|--------|
| /login | GET | LoginController | login.jsp |
| /login | POST | Spring Security | /mypage |
| /register | GET | RegisterController | register.jsp |
| /register | POST | RegisterController | registerComplete.jsp |
| /mypage | GET | MyPageController | mypage.jsp |
| /security | GET | SecurityController | security/securityMenu.jsp |
| /external | GET | ExternalController | 未実装 |
| /fortuneTelling | GET | FortuneTellingController | fortuneTelling001.jsp |
| /weather | GET | ExternalController | 未実装 |
