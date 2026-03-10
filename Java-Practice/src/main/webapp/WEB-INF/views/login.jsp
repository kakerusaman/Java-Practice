<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="/Java-Practice/css/style.css">

<html>
    <head>
        <title>ログイン</title>
    </head>
    <body>
        <h1>ログイン</h1>
        <p>こちらのアプリを使用するには<br>
           アプリアカウントにログインしてください。</p>
        <form action="login" method="POST">
            <input class="form" type="text" name="userId">
            <input type="password" name="password">
            <p>パスワードをお忘れですか？</p>
            <button type="submit">ログイン</button>
        </form>
        <p>アカウントが未登録ですか？</p>
        <a href="${pageContext.request.contextPath}/register">アカウント作成</p>
    </body>







</html>