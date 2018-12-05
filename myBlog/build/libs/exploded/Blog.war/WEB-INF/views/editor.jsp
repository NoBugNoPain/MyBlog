<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>CKEditor 5 – Classic editor</title>
    <script type="text/javascript" src="\js\wangEditor\release\wangEditor.js"></script>
    <script src="\js\jquery\jquery-3.3.1.js"></script>
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

    //将编辑完的博客以json的格式发送到后台
    document.getElementById('saveBlog').addEventListener('click',function(){
        alert($("#blogTitle").val());
        var data={
            blogName:$("#blogTitle").val(),
            blogContent: editor.txt.html()
        };
        alert(data);
        $.ajax({
            type:"post",
            url:"saveEditor",
            data:JSON.stringify(data),
            dataType: "json",
            contentType:"application/json;charset=UTF-8",
            success:function(){                      //这部分判断json是否回复成功
                alert("成功");
            },
            error:function(){
                alert("失败");
            }
        });
    },false)
</script>
</body>
</html>