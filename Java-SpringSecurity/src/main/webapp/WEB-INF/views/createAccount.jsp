<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント作成</title>
<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<!-- 自分のJSファイル（jQueryより後に書く） -->
<script src="<c:url value='/js/createaccount.js'/>"></script>
</head>
<body>
    <h1>アカウントを作成してください</h1>
        <c:if test="${param.error != null}">
            <p style="color:red;">IDまたはパスワードが正しくありません。</p>
        </c:if>
        <form:form action="${pageContext.request.contextPath}/createAccount" method="POST" modelAttribute="userForm">
            <div>
                <h1>ログインID</h1>
                <form:input type="text" path="loginId" />
                <form:errors path="loginId" />
            </div>
            <div>
                <h1>パスワード</h1>
                <form:input type="password" path="password" />
                <form:errors path="password" />
            </div>
            <div>
                <h1>メールアドレス</h1>
                <form:input type="text" path="email" />
                <form:errors path="email" />
            </div>
            <div>
                <h1>会員選択</h1>
                <%-- pathをラジオボタンで統一することによって一意に選択可能 --%>
                <h3>プレミアム会員で登録する方</h3>
                <form:radiobutton path="role" value="premium" />
                <h3>一般会員で登録する方</h3>
                <form:radiobutton path="role" value="general" />
                <h3>簡易ログインの方</h3>
                <form:radiobutton path="role" value="guest" />
                <form:errors path="role" />
            </div>
            <button type="submit">登録</button>
        </form:form>
</body>
</html>