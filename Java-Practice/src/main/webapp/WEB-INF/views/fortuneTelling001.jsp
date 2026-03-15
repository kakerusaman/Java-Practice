<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<html>
    <head>
        <title>個人情報入力画面</title>   
    </head>
    <body>
        <h1>個人情報入力画面</h1>
        <p>性</p>
        <form:input type="text" />
        <p>名</p>
        <form:input type="text" />
        <p>住所</p>
        <form:input type="text" />
        <button type="button">住所検索</button>
        <p>電話番号</p>
        <form:input type="text" />
        <form:input type="text" />
        <form:input type="text" />
        <p>生年月日</p>
        <form:input type="text" />
        <p></p>
        <button type="button">次へ</button>
    </body>
</html>