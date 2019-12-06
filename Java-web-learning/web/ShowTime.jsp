<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by Garen
  Date: 2019/12/5
  Time: 20:26
  Title: 改写CookieTestServlet
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LastTime</title>
</head>
<body>
<% Cookie[] cookies = request.getCookies();
    boolean flag = false;
    if (cookies != null && cookies.length > 0) { //cookie not null
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (name.equals("LastTime")) { //Not first time to visit
//set time format
                flag = true;
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String str_date = sdf.format(date);
                System.out.println("编码前：" + str_date);
//URL编码
                str_date = URLEncoder.encode(str_date, "utf-8");
                System.out.println("编码后：" + str_date);
                cookie.setValue(str_date);
                cookie.setMaxAge(60 * 60 * 24 * 30); // set age as a month
                response.addCookie(cookie);

//response,get cookie's value-time
                String value = cookie.getValue();
                System.out.println("解码前：" + value);
//URL解码：
                value = URLDecoder.decode(value, "utf-8");
                System.out.println("解码后：" + value);
                out.write("<hr>欢迎回来，您上次访问时间为" + value + " </hr>");
                break; // done
            }
        }
    }
    if (cookies == null || cookies.length == 0 || flag == false) {
//cookies is null, first time to visit
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str_date = sdf.format(date);

        System.out.println("编码前：" + str_date);
//URL编码
        str_date = URLEncoder.encode(str_date, "utf-8");
        System.out.println("编码后：" + str_date);

//new a cookie
        Cookie cookie = new Cookie("LastTime", str_date);

//set age
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
%>

        <hr>您好，欢迎您首次访问 </hr>
        <span color="0,179,8">Welcome to my page</span>
<%
    }
%>
</body>
</html>
