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
<sf:form method="POST" commandName="userLogin" action="userRegister">
    <sf:errors path="*" element="div" cssClass="errors" />
    <sf:label path="userName"
              cssErrorClass="error">用户名</sf:label>:
    <sf:input path="userName"/><br/>
    <sf:label path="password"
              cssErrorClass="error">密码</sf:label>:
    <sf:password path="password"/><br/>
    <label for="confirmPassword"
              cssErrorClass="error">确认密码</label>:
    <input type="password" id="confirmPassword"/><br/>
    <input type="button" value="login/user" onclick="ConfirmPassword()">
</sf:form>




<script  language="javascript" type="text/javascript">
    function ConfirmPassword() {
        var firstPassword = document.getElementById("password").value;
        alert(firstPassword);
        if(firstPassword==document.getElementById("confirmPassword").value) {
            document.getElementById("password").value = hex_md5(firstPassword);
            document.getElementById("userLogin").submit();
        }
        else{
            document.getElementById("password").value="";
            document.getElementById("confirmPassword").value="";
            alert("两次密码输入不一致,请重新输入");
            return false;
        }
    }
</script>
</body>
</html>

