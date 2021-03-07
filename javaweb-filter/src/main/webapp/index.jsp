<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<h1>当前有<span style="color: blue"><%=request.getServletContext().getAttribute("OnlineCount")%></span>人在线</h1>
<%
    out.write(request.getSession().getId());
%>
</body>
</html>
