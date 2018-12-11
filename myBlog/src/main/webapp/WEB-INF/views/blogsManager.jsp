<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/29
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="\js\jquery\jquery-3.3.1.js"></script>
</head>
<body>
    <c:forEach items="${allBlogs}" var = "blog">
        <div id="${blog.blogId}div">
            ${blog.blogName}
            &nbsp&nbsp<button id="${blog.blogId}" onclick="return clickDeleteButton(this.id)">删除</button>
            &nbsp&nbsp<button id="${blog.blogId}" onclick="window.location.href='/yuanBlog/editor/${blog.blogId}'";>编辑</button>
        </div>
    </c:forEach>

<script>
    function clickDeleteButton(id) {
        //alert(id);
        $.ajax({
            type:"post",
            url:"/delete/"+id,
            contentType:"application/json;charset=UTF-8",
        })
        var thisDiv = document.getElementById(id+"div");
        if(thisDiv!=null){
            thisDiv.parentNode.removeChild(thisDiv);
        }
    }
</script>
</body>
</html>
