<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
    <head>
        <title>登録画面</title>   
    </head>
    <body>
        <h1>アプリ名</h1>
        <form:form action="register" method="POST">
            <input type="text" name="userId" />
            <input type="password" name="password" />
            <button type="submit">登録</button>
        </form:form>
    </body>
</html>