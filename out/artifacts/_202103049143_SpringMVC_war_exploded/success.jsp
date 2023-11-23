<jsp:useBean id="images" scope="request" type="java.util.List"/>
<jsp:useBean id="filename" scope="request" type="java.util.List"/>
<jsp:useBean id="filesize" scope="request" type="java.util.List"/>
<jsp:useBean id="fileextension" scope="request" type="java.util.List"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
    #center
    {
        width: 65%;
        margin: 0 auto;
    }
</style>
<title>成功页面</title>
<body>
<script>
    let 文件上传成功;
    文件上传成功</script>
<h1>上传成功！</h1>
<hr><hr>

<h3>本次上传的图片：</h3>
<%
    // 使用for循环遍历图片路径列表
    for(int a = 0; a < images.size(); a++) {
        String imagePath = images.get(a).toString(); // 获取图片路径
        String Filename= (String) filename.get(a);
        String Filesize=(String) filesize.get(a);
        String Fileextension=(String) fileextension.get(a);
%><div id="center">
<b><% int b=a+1;out.print("图片"+b+":");%></b>
<p><%= Filename%></p>
<p><%= Filesize%></p>
<p><%= Fileextension%></p>
<img src="<%= imagePath %>" width="100%" alt="Uploaded Image"> <!-- 使用拼接后的URL显示图片 --></div>
<hr>
<%
    }
%>

</body>
</html>