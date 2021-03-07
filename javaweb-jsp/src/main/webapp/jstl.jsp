<%--
  Created by IntelliJ IDEA.
  User: lm
  Date: 2021/3/6
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="jstl.jsp" method="get">
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="登录">
</form>


<%
    if (request.getParameter("username").equals("admin")){
        out.write("登录成功");
    }
%>

<c:if test="${param.username == 'admin'}" var="isAdmin">
    <c:out value="管理员登录" />
</c:if>
<c:out value="${isAdmin}" />
</body>
</html>
