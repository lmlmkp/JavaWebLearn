<%--
  Created by IntelliJ IDEA.
  User: lm
  Date: 2021/3/6
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--setAttribute(String name,Object value,int scope)--%>
<%--APPLICATION_SCOPE	在application对象中搜索--%>
<%--PAGE_SCOPE	在page对象中搜索--%>
<%--REQUEST_SCOPE	在request对象中搜索--%>
<%--SESSION_SCOPE	在session对象中搜索--%>

<%
    pageContext.setAttribute("name","lmkp",pageContext.SESSION_SCOPE);
    pageContext.setAttribute("pawd","lmkp",4);
%>
</body>
</html>
