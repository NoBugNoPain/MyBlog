<%@ page import="com.yuan.model.Blog" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>CKEditor 5 – Classic editor</title>
    <script type="text/javascript" src="\js\wangEditor\release\wangEditor.js"></script>
    <script src="\js\jquery\jquery-3.3.1.js"></script>
    <%
        Long blogId = 0L;
        String title = null;
        String content = null;
        if(request.getAttribute("editorBlog")!=null){
            blogId = ((Blog)request.getAttribute("editorBlog")).getBlogId();
            title = ((Blog)request.getAttribute("editorBlog")).getBlogName();
            content = ((Blog)request.getAttribute("editorBlog")).getBlogContent();
        }
    %>
</head>
<body>
    <h1>编辑器</h1>
    <label for="blogTitle">博客标题:</label>
    <input type="text" id="blogTitle">
    <div id="editor">
        <p><b>WangEditor</b>富文本编辑器</p>
    </div>
    <button id="saveBlog">保存</button>
<script>
    var E = window.wangEditor
    var editor = new E('#editor')
    editor.create()
    var jsBlogId = '<%=blogId%>';
    if(jsBlogId != 0){
        document.getElementById("blogTitle").value = "<%=((Blog)request.getAttribute("editorBlog")).getBlogName()%>";
        editor.txt.clear();
        editor.txt.html('<%=((Blog)request.getAttribute("editorBlog")).getBlogContent()%>')
    }


    //将编辑完的博客以json的格式发送到后台
    document.getElementById('saveBlog').addEventListener('click',function(){
        //alert($("#blogTitle").val());
        var data={
            blogId:<%=blogId%>,
            blogName:$("#blogTitle").val(),
            blogContent: editor.txt.html(),
        };
        editor.txt.text()
        //alert(data);
        $.ajax({
            type:"post",
            url:"/yuanBlog/saveEditor",
            data:JSON.stringify(data),
            dataType: "json",
            contentType:"application/json;charset=UTF-8",
            success:function(){                      //这部分判断json是否回复成功
                alert("成功");
                window.location.href="/yuanBlog/BlogManager"
            },
            error:function(){
                alert("失败");
            }
        });
    },false)
</script>
</body>
</html>