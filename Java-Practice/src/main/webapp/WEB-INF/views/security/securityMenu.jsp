<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<html>
    <head>
        <title>Spring Security選択画面</title>   
    </head>
    <body>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/mfa'">多要素認証</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/persistence'">永続化</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/passkey'">パスキー</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/otp'">ワンタイムトークン</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/session'">セッション管理</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/remember-me'">自分を記憶</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/anonymous'">匿名</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/pre-auth'">事前認証</button>
    </body>
</html>