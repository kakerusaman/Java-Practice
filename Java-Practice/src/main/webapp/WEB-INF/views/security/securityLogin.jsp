<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
    <head>
        <title>Security認証</title>
    </head>
    <body>
        <h1>再認証</h1>
        <p>このページにアクセスするには再度認証が必要です。</p>
        <c:if test="${param.error != null}">
            <p style="color:red;">IDまたはパスワードが正しくありません。</p>
        </c:if>
        <form:form action="${pageContext.request.contextPath}/security/login" method="POST">
            <div>
                <label>ユーザーID</label>
                <input type="text" name="userId" />
            </div>
            <div>
                <label>パスワード</label>
                <input type="password" name="password" />
            </div>
            <button type="submit">認証</button>
        </form:form>
    </body>
</html>
