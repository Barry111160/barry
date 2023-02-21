<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.room.model.RoomVO" %>
<%@ page import="com.musclebeach.room.model.RoomService" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="BIG5">
    <title>場地管理</title>
    <link href="css/index.css" rel="stylesheet"/>
    <link href="css/flaticon.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style type="text/css"></style>
    <style>
        img {
            display: block;
            max-width: 30%
        }
    </style>
</head>
<body>
<%@ include file="/back-end/header_sidebar.jsp" %>
<%
    RoomService roomSvc = context.getBean(RoomService.class);
    List<RoomVO> list = roomSvc.getAllIncZ();
    pageContext.setAttribute("list", list);
%>
<div class="tab-content" id="v-pills-tabContent" style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade" id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0">
        <img src="image/welcome.gif" style="width: 95%" alt=""/>
    </div>
    <!-- ========================================= 員工管理 ========================================= -->
    <div class="tab-pane fade p-3 container" id="v-pills-employee"
         role="tabpanel" aria-labelledby="v-pills-employee-tab" tabindex="0"
         style="display: flex; flex-direction: column; align-items: center">
    </div>
    <!-- ========================================= 會員管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-member" role="tabpanel"
         aria-labelledby="v-pills-member-tab" tabindex="0"></div>
    <!-- ========================================= 商城管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-shop" role="tabpanel"
         aria-labelledby="v-pills-shop-tab" tabindex="0"
         style="border: 2px solid brown">shop section
    </div>
    <!-- ========================================= 教練管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-coach" role="tabpanel"
         aria-labelledby="v-pills-coach-tab" tabindex="0"
         style="border: 2px solid rgb(214, 122, 122)">coach section
    </div>
    <!-- ========================================= 課程管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-class" role="tabpanel"
         aria-labelledby="v-pills-class-tab" tabindex="0"
         style="border: 2px solid purple">class section
    </div>
    <!-- ========================================= 場地管理 ========================================= -->
    <div class="tab-pane fade show active p-3 container" id="v-pills-room"
         role="tabpanel" aria-labelledby="v-pills-room-tab" tabindex="0"
         style="display: flex; flex-direction: column; align-items: center">

        <h1 class="display-6">場地資料查詢</h1>
        <div class="d-inline">
            <a class="col-2 btn btn-secondary" style="display:inline"
               href="<%=request.getContextPath()%>/back-end/room/room_page.jsp"
               role="button">回場地首頁</a>
        </div>

        <form class="row g-3" METHOD="post" ACTION="room.do"
              style="margin-top: 10px">
            <div class="input-group flex-nowrap">
                <span class="input-group-text" id="addon-wrapping">場地編號查詢</span> <input
                    type="text" class="form-control" name="roomID"
                    aria-label="Username" aria-describedby="addon-wrapping">
                <div class="col-auto">
                    <input type="hidden" name="action" value="getOne_For_Display"/> <input
                        class="btn btn-outline-warning" type="submit" value="查詢"/>
                </div>
            </div>
            <!-- 				<div class="col-auto"> -->
            <!-- 					<label for="searchName" class="col-form-label">依場地編號查詢</label> -->
            <!-- 				</div> -->
            <!-- 				<div class="col-auto"> -->
            <!-- 					<input type="text" id="searchName" class="form-control" -->
            <!-- 						name="roomID" /> -->
            <!-- 				</div> -->
            <!-- 				<div class="col-auto"> -->
            <!-- 					<input type="hidden" name="action" value="getOne_For_Display" /> <input -->
            <!-- 						class="btn btn-outline-primary" type="submit" value="查詢" /> -->
            <!-- 				</div> -->
        </form>

        <div class="row" style="height: 15px; padding-left: 50px">
            <c:if test="${not empty errorMsgs}">
                <c:forEach var="message" items="${errorMsgs}">
                    <div style="color: red">${message}</div>
                </c:forEach>
            </c:if>
        </div>

        <FORM METHOD="post" ACTION="room.do" style="margin-top: 20px;">
            <div class="col-auto" style="display: inline;">
                <label for="chooseName" class="col-form-label">選擇場地編號</label>
            </div>
            <select size="1" name="roomID" id="chooseName">
                <c:forEach var="roomVO" items="${list}">
                <option value="${roomVO.roomID}">${roomVO.roomID}
                    </c:forEach>
            </select> <input type="hidden" name="action" value="getOne_For_Display">
            <div class="col-auto" style="display: inline; margin-left: 10px">
                <input type="hidden" name="action" value="getOne_For_Display"/> <input
                    class="btn btn-outline-warning" type="submit" value="查詢"/>
            </div>
        </FORM>
        <a href='listAllRoom.jsp' class="btn btn-outline-secondary"
           style="margin-top: 30px">查看所有場地</a> <br> <a
            class="btn btn-outline-secondary" href="addRoom.jsp" role="button">新增場地資料</a>


    </div>
    <!-- ========================================= 論壇管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-article" role="tabpanel"
         aria-labelledby="v-pills-article-tab" tabindex="0"></div>
    <!-- ========================================= 客服管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-service" role="tabpanel"
         aria-labelledby="v-pills-service-tab" tabindex="0"></div>
</div>
</main>
<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/sidebars.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {
        $("#toRoom").addClass("active");
        $("#toRoom").attr("aria-selected", "true");
    })
</script>
</body>
</html>