<%--
  Created by IntelliJ IDEA.
  User: lm
  Date: 2021/3/6
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--http:localhost:8080/jsptag2.jsp?v1=v1&v2=v2--%>
<jsp:forward page="jsptag2.jsp">
    <jsp:param name="v1" value="v1"/>
    <jsp:param name="v2" value="v2"/>
</jsp:forward>
</body>
</html>
