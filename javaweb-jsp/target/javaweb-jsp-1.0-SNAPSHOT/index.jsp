<%@ page import="java.util.Date" %>
<html>
<body>


<%@include file="common/header.jsp"%>

<%--jsp标签--%>
<jsp:include page="/common/header.jsp"></jsp:include>



<%= new Date()%>

<%
    for (int i = 0; i < 10; i++) {
        out.println("<h3>" + i + "</h3>");
    }
%>


<%--在代码嵌入HTML元素--%>
<%
    for (int i = 0; i < 5; i++) {
%>
<h1>Hello,World  <%=i%>
</h1>
<%
    }
%>

<%--JSP声明，定义到类里而不是方法里--%>
<%!
    static {
        System.out.println("Loading Servlet!");
    }

    private int globalVar = 0;

    public void kuang(){
        System.out.println("进入了方法Kuang！");
    }
%>

</body>
</html>
