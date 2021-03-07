<%--
  Created by IntelliJ IDEA.
  User: lm
  Date: 2021/3/6
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //通过寻找方式来取
    //按道理说各自取各自的，这里全部用pageContext
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String)  pageContext.findAttribute("name2");
    String name3 = (String)  pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");

%>

<%--使用EL表达式输出--%>

<h1>${name1}</h1>
<h1>${name2}</h1>
<h1>${name3}</h1>
<h1>${name4}</h1>
</body>
</html>
