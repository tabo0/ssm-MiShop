<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 用于格式化时间 -->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/list.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bright.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addBook.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <title></title>
<%--    <style type="text/css">--%>
<%--        #batchdelpro {--%>
<%--            background-color: orangered;--%>
<%--            width: 150px;--%>
<%--            height: 48px;--%>
<%--            margin-top: 15px;--%>
<%--            margin-left: 25px;--%>
<%--            font-size: 18px;--%>
<%--        }--%>
<%--    </style>--%>
</head>

<body>
<!-- 判断否登录  登录成功会将users保存到session ,如果没有登录 session中的users为空 -->
<!--UsersController.java中的登录中的登录成功保存用户信息到session的代码
session.setAttribute("users", users); -->
<c:if test="${sessionScope.users == null}">
    <!-- 没有登录，重新去登录页面登录 对应UsersController.java中的
    @RequestMapping(value="/login",method=RequestMethod.GET)-->
    <c:redirect url="login"></c:redirect>
</c:if>
<div id="brall">
    <div id="nav">
        <p>订单管理>订单列表</p>
    </div>
    <div id="table">
        <div id="middle">
            <table class="table table-bordered table-striped">
                <tr>
<%--                    <th style="width: 50px;text-align: center;">--%>
<%--                        <input type="checkbox" style="width: 20px;height: 20px;" id="checkAll">--%>
<%--                    </th>--%>
                    <th>订单号</th>
                    <th>客户姓名</th>
                    <th>地址</th>
                    <th>总价格</th>
                    <th>备注</th>
                    <th>订单状态</th>
                    <th>时间</th>
                    <th>操作</th>
                </tr>
                <!-- pagebean分页实体 list属性为当前页的数据 -->
                <c:forEach items="${pagebean.list}" var="o">
                    <tr style="text-align: center">
                        <!-- 订单号  -->
                        <td>${o.oid}</td>
                        <!--客户姓名  -->
                        <td>${o.cname}</td>
                        <!--地址  -->
                        <td>${o.address}</td>
                        <!--总价格  -->
                        <td>${o.totalprice}</td>
                        <!-- 备注 -->
                        <td>${o.remarks}</td>
                        <!-- 订单状态 -->
                        <td>${o.status}</td>
                        <td><fmt:formatDate value="${o.odate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                        <td>
                            <!-- 详情 （选做） -->
                            <button type="button" class="btn btn-info" onclick="odetail(${o.oid})">详情</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <!--分页栏-->
            <div class="footNum">
                <ul>
                    <c:choose>
                        <c:when test="${pagebean.page eq 1}">
                            <li class="pre"><a href="javascript:void(0)">上一页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="pre"><a
                                    href="${pageContext.request.contextPath}/getorderbypage?page=${pagebean.page-1}">
                                上一页</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${pagebean.pages}" step="1" var="index">
                        <c:choose>
                            <c:when test="${pagebean.page eq index}">
                                <li class="num current"><a href="javascript:void(0)">${index}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="num"><a
                                        href="${pageContext.request.contextPath}/getorderbypage?page=${index}">${index}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${pagebean.page eq pagebean.pages}">
                            <li class="last"><a href="javascript:void(0)">下一页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="last"><a
                                    href="${pageContext.request.contextPath}/getorderbypage?page=${pagebean.page+1}">
                                下一页</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>