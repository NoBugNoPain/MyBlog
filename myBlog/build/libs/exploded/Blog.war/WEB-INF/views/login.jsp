<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/28
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录界面</title>
</head>
<body>
    <sf:form method="POST" commandName="userLogin">
        <sf:errors path="*" element="div" cssClass="errors" />
        <sf:label path="userName"
                  cssErrorClass="error">用户名</sf:label>:
        <sf:input path="userName"/><br/>
        <sf:label path="password"
                  cssErrorClass="error">密码</sf:label>:
        <sf:password path="password"/><br/>
        <input type="submit" value="login/user">
    </sf:form>
</body>
</html>
