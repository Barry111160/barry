<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.room.model.RoomService" %>
<%@ page import="com.musclebeach.room.model.RoomVO" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.mem.model.MemVO" %>


<%
    ApplicationContext context = ApplicationContextUtil.getContext();
    RoomService roomSvc = context.getBean(RoomService.class);
    List<RoomVO> list = roomSvc.getAll();
    pageContext.setAttribute("list", list);
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
    <section class="roomType_body" style="margin: 30px 0px;">
        <div class="container">
            <div class="row" style="margin-top: 100px;">
                <c:forEach var="roomVO" items="${list}">
                    <div class="col-lg-4 col-md-6" style="margin: 10px 0px;">
                        <div class="roomType_item" style="margin-bottom: 30px;">
                            <div>
                                <img
                                        src="${pageContext.request.contextPath }/back-end/room/room.do?action=getImg&roomID=${roomVO.roomID}"
                                        alt="" style="border-radius: 10px 10px 0px 0px;">
                                <div class="ri-text"
                                     style="border: 1px solid #ebebeb; border-radius: 0px 0px 10px 10px; padding: 15px;">
                                    <div
                                            style="display: flex; justify-content: space-between; align-items: center;">
                                        <h4>${roomVO.roomName}</h4>
                                    </div>
                                    <div
                                            style="display: flex; justify-content: space-between; padding: 5px; margin-top: 10px">
                                        <h5 style="color: #AA0000;">
                                            <span
                                                    style="color: black; font-size: 12px;">上午/下午/晚上</span>
                                        </h5>
                                        <form action="room.do" method="post">
                                            <input type="submit" class="btn btn-outline-secondary"
                                                   value="查看場地詳情">
                                            <input type="hidden" name="roomID"
                                                   value="${roomVO.roomID }"> <input type="hidden"
                                                                                     name="action" value="roomDetail">
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
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
    $(document).ready(function () {
        $(".details_btn").click(function () {
            $(this).parent("form").submit();
        })
    })
</script>
    <script>
        $(function () {
            $("#toRoom").addClass("active");
            $("#toRoom").attr("aria-selected", "true");
        })
    </script>
</body>
</html>