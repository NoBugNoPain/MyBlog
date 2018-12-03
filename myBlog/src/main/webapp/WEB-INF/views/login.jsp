<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/28
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录界面</title>
    <script type="text/javascript" src="\js\encrypt\md5.js"></script>
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
        <input type="button" value="login/user" onclick="firstEncrypt()">
    </sf:form>




    <script  language="javascript" type="text/javascript">
        function firstEncrypt() {
            var pw = document.getElementById("password").value;
            document.getElementById("password").value = hex_md5(pw);
            document.getElementById("userLogin").submit();
        }
    </script>
</body>
</html>

