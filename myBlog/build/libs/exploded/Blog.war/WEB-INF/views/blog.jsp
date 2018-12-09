<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/29
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <h1>${Blog.blogName}</h1>
    <div>${Blog.blogContent}</div>
    <c:set value="${Blog.comment}" var="commend" />
    <c:forEach items="${commend}" var="eachComment">
        <div id="comment${eachComment.blogId}">${eachComment.blogComment}</div>
    </c:forEach>
</head>
<body>

</body>
</html>
