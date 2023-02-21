<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>商品詳情</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- all css -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front-end/product/resources/frontStage/lib/flaticon/font/flaticon.css"
          rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front-end/product/resources/frontStage/css/style.min.css"
          rel="stylesheet">

    <link href="<%=request.getContextPath()%>/front-end/product/resources/shop/css/shopDetail.css" rel="stylesheet">

    <link href="https://kit.fontawesome.com/db0445c7fa.css" rel="stylesheet" crossorigin="anonymous">
</head>

<body class="bg-white">

<!-- header -->
<%@include file="/front-end/common/header.jsp" %>

<!-- Start All Title Box -->
<div class="all-title-box" style="padding-top: 100px;">
    <div class="container">
        <div class="row flex-column">
            <div class="col-lg-12">
                <a href="<%=request.getContextPath()%>/front-end/product/shop.jsp"
                   class="btn btn-info mb-3">返回商城</a>
                <ul class="breadcrumb" style="border-radius : 15px;">
                    <h2>商品詳情</h2>
                    <i class="fa-brands fa-shopify" style="font-size: 36px ;margin-right: 10px;"></i>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- End All Title Box -->

<!-- shopDetail -->
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div style="display: flex;">
                <div class="me-3">
                    <img src="<%=request.getContextPath()%>/front-end/product/ShowProdImg?proID=${prodVO.proID}"
                         width="200px"
                         class="img-fluid hoverable"
                         style="border-radius : 15px;">
                </div>
                <div class="text-block NopaddingDetails" style="margin-left: 10px">
                    <h3 class="mb-4">${prodVO.proName}</h3>
                    <p class="mb-2">庫存：${prodVO.proQty} 個</p>
                    <%--            <p class="mb-2">價格：${prodVO.proPrice}</p>--%>
                    <p class="price">
                <span class="price-new"
                      style="color:red">會員價:${prodVO.proPrice*0.9}元</span>
                        <span class="price-old">原價:${prodVO.proPrice}元</span>
                    </p>
                    <p class="mb-2">詳情：${prodVO.proContent}</p>
                    <button onclick="addInCart(${prodVO.proID})" class="btn btn-info mb-3">加入購物車</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- shopDetail -->


<!-- Footer -->
<%@include file="/front-end/common/footer.jsp" %>

<!-- Back to Top -->
<a href="#" class="btn btn-outline-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


<!-- all js -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/product/resources/frontStage/lib/easing/easing.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/product/resources/frontStage/lib/waypoints/waypoints.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/product/resources/frontStage/mail/jqBootstrapValidation.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/product/resources/frontStage/mail/contact.js"></script>
<script src="<%=request.getContextPath()%>/front-end/product/resources/frontStage/js/main.js"></script>
<script src="https://kit.fontawesome.com/db0445c7fa.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.3.2/axios.min.js"></script>
<script>
    function addInCart(id) {
        axios.post('../../carts', [{
            proID: id,
            count: 1,
        }]).then((res) => {
            const resURL = res.request.responseURL;
            if ('/login.jsp' === resURL.substring(resURL.lastIndexOf('/'))) {
                window.location.href = resURL;
            }
            if (res.data.data > 0) {
                window.location.href = '../../carts';
            }
        });
    }
</script>
</body>
</html>