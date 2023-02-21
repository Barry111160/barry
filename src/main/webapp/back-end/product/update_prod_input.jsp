<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.product.model.entity.Product" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.product.model.service.ProductTypeService" %>
<%@ page import="com.musclebeach.product.model.entity.ProductType" %>

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
<body>
<%@ include file="../header_sidebar.jsp" %>
<div class="tab-content" id="v-pills-tabContent"
     style="width: 100%; height: calc(100vh - 70px);">
    <%
        ProductTypeService prodTypeSvc = context.getBean(ProductTypeService.class);
        List<ProductType> list = prodTypeSvc.getAll();
        pageContext.setAttribute("list", list);
        Product prodVO = (Product) request.getAttribute("prodVO");
        pageContext.setAttribute("prodVO", prodVO);
    %>
    <!-- ============================================ 首頁 ============================================ -->
    <!--         <div -->
    <!--           class="tab-pane fade" -->
    <!--           id="v-pills-home" -->
    <!--           role="tabpanel" -->
    <!--           aria-labelledby="v-pills-home-tab" -->
    <!--           tabindex="0" -->
    <!--         > -->
    <%--           <img src="<%=request.getContextPath() %>/back-end/resources/image/welcome.gif" style="width: 96%" alt="" /> --%>
    <!--         </div> -->

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
                                        <a href="<%=request.getContextPath()%>/back-end/product//listAllProd.jsp">
                                            <img
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
                                <h4 class="card-title">商品修改</h4>
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
                                          ACTION="<%=request.getContextPath() %>/back-end/product/updateProdInput"
                                          enctype="multipart/form-data" name="form1">
                                        <table>
                                            <tr>
                                                <td>編號:<font color=red><b>*</b></font></td>
                                                <td>${prodVO.proID}</td>
                                            </tr>
                                            <tr>
                                                <td>名稱:</td>
                                                <td><input type="TEXT" name="proName" size="45"
                                                           value="${prodVO.proName}"/></td>
                                            </tr>
                                            <tr>
                                                <td>類別:<font color=red><b>*</b></font></td>
                                                <td><select size="1" name="typeID">
                                                    <c:forEach var="prodTypeVO" items="${list}">
                                                    <option value="${prodTypeVO.typeID}"
                                                        ${(prodVO.typeID==prodTypeVO.typeID)? 'selected':'' }>${prodTypeVO.typeName}
                                                        </c:forEach>
                                                </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>數量:</td>
                                                <td><input type="TEXT" name="proQty" size="45"
                                                           value="${prodVO.proQty}"/></td>
                                            </tr>
                                            <tr>
                                                <td>價錢:</td>
                                                <td><input type="TEXT" name="proPrice" size="45"
                                                           value="${prodVO.proPrice}"/></td>
                                            </tr>
                                            <tr>
                                                <td>內容:</td>
                                                <td><textarea name="proContent" cols="45"
                                                              rows="10">${prodVO.proContent}</textarea></td>
                                            </tr>
                                            <tr>
                                                <td>狀態:</td>
                                                <td>
                                                    <input type="radio" name="proStatus" size="45" id="status1"
                                                           value="1" checked/>
                                                    <label for="status1">已上架</label>
                                                    <br>
                                                    <input type="radio" name="proStatus" size="45" id="status0"
                                                           value="0"/>
                                                    <label for="status0" style="color: red;">已下架</label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>原圖:</td>
                                                <td><img
                                                        src="<%=request.getContextPath()%>/front-end/product/ShowProdImg?proID=${prodVO.proID}"
                                                        width=150px height=150px>
                                                </td>
                                                <td>
                                            </tr>
                                            <tr>
                                                <td>更改為:</td>
                                            </tr>
                                            <tr>
                                                <td><input type="file" name="proImg" value="${prodVO.updateImg}"
                                                           size="45" id="p_file" accept="image/*"/></td>
                                                <td>
                                                    <div id="preview">
                                                        <span class="text"></span>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="proID" value="${prodVO.proID}">
                                        <input type="submit" value="送出修改">
                                    </FORM>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>   <!-- row -->

            </div>  <!-- content wraper -->
        </div>   <!-- main -->
    </div> <!-- 商城 -->
</div>
</main>
<script src="<%=request.getContextPath()%>/back-end/resources/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/resources/index/sidebars.js"></script>
<script src="https://kit.fontawesome.com/db0445c7fa.js" crossorigin="anonymous"></script>
<!-- 預覽圖 -->
<script>
    window.addEventListener("load", function (e) {
        var preview_el = document.getElementById("preview");
        var p_file_el = document.getElementById("p_file");
        var preview_img = function (file) {
            var reader = new FileReader(); // 用來讀取檔案
            reader.readAsDataURL(file); // 讀取檔案
            reader.addEventListener("load", function () {
                var img_str = '<img src="' + reader.result + '" class="preview_img">';
                preview_el.innerHTML = img_str;

            });
        };
        p_file_el.addEventListener("change", function (e) {
            if (this.files.length > 0) {
                preview_img(this.files[0]);
            } else {
                preview_el.innerHTML = '<span class="text">預覽圖</span>';
            }
        });
    });
</script>
<script>
    $(function () {
        $("#status${prodVO.proStatus}").attr("checked", true);
    });
</script>
</body>
</html>
