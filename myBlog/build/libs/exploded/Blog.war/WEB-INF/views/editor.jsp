<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>CKEditor 5 – Classic editor</title>
    <script type="text/javascript" src="\js\wangEditor\release\wangEditor.js"></script>
</head>
<body>
    <h1>编辑器</h1>
    <div id="editor">
        <p><b>WangEditor</b>富文本编辑器</p>
    </div>
<script>
    var E = window.wangEditor
    var editor = new E('#editor')
    editor.create()
</script>
</body>
</html>