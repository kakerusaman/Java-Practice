<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント作成</title>
</head>
<body>
    <h1>アカウントを作成してください</h1>
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
                <lavel>プレミアム会員選択</lavel>
                <%-- nameをラジオボタンで統一することによって一意に選択可能 --%>
                <label>プレミアム会員で登録する方</label>
                <form:input type="radio" name="role" value="premium" />
                <label>一般会員で登録する方</label>
                <form:input type="radio" name="role" value="general"/>
                <label>簡易ログインの方</label>
                <form:input type="radio" name="role" value="guest" />
            </div>
            <button type="submit">登録</button>
        </form:form>
</body>
</html>