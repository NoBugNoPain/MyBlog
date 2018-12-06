<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/26
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List list = (List)request.getAttribute("allBlogs");
%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <div>Hello it's a test!</div>
    <c:forEach items="${allBlogs}" var="blog">
      <div id="${blog.blogId}"><a href="/blog/${blog.blogId}" >${blog.blogName}</a></div>
    </c:forEach>
  </body>
</html>
