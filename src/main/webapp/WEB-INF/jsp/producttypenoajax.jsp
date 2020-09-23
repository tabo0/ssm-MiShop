<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <script type="text/javascript">
        function addproducttypepage(){
            window.location.href="${pageContext.request.contextPath}/addproducttypepage";//get
        }
    </script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/list.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bright.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addBook.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/kkpage.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/kkpage.js"></script>
    <title></title>
</head>

<body>
<div id="brall">
    <div id="nav">
        <p>商品类型管理>商品类型列表</p>
    </div>
    <div id="table">
        <div id="top">
            <input type="button" class="btn btn-warning" id="btn1" value="新增商品类型" onclick="addproducttypepage()">
            <form action="${pageContext.request.contextPath}/getproducttypepage" method="get" style="text-align: center">
                请输入类型的名称: <input type="text" name="typename" value="${typename}">
                <input type="submit" value="查询">
            </form>
        </div>
        <!--显示没有分页的商品信息-->
        <div id="middle">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>商品类型名称</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${producttypes.list}" var="pt">
                    <tr>
                        <td>${pt.name}</td>
                        <td>
                            <button type="button" class="btn btn-info myupdate"
                                    onclick="ptmodify(${pt.id})">修改</button>
                            <button type="button" class="btn btn-warning" id="mydel"
                                    onclick="ptdel(${pt.id})">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <!--分页栏-->
            <div class="footNum">
                <ul>
                    <c:choose>
                        <c:when test="${producttypes.page eq 1 }">
                            <li class="pre"><a href="javascript:void(0)">上一页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="pre"><a
                                    href="${pageContext.request.contextPath}/getproducttypepage?page=${producttypes.page-1}&typename=${typename}">
                                上一页</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${producttypes.pages}" step="1" var="index">
                        <c:choose>
                            <c:when test="${producttypes.page eq index}">
                                <li class="num current"><a href="javascript:void(0)">${index}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="num">
                                    <a href="${pageContext.request.contextPath}/getproducttypepage?page=${index}&typename=${typename}">${index}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${producttypes.page eq producttypes.pages}">
                            <li class="last"><a href="javascript:void(0)">下一页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="last"><a
                                    href="${pageContext.request.contextPath}/getproducttypepage?page=${producttypes.page+1}&typename=${typename}">
                                下一页</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    //修改函数
    function ptmodify(id) {
        location.href = "${pageContext.request.contextPath}/toupdateprotypepage?id="+id;//get
    }
    //删除函数
    function ptdel(id) {
        if (confirm("确定删除吗")) {
            location.href = "${pageContext.request.contextPath}/delproducttype?id="+id;//get
        }
    }
</script>
</html>