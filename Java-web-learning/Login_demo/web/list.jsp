<%--
  Created by IntelliJ IDEA.
  User: Garen
  Date: 2019/12/10
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        //是否确认删除的询问---js
        function deleteUser(id) {
            if (confirm("确定要删除这一项吗？")) {
                location.href = "${pageContext.request.contextPath}/DeleteUserServlet?id=" + id;
            }
        }

        //给单击删除选中按钮加事件 提交表单
        window.onload = function () {
            document.getElementById("delSelected").onclick = function () {
                //增加提示信息
                if (confirm("确定要删除所选项吗？")) {
                    var flag = false;
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {    //判断是否有选中条目, 有一个则flag=true, 立即break 防止空指针异常
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        document.getElementById("form").submit();
                    }
                }
            }
            //复选框全选与全部选状态设置
            document.getElementById("firstCB").onclick = function () {
                var cbs = document.getElementsByName("uid");  //获取列表中所有的checkbox
                for (var i = 0; i < cbs.length; i++) {//遍历设置为选中
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left; margin: 5px ">
        <form class="form-inline" action="${pageContext.request.contextPath}/FindUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" value="${condition.name[0]}" class="form-control" id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" name="address" value="${condition.address[0]}" class="form-control"
                       id="exampleInputName3">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="text" name="email" value="${condition.email[0]}" class="form-control"
                       id="exampleInputEmail2">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin: 5px">
        <a class="btn btn-primary" href="
            ${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
    </div>
    <%--将复选框加入到表单中自动提交--%>
    <form id="form" action="${pageContext.request.contextPath}/DelSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCB"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.id}"></td>
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td>
                        <a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/FindUserServlet?id=${user.id}">修改
                        </a>&nbsp;
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div style="margin: 5px ;text-align: center">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <%--         判断当前页码是否为null，rows是否为null--%>
                <c:if test="${pb.currentPage == 1}">
                    <li class="disabled">
                        </c:if>
                    <c:if test="${pb.currentPage != 1}">
                <li></c:if>

                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pb.currentPage -
                    1}&row=2&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">

                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&row=2&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != i }">
                        <li><a
                                href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&row=2&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pb.currentPage +
                    1}&row=2&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 20px ; margin: 10px">共${pb.totalPage}页,共${pb.totalCount}条记录</span>
            </ul>
        </nav>

    </div>

</div>
</body>
</html>
