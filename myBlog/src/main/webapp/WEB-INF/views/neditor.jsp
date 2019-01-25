<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/1/24
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台编辑</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="/js/neditor/neditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/neditor/neditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/js/neditor/neditor.service.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/ckeditor/ckeditor.js"></script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/js/neditor/i18n/zh-cn/zh-cn.js"></script>

    <script type="text/javascript" src="/js/neditor/third-party/browser-md5-file.min.js"></script>
    <script type="text/javascript" src="/js/neditor/third-party/jquery-1.10.2.min.js"></script>
    <style type="text/css">
        div{
            width:100%;
        }
    </style>
</head>
<body>
    <h1>博客编辑</h1>
    <div id="ckeditor">
        <p>标题编写</p>
    </div>
    <div>
        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
    </div>
<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');

    InlineEditor
        .create( document.querySelector( '#ckeditor' ) )
        .catch( error => {
            console.error( error );
        } );
    function Markdown( editor ) {
        editor.data.processor = new GFMDataProcessor();
    }
</script>
</body>
</html>