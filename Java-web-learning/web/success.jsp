<%--
  Created by IntelliJ IDEA.
  User: Garen
  Date: 2019/12/6
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功页面</title>
</head>
<body>
    <h1 align="center"> 登录成功,<%=request.getSession().getAttribute("user")%>,欢迎您！</h1>
</body>
</html>
