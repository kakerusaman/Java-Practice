<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<html>
    <head>
        <title>外部API</title>   
    </head>
    <body>
        <h1>使用したいAPIを選択してください。</h1>
        <button type="button" onclick="location.href='${pageContext.request.contextPath}/weather'">天気API</button>
    </body>
</html>