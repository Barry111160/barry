<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.product.model.entity.Product" %>

<%
    Product prodVO = (Product) request.getAttribute("prodVO");
    pageContext.setAttribute("prodVO", prodVO);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Muscle Beach 後台首頁</title>
    <link
            rel="canonical"
            href="https://getbootstrap.com/docs/5.3/examples/headers/"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
    />
    <link
            rel="canonical"
            href="https://getbootstrap.com/docs/5.3/examples/sidebars/"
    />
    <link href="<%=request.getContextPath()%>/back-end/resources/assets/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style type="text/css"></style>
    <link href="<%=request.getContextPath()%>/back-end/resources/index/index.css" rel="stylesheet"/>
    <!-- Flaticon Font -->
    <link href="<%=request.getContextPath()%>/back-end/resources/lib/flaticon/font/flaticon.css" rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
            rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"/>
</head>
<style>

    table#table-1 {
        background-color: #CCCCFF;
        border: 2px solid black;
        text-align: center;
    }

    table#table-1 h4 {
        color: red;
        display: block;
        margin-bottom: 1px;
    }

    h4 {
        color: blue;
        display: inline;
    }
</style>

<style>
    table {
        width: 450px;
        background-color: white;
        margin-top: 1px;
        margin-bottom: 1px;
    }

    table, th, td {
        border: 0px solid #CCCCFF;
    }

    th, td {
        padding: 1px;
    }
</style>
<body>
<%@ include file="../header_sidebar.jsp" %>
<div class="tab-content" id="v-pills-tabContent"
     style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div
            class="tab-pane fade"
            id="v-pills-home"
            role="tabpanel"
            aria-labelledby="v-pills-home-tab"
            tabindex="0"
    >
        <img src="/image/welcome.gif" style="width: 96%" alt=""/>
    </div>

    <!-- ========================================= 商城管理 ========================================= -->
    <div
            class="tab-pane fade show active"
            id="v-pills-shop"
            role="tabpanel"
            aria-labelledby="v-pills-shop-tab"
            tabindex="0"
            style="border: 2px solid white"
    >
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-sm-6">
                        <h3 class="mb-0 font-weight-bold">商品管理</h3>
                    </div>
                    <div class="col-sm-6">
                        <div class="d-flex align-items-center justify-content-md-end">
                            <div class="mb-3 mb-xl-0 pr-1">
                                <div class="dropdown">
                                    <button style="margin-right:10px;">
                                        src="<%=request.getContextPath()%>/back-end/product/listAllProd.jsp<img
                                            src="<%=request.getContextPath()%>/back-end/product/images/home.png"
                                            title="返回所有商品"
                                            width="30px" height="30px"></a>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row  mt-3">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">商品查詢</h4>
                                <div class="table-responsive">
                                    <%-- 錯誤表列 --%>
                                    <c:if test="${not empty errorMsgs}">
                                        <font style="color:red">請修正以下錯誤:</font>
                                        <ul>
                                            <c:forEach var="message" items="${errorMsgs}">
                                                <li style="color:red">${message}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                    <table>
                                        <tr>
                                            <th>商品編號</th>
                                            <th>商品名稱</th>
                                            <th>商品類型編號</th>
                                            <th>商品數量</th>
                                            <th>商品價格</th>
                                            <th>商品內容</th>
                                            <th>商品狀態</th>
                                            <th>上次修改狀態時間</th>
                                            <th>商品上架時間</th>
                                        </tr>
                                        <tr>
                                            <td><%=prodVO.getProID()%>
                                            </td>
                                            <td><%=prodVO.getProName()%>
                                            </td>
                                            <td><%=prodVO.getTypeID()%>
                                            </td>
                                            <td><%=prodVO.getProQty()%>
                                            </td>
                                            <td><%=prodVO.getProPrice()%>
                                            </td>
                                            <td><%=prodVO.getProContent()%>
                                            </td>
                                            <td>
                                                <c:if test="${prodVO.proStatus == 1}">
                                                    <div>已上架</div>
                                                </c:if>
                                                <c:if test="${prodVO.proStatus == 0}">
                                                    <div style="color: red;">已下架</div>
                                                </c:if>
                                            </td>
                                            <td><%=prodVO.getUpdateTime()%>
                                            </td>
                                            <td><%=prodVO.getCreateTime()%>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</main>
<script src="<%=request.getContextPath()%>/back-end/resources/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/resources/index/sidebars.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {
        $("#toShop").addClass("active");
        $("#toShop").attr("aria-selected", "true");
    })
</script>
</body>
</html>
