<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="com.yuan.model.Blog" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/26
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="/css/blogHomePage.css" />
  </head>
  <body>
    <div id="header">
      <h1>袁鹏的博客</h1>
      <div style="float:right; margin-right:20px;">
        <input  id="searchInput">
        <button id="search">搜索</button>
      </div>
    </div>
    <aside id="sideBar">
      <div id="headImage">
        <a href="#"><img src="/image/headImage.gif" alt="有趣"/></a>
      </div>
      <h3 style="text-align:center;">嗨呀！是真滴抽象</h3>
    </aside>
    <div class="container">
        <c:forEach items="${allBlogs}" var="blog">
          <div class="blogContent">
            <h2 class="blogName"><a href="/blog/${blog.blogId}">${blog.blogName}</a></h2>
            <p id="${blog.blogId}"></p>
          </div>
        </c:forEach>
    </div>
    </div>
  </body>
  <script type="text/javascript">
      window.onload=function(){
      <%
      List list = (List)request.getAttribute("allBlogs");
      if(list.size()>0) {
        for (Object blog : list) {
            if(blog instanceof Blog){
      %>
      var blogId = "<%=((Blog)blog).getBlogId()%>";
      var blogContent = '<%=((Blog)blog).getBlogContent()%>';
      //alert(blogContent.replace(/<.*?>/g,""));
      document.getElementById(blogId).innerText = blogContent.replace(/<.*?>/g, function () {
          return '';
      });
      <%

              }
          }
        }
      %>
      }
  </script>
</html>
