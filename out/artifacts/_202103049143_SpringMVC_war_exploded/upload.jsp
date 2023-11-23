<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/11/13
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    p{
        color: red;
    }
</style>
<head>
    <title>文件上传</title>
</head>
<body>
<form method="post" action="upload.do" enctype="multipart/form-data">
    请选择要上传的文件：<input type="file" name="uploadFiles" multiple/>
    <input type="submit" value="上传文件"/>
</form>
<b>
<p>注意！</p>
<p>只能传输png/jpg格式的图片</p></b>
</body>
</html>
