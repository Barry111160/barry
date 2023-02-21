<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%@ page import="com.musclebeach.room.model.RoomVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    RoomVO roomVO = (RoomVO) request.getAttribute("roomVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
    MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free Website Template" name="keywords">
    <meta content="Free Website Template" name="description">
    <title>Msucle Beach: 找場地</title>

    <link href="<%=request.getContextPath()%>/front-end/room/images/favicon.ico" rel="icon"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/front-end/room/lib/flaticon/font/flaticon.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/front-end/room/css/style.min.css" rel="stylesheet"/>

    <style>
        img {
            display: block;
            width: 100%;
            height: auto;
        }

        .login {
            display: inline;
            position: relative;
            Left: calc(100% - 150px);
            top: 5px;
        }

        .register {
            display: inline;
            position: relative;
            Left: calc(100% - 150px);
            top: 5px;
            color: white;
        }
    </style>
</head>

<body class="bg-white">
<c:if test="${ memVO.memID == null}">
    <%@ include file="/front-end/common/header.jsp" %>
</c:if>
<c:if test="${ memVO.memID!=null}">
    <%@ include file="/front-end/common/headerlogin.jsp" %>
</c:if>
<!-- Navbar End -->
<div class="container py-5">
    <section class="room-details-section spad"
             style="padding-bottom: 15px">
        <div class="room_img container">
            <div class="row"
                 style="justify-content: center; padding: 30px 0px; margin-top: 100px">
                <div class="col-lg-6">
                    <img
                            src="${pageContext.request.contextPath }/back-end/room/room.do?action=getImg&roomID=${roomVO.roomID}"
                            alt="" style="border-radius: 10px 10px 0px 0px;">
                </div>
            </div>
        </div>
        <div class="room_details container">
            <div
                    style="font-size: 40px; color: #000000; padding: 15px 0px; display: flex; justify-content: space-between; align-items: center;">
                場地名稱：${roomVO.roomName}</div>
            <div style="font-size: 22px; color: #CC0000">場地位於：${roomVO.roomAddress}</div>
            <div style="font-size: 18px;">場地介紹：${roomVO.roomContent}</div>
            <div style="font-size: 18px; color: #CC0000">場地價格：${roomVO.roomPrice} ( 單次 )</div>
            <div style="font-size: 18px;">租借時段：上午 ( 10:00 ~ 11:30 ) / 下午 ( 14:00 ~ 15:30 ) / 晚上 ( 18:00 ~ 19:30 )</div>
            <hr>
            <div class="row">
                <div class="col-lg-8"></div>

            </div>
        </div>
    </section>
    <div style="display: flex; justify-content: center;">

        <form method="post" action="<%=request.getContextPath()%>/front-end/room/room.do">
            <input type="hidden" name="action" value="booking"/> <input
                type="hidden" name="roomID" value="${roomVO.roomID}"/> <input
                type="submit" value="我要預約" onclick="myFunction(event)" class="btn btn-outline-danger btn-lg"
        />
        </form>
    </div>
</div>

<!-- Footer Start -->
<div
        class="footer container-fluid mt-5 py-5 px-sm-3 px-md-5 text-white">
    <div style="display: inline; padding: 0px;">
        <div class="col-lg-0 col-md-10 " style="margin: auto">
            <h5 class="text-white mb-4">聯絡我們</h5>
            <p style="display: inline-block;">
                <i class="fa fa-map-marker-alt mr-2"></i>住址
            </p>
            <p style="display: inline-block; position: absolute; right: 420px;">
                <i class="fa fa-phone-alt mr-2"></i>0987-654-321
            </p>
            <p style="display: inline; float: right;" ;>
                <i class="fa fa-envelope mr-2"></i>abc信箱@gmail.com
            </p>
        </div>
    </div>
    <div class="container border-top border-dark pt-2">
        <p class="m-0 text-center text-white">
            &copy; <a class="text-white font-weight-bold" href="#">Muscale
            beach</a> <a class="text-white font-weight-bold"
                         href="https://htmlcodex.com"></a>版權所有 翻印必究
        </p>
    </div>
</div>
<!-- Footer End -->

<!-- Back to Top -->
<a href="#" class="btn btn-outline-primary back-to-top"><i
        class="fa fa-angle-double-up"></i></a>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/room/lib/easing/easing.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/room/lib/waypoints/waypoints.min.js"></script>

<!-- Contact Javascript File -->
<script src="<%=request.getContextPath()%>/front-end/room/mail/jqBootstrapValidation.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/room/mail/contact.js"></script>

<!-- Template Javascript -->
<script src="<%=request.getContextPath()%>/front-end/room/js/main.js"></script>
<script>
    $(function () {
        $("#toRoom").addClass("active");
        $("#toRoom").attr("aria-selected", "true");
    })
</script>

<script>
    function myFunction(e) {

        <%if (memVO == null) {%>
        if (!confirm('請先登入會員')) {
            e.preventDefault();
        }
        <%} else if (memVO.getMemAccess() == 0) {%>
        if (!confirm('請先成為健身會員')) {
            e.preventDefault();
        }
        <%}%>
    }
</script>

</body>

</html>