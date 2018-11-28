<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/28
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
    <sf:form method="POST" commandName="userLogin">
        用户名:<sf:input path="userName"/><br/>
        密码:<sf:input path="passWord" /><br/>
        <input type="submit" value="login/user">
    </sf:form>
</body>
</html>
