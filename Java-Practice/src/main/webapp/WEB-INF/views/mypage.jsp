<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<html>
    <head>
        <title>マイページ</title>   
    </head>
    <body>
        <button type="button" onclick="location.href='${pageContext.request.contextPath}/external'">外部API</button>
        <button type="button" onclick="location.href='${pageContext.request.contextPath}/fortuneTelling'">占い遊び</button>
        <button type="button" onclick="location.href='${pageContext.request.contextPath}/external'">何かやりたいことあれば</button>
    </body>
</html>