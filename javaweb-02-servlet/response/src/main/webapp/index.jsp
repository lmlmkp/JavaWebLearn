<%@ page contentType="text/html; charset=UTF-8"  %>
<html>
<head>
    <title>表单验证码</title>
</head>
<body>
<h2>这里是response的index目录</h2>
<%--${pageContext.request.contextPath}代表当前项目--%>
<form action="${pageContext.request.contextPath}/login" method="get">
    用户名：<input type="text" name="username"> <br>
    密码：<input type="password" name="password"> <br>


    <input type="submit" value="点击提交">

</form>
</body>
</html>