<%--
  Created by IntelliJ IDEA.
  User: Garen
  Date: 2019/11/28
  Time: 16:49
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project's front page</title>
</head>
<body>

<%--就近原则,输出局部变量 i = 5--%>
    <%
        out.write("Hello");
        //System.out.println("Hello");
        //int i = 5;
    %>
    <%!
        //int i = 3;
    %>

<%--    <%= i %>--%>
    <%--加不加虚拟目录的判断规则:判断请求将来从哪里发出 1. 给客户端使用，需要写虚拟目录 2. 给服务器使用，不需要加虚拟目录--%>
<a href="/1129/login.html"> 登录界面,由此进入</a><br>
<a href="/1129/ResponseServlet01">测试Response Demo1 , 由此进入</a><br>
<a href="/1129/download.html">测试文件下载案例 , 由此进入</a><br>
<a href="/1129/CookieDemo1">测试Cookie , 由此进入</a><br>
<a href="/1129/CookieTestServlet">测试获取上次访问时间Cookie案例 , 由此进入</a><br>
<a href="/1129/login.jsp">测试验证码改进版案例,由此进入</a>


</body>
</html>
