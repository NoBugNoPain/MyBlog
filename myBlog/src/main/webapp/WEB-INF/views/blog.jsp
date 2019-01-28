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
    <title>博客</title>
    <link rel="stylesheet" type="text/css" href="/css/blogPage.css" />
    <script type="text/javascript" src="/js/wangEditor/release/wangEditor.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="/js/drawTree/drawTree.js"></script>
</head>
<body>

<div id="header">
    <h1>袁鹏的博客</h1>
</div>
<aside id="sideBar">
    <div id="headImage">
        <a href="/yuanBlog/BlogManager"><img src="/image/headImage.gif" alt="有趣"/></a>
    </div>
    <h3 style="text-align:center;">嗨呀!是真滴抽象</h3>
</aside>
<div class="container">
    <div class="blogContent">
        <h1 class="blogName">${Blog.blogName}</h1>
        <p>${Blog.blogContent}</p>
    </div>
    <div id="blogComments">
        <c:set value="${Blog.comment}" var="commend" />
        <c:forEach items="${commend}" var="eachComment">
            <div class="blogComment" id="comment${eachComment.blogId}">${eachComment.blogComment}</div>
        </c:forEach>
        <div id="divLine"style="border-top:1px dashed black; margin-bottom:10px;"></div>
        <div id="menu" class="toolbar"></div>
        <div id="editor" class="text"></div>
        <button id="editorButton" onclick="increaseComment()">评论</button>
    </div>
</div>
<div id="backgroundImage"></div>
<script type="text/javascript">
    $('#backgroundImage').drawTree();
    var E = window.wangEditor
    var editor = new E('#menu','#editor')
    editor.customConfig.menus = [
        'link',
        'emoticon',
        'undo'
    ]
    editor.create();
    function increaseComment(){
        if(editor.txt.text()==""||editor.txt.text().length>150){
            alert("请输入评论再提交,或评论字数不允许超过150字");
        }
        else{
            var editorDiv = document.getElementById("divLine");
            var position = editorDiv.previousSibling;
            var newDiv = document.createElement('div');
            newDiv.innerHTML = '<div class="blogComment"><p>'+editor.txt.text()+'</p></div>';
            editor.txt.clear();
            insertAfter(newDiv,position);
            newDiv = editorDiv.previousSibling;
        }
    }

    function insertAfter(newEl, targetEl)
    {
        var parentEl = targetEl.parentNode;

        if(parentEl.lastChild == targetEl)
        {
            parentEl.appendChild(newEl);
        }else
        {
            parentEl.insertBefore(newEl,targetEl.nextSibling);
        }
    }
</script>

</body>
</html>
