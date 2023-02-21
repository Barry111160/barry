<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.mem.model.MemService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    ApplicationContext context = ApplicationContextUtil.getContext();
    MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
    pageContext.setAttribute("memVO", memVO);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>買家資訊填寫</title>

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- all css -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front-end/product/resources/frontStage/lib/flaticon/font/flaticon.css"
          rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front-end/product/resources/frontStage/css/style.min.css"
          rel="stylesheet">
    <link href="https://kit.fontawesome.com/db0445c7fa.css" rel="stylesheet" crossorigin="anonymous">
    <link href="<%=request.getContextPath()%>/front-end/product/resources/shop/css/checkout.css" rel="stylesheet">


</head>

<body class="bg-white">

<!-- header -->
 <%@ include file="/front-end/common/header.jsp" %>


<!-- Checkout Start -->
<h1><i class="fa-solid fa-truck-fast"></i>訂單資訊</h1>
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

    <div class="container" style="padding-top: 100px;">
        <div class="memData-container">

            <div class="card-container">

                <div class="front">
                    <div class="image">

                    </div>
                    <div class="card-number-box">################</div>
                    <div class="flexbox">
                        <div class="box">
                            <span>card holder</span>
                            <div class="card-holder-name">full name</div>
                        </div>
                        <div class="box">
                            <span>expires</span>
                            <div class="expiration">
                                <span class="exp-month">mmyy</span>

                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/order/" name="form1">

                <div class="inputBox">
                    <!--<span>會員編號</span> -->
                    <input type="hidden" name="memID" value="1"/>
                </div>
                <div class="inputBox">
                    <span>信用卡卡號</span>
                    <input type="text" maxlength="16" name="ccNumber" class="card-number-input"/>

                </div>
                <div class="inputBox">
                    <span>持卡人姓名</span>
                    <input type="text" name="orderRecName" class="card-holder-input"
                           value="<%= (memVO == null) ? "" : memVO.getMemName()%>" required/>
                </div>
                <div class="flexbox">
                    <div class="inputBox">
                        <span>信用卡期限</span>
                        <input type="text" maxlength="4" name="ccTime" class="month-input"/>

                    </div>

                    <div class="inputBox">
                        <span>驗證碼</span>
                        <input type="text" maxlength="3" name="ccvc" class="cvv-input"/>
                    </div>
                    <div class="inputBox">
                        <span>電話</span>
                        <input type="tel" type="tel" id="phone" name="orderRecPhone"
                               pattern="[0-9]{3}[0-9]{3}[0-9]{4}"
                               value="<%= (memVO == null) ? "" : memVO.getMemPhone()%>" required/><br><br>
                    </div>
                    <div class="inputBox">
                        <span>地址</span>
                        <input type="text" id="address" name="orderAddress"
                               value="<%= (memVO == null) ? "" : memVO.getMemAddress()%>" required/><br><br>
                    </div>
                </div>

                <input type="hidden" name="action" value="insertOrder">
                <input type="hidden" name="memID" value="${memVO.memID}">
                <input type="submit" value="確認付款" class="submit-btn">
            </form>

        </div>
    </div>
</table>
<!-- Checkout End -->


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
<script>

    document.querySelector('.card-number-input').oninput = () => {
        document.querySelector('.card-number-box').innerText = document.querySelector('.card-number-input').value;
    }

    document.querySelector('.card-holder-input').oninput = () => {
        document.querySelector('.card-holder-name').innerText = document.querySelector('.card-holder-input').value;
    }

    document.querySelector('.month-input').oninput = () => {
        document.querySelector('.exp-month').innerText = document.querySelector('.month-input').value;
    }


    document.querySelector('.cvv-input').onmouseenter = () => {
        document.querySelector('.front').style.transform = 'perspective(1000px) rotateY(-180deg)';
        document.querySelector('.back').style.transform = 'perspective(1000px) rotateY(0deg)';
    }

    document.querySelector('.cvv-input').onmouseleave = () => {
        document.querySelector('.front').style.transform = 'perspective(1000px) rotateY(0deg)';
        document.querySelector('.back').style.transform = 'perspective(1000px) rotateY(180deg)';
    }

    document.querySelector('.cvv-input').oninput = () => {
        document.querySelector('.cvv-box').innerText = document.querySelector('.cvv-input').value;
    }

</script>
<script src="https://kit.fontawesome.com/db0445c7fa.js" crossorigin="anonymous"></script>
<script>
    $(function () {
        $("#toShop").addClass("active");
        $("#toShop").attr("aria-selected", "true");
    })
</script>
</body>

</html>