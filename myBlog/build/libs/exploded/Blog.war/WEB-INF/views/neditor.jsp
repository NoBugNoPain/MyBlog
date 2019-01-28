<%@ page import="com.yuan.model.Blog" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/1/24
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  Blog blog = (Blog)request.getAttribute("editorBlog");
    Long blogId = blog==null?0l:blog.getBlogId();
%>
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
    <br>
    <br>
    <div id="ckeditor">
        <h1>标题编写</h1>
    </div>
    <div>
        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
    </div>
    <button id="saveBlog">保存博客</button>
    <button id="editorBlogData">编辑博客</button>
<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    //alert(typeof ue);
    let editor;
    InlineEditor
        .create( document.querySelector( '#ckeditor' ) )
        .then( newEditor => {
            editor = newEditor;
        } )
        .catch( error => {
            console.error( error );
        } );
    function Markdown( editor ) {
        editor.data.processor = new GFMDataProcessor();
    }
    <%if((Blog)request.getAttribute("editorBlog")!=null){%>
        <%if(((Blog) request.getAttribute("editorBlog")).getBlogId()!=0l){%>
        ue.ready(function(){
            UE.getEditor('editor').setContent('<%=(((Blog) request.getAttribute("editorBlog")).getBlogContent())%>');
            editor.setData('<%=(((Blog) request.getAttribute("editorBlog")).getBlogName())%>');
        });
    <%}}%>
    document.getElementById('saveBlog').addEventListener('click',function(){
        //alert($("#blogTitle").val());
        var data={
            blogId:<%=blogId%>,
            blogName:editor.getData(),
            blogContent: ue.getContent(),
        };
        //alert(data);
        $.ajax({
            type:"post",
            url:"/saveEditor",
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