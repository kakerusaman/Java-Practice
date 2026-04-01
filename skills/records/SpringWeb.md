# SpringWeb

## 概要

`@ResponseBody` アノテーションを使用することでREST APIの場合にJSONでデータを返すことができる。

---

## 学習日

2026-04-01

---

## 環境構築方法/設定方法

なし

---

## 学んだこと

- REST APIの場合に `@ResponseBody` を使用する
- `@ResponseBody` を付けることでオブジェクトをJSONに変換してレスポンスとして返せる

---

## つまずいたポイント

### 問題

なし

### 原因

なし

### 解決方法

なし

---

## サンプルコード

```java
@PostMapping("/createAccount")
public String createAccount(@RequestBody String entity) {
    //TODO: process POST request

    return "hoge";
}
```

---

## 参考資料

- [公式ドキュメント（Spring MVC）](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/responsebody.html)
- [Javadoc API リファレンス](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseBody.html)
