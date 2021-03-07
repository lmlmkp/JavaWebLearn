<%--
  Created by IntelliJ IDEA.
  User: lm
  Date: 2021/3/6
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PageContext</title>
</head>
<body>

<%--内置对象--%>
<%
    pageContext.setAttribute("name1","小明1"); // 数据只在一个页面中有效
    request.setAttribute("name2","小明2"); // 数据只在一次请求中有效，转发会携带
    session.setAttribute("name3","小明3"); // 数据只在一次会话有效，从打开浏览器到关闭浏览器
    application.setAttribute("name4","小明4"); // 数据只在服务器中有效，从打开服务器到关闭服务器
%>

<%
    //通过pageContext来取我们保存的值
    String name = (String) pageContext.getAttribute("name1");
    System.out.println(name);
    //通过寻找方式来取
    //按道理说各自取各自的，这里全部用pageContext
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String)  pageContext.findAttribute("name2");
    String name3 = (String)  pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");

%>

<%--使用EL表达式输出--%>
<%
    out.write(name);
%>
<%= name%>
<h1>1111</h1>
<h1>${name1}</h1>
<h1>${name2}</h1>
<h1>${name3}</h1>
<h1>${name4}</h1>
</body>
</html>
