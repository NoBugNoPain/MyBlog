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
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="/css/component.css" />
    <script src="/js/jquery/jquery-3.3.1.js"></script>
</head>

<body oncontextmenu="return false">

    <div class="page-container">
        <h1>Login</h1>
        <sf:form method="POST" commandName="loginUser">
            <sf:errors path="*" element="div" cssClass="errors" />
            <div>
                <sf:input path="userName" placeholder="Username" autocomplete="off"/>
            </div>
            <div>
                <sf:input path="password" type="password" placeholder="Password" oncontextmenu="return false" onpaste="return false" />
            </div>
            <button id="submitButton" type="button">登录</button>
        </sf:form>
        <div class="connect">
            <p>If we can only encounter each other rather than stay with each other,then I wish we had never encountered.</p>
            <p style="margin-top:20px;">如果只是遇见，不能停留，不如不遇见。</p>
        </div>
    </div>
    <div class="alert" style="display:none">
        <h2>消息</h2>
        <div class="alert_con">
            <p id="ts"></p>
            <p style="line-height:70px"><a class="btn">确定</a></p>
        </div>
    </div>
    <div class="container demo-2" style="position: fixed;top:0; z-index:-1;">
        <div class="content">
            <div id="large-header" class="large-header">
                <canvas id="demo-canvas"></canvas>
            </div>
        </div>
    </div>
    <script>
        $(".btn").click(function(){
            is_hide();
        })
        var u = $("input[name=userName]");
        var p = $("input[name=password]");
        $("#submitButton").click(function(){
            if(u.val() == '' || p.val() =='')
            {
                $("#ts").html("用户名或密码不能为空~");
                is_show();
                return false;
            }
            else{
                var reg = /^[0-9A-Za-z]+$/;
                if(!reg.exec(u.val()))
                {
                    $("#ts").html("用户名错误");
                    is_show();
                    return false;
                }
            }
            var pw = document.getElementById("password").value;
            document.getElementById("password").value = hex_md5(pw);
            document.getElementById("loginUser").submit();
        });

        window.onload = function()
        {
            $(".connect p").eq(0).animate({"left":"0%"}, 600);
            $(".connect p").eq(1).animate({"left":"0%"}, 400);
        }
        function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
        function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }
    </script>
    <script src="/js/demo2/rAF.js"></script>
    <script src="/js/demo2/demo-2.js"></script>
</body>
</html>
