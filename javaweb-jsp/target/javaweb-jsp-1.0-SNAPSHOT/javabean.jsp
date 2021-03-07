<%@ page import="com.lmkp.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: lm
  Date: 2021/3/7
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //User user = new User();
    //user.setName("lmkp");
    //user.setPassword("123456");
%>

<jsp:useBean id="user" class="com.lmkp.pojo.User" scope="page"/>
<jsp:setProperty name="user" property="name" value="lmkp"/>
<jsp:setProperty name="user" property="password" value="123456"/>

用户名：<jsp:getProperty name="user" property="name"/>
密码：<jsp:getProperty name="user" property="password"/>
</body>
</html>
