<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.product.model.entity.ProductType" %>

<%
    ProductType prodTypeVO = (ProductType) request.getAttribute("prodTypeVO");
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
    <link href="https://kit.fontawesome.com/db0445c7fa.css" rel="stylesheet" crossorigin="anonymous">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
            rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"/>


</head>
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
                        <a href="<%=request.getContextPath()%>/back-end/product/listAllProd.jsp"
                           class="nav-link">
                            <p><i class="fa-sharp fa-solid fa-dumbbell"></i>商品管理</p>
                        </a>
                    </div>
                    <div class="col-sm-6">
                        <div class="d-flex align-items-center justify-content-md-end">
                            <div class="mb-3 mb-xl-0 pr-1">
                                <div class="dropdown">
                                    <button style="margin-right:10px;">
                                        <a href="<%=request.getContextPath()%>/back-end/product/listAllProdType.jsp"><img
                                                src="<%=request.getContextPath()%>/back-end/product/images/home.png"
                                                title="返回所有商品種類" width="30px"
                                                height="30px"></a>
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
                                <h4 class="card-title">新增商品類別</h4>
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
                                    <FORM METHOD="post"
                                          ACTION="<%=request.getContextPath() %>/back-end/product/addOneProd"
                                          enctype="multipart/form-data" name="form1">
                                        <table>
                                            <tr>
                                                <td>類別:</td>
                                                <td><input type="TEXT" name="typeName" size="45"
                                                           value="<%= (prodTypeVO==null)? "" : prodTypeVO.getTypeName()%>"/>
                                                </td>
                                            </tr>

                                        </table>
                                        <input type="hidden" name="action" value="insert_type">
                                        <br><br>
                                        <input type="submit" value="送出新增">
                                    </FORM>
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
<script src="https://kit.fontawesome.com/db0445c7fa.js" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {
        $("#toShop").addClass("active");
        $("#toShop").attr("aria-selected", "true");
    })
</script>
</body>
</html>
