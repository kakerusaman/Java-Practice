<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<html>
    <head>
        <title>登録画面</title>   
    </head>
    <body>
        <h1>アプリ名</h1>
        <form action="register" method="POST">
            <input class="form" type="text" name="userId">
            <input type="password" name="password">
            <button type="submit">登録</button>
        </form>
    </body>
</html>