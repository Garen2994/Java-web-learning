<%--
  Created by IntelliJ IDEA.
  User: Garen
  Date: 2019/12/5
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <script>
        /*javascript 响应单击 运行换图片的脚本*/
        window.onload = function () {
            document.getElementById("img").onclick = function () {
                this.src = "/1129/RandomCodeServlet?time=" + new Date().getTime();
            }
        }
    </script>
</head>
<body>
<form action="/1129/LoginDemoPro" method="post">
    <table>
        <tr>
            <td>username</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>check-code</td>
            <td><input type="text" name="checkCode"></td>
        </tr>
        <tr>
            <td colspan="2"><img id="img" src="/1129/RandomCodeServlet"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录"></td>
        </tr>
    </table>
</form>
    <div> <%=request.getAttribute("checkCode_fail") == null ? "" : request.getAttribute("checkCode_fail")%></div>
    <div><%=request.getAttribute("login_fail") == null ? "" : request.getAttribute("login_fail")%></div>
</body>
</html>
