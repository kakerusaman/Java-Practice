<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
    <head>
        <title>Security認証</title>
    </head>
    <body>
        <h1>多要素認証用のアカウントを作成してください</h1>
        <c:if test="${param.error != null}">
            <p style="color:red;">IDまたはパスワードが正しくありません。</p>
        </c:if>
        <form:form action="${pageContext.request.contextPath}/security/login" method="POST">
            <div>
                <label>ログインID</label>
                <form:input type="text" name="loginId" />
            </div>
            <div>
                <label>パスワード</label>
                <form:input type="password" name="password" />
            </div>
            <div>
                <label>メールアドレス</label>
                <form:input type="text" path="e-mail" />
            </div>
            <div>
                <lavel>ロール</lavel>
            </div>
            <button type="submit">登録</button>
        </form:form>
    </body>
</html>
