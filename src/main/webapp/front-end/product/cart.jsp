<%@ page import="com.musclebeach.cart.entity.CartProduct" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    List<CartProduct> cartList = (List<CartProduct>) session.getAttribute("cartList");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>購物車</title>

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- all css -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front-end/product/resources/frontStage/lib/flaticon/font/flaticon.css"
          rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front-end/product/resources/frontStage/css/style.min.css"
          rel="stylesheet">

    <style>
        table {
            border-collapse: collapse;
            width: 60%;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: lightgray;
        }

        input[type="number"] {
            width: 50px;
        }

        #total {
            font-weight: bold;
        }

        button {
            padding: 10px 20px;
            background-color: lightgray;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }
    </style>
</head>

<body class="bg-white">

<!-- header -->
<c:if test="${ memVO.memID == null}">
    <%@ include file="/front-end/common/header.jsp" %>
</c:if>
<c:if test="${ memVO.memID!=null}">
    <%@ include file="/front-end/common/headerlogin.jsp" %>
</c:if>


<!-- Start All Title Box -->
<div class="all-title-box">
    <div class="container">
        <div class="row">
            <div class="col-lg-12"  style="margin-top:89.5px">
                <a href="<%=request.getContextPath()%>/front-end/product/shop.jsp"
                   class="btn btn-info mb-3">返回商城</a>
                <ul class="breadcrumb">
                    <h2>購物車清單</h2>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- End All Title Box -->

<!-- Cart Start -->
<table id="cartTable">
    <tr>
        <th>商品名稱</th>
        <th>數量</th>
        <th>價格</th>
        <th>總金額</th>
        <th>操作</th>
    </tr>
    <c:if test="${cartList == null || cartList.isEmpty()}">
        你的購物車沒有東西喔
    </c:if>
    <c:forEach items="${cartList}" var="cartProduct">
        <tr id="tr${cartProduct.proID}">
            <td>${cartProduct.proName}</td>
            <td>
                <input type="number" value="${cartProduct.count}" class="quantity" min="1" max="10"
                       id="quantity${cartProduct.proID}">
                <input hidden="hidden" value="${cartProduct.count}" id="initialCount${cartProduct.proID}">
            </td>
            <td class="price" id="price${cartProduct.proID}">${cartProduct.proPrice}</td>
            <td class="total" id="totalPrice${cartProduct.proID}">${cartProduct.totalPrice}</td>
            <td>
                <button onclick="remove(${cartProduct.proID})">刪除</button>
            </td>
        </tr>
    </c:forEach>
</table>
<p>總金額: $<span id="total">".total"</span></p>
<a href="<%=request.getContextPath()%>/front-end/product/checkout.jsp">
    <button id="checkout">結帳</button>
</a>
<script>
    getTotal();
    const quantity = document.querySelectorAll(".quantity");
    quantity.forEach(q => {
        q.addEventListener("change", e => {
            const proID = e.target.id.substring(e.target.id.length - 1);
            const newValue = parseInt(e.target.value);
            if (e.target.oldValue === null || e.target.oldValue === undefined) {
                e.target.oldValue = document.querySelector('#initialCount' + proID).value;
            }
            const oldValue = parseInt(e.target.oldValue);
            const totalPriceTd = document.querySelector('#totalPrice' + proID);
            if (typeof totalPriceTd.innerText !== "number") {
                totalPriceTd.innerText = Math.floor(parseInt(totalPriceTd.innerText) * (newValue / oldValue));
            } else {
                totalPriceTd.innerText = Math.floor(totalPriceTd.innerText * (newValue / oldValue));
            }
            e.target.oldValue = e.target.value;
            getTotal();
            axios.post("carts", [{
                proID: parseInt(proID),
                count: newValue
            }]);
        })
    })

    function remove(id) {
        axios.delete('carts/' + id).then((res) => {
            if (res.data.data) {
                alert("刪除成功");
                getTotal();
            }
        });
        document.querySelector('#tr' + id).remove();
    }

    function getTotal() {
        let total = 0;
        document.querySelectorAll('.total').forEach(p => {
            if (typeof p.innerText !== 'number') {
                total += parseInt(p.innerText);
            } else {
                total += p.innerText;
            }
        })
        document.querySelector("#total").innerText = Math.floor(total);
    }
</script>
<!-- Cart End -->


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
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.3.2/axios.min.js"></script>
<script>
</script>
<script>
    $(function () {
        $("#toShop").addClass("active");
        $("#toShop").attr("aria-selected", "true");
    })
</script>
</body>

</html>