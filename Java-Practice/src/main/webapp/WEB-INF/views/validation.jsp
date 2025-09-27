<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
<form:form modelAttribute="validationForm" method="post">
    <form:radiobutton path="radio" value="1" />持ってる
    <form:radiobutton path="radio" value="2" />持ってない
    <br>
    <form:errors path="radio" />
    <br>
    <input type="submit" value="送信">
</form:form>
</body>
</html>
