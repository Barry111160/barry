<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.musclebeach.room.model.RoomVO" %>
<%
    RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
%>

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
<div class="tab-content" id="v-pills-tabContent" style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade" id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0">
        <img src="../image/welcome.gif" style="width: 95%" alt=""/>
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

        <div>
            <div class="d-inline display-6" style="margin-right: 644px;">修改場地資料</div>
            <div class="d-inline">
                <a class="col-2 btn btn-warning"
                   href="<%=request.getContextPath()%>/back-end/room/select_page.jsp" role="button">回查詢頁</a>
            </div>
        </div>

        <form class="g-3" method="post" action="room.do" name="form1"
              enctype="multipart/form-data">
            <div class="container" style="width: 1000px">
                <div class="row">
                    <div class="col-md-2">
                        <label for="inputId" class="form-label">場地編號</label> <input
                            type="text" class="form-control" id="inputId"
                            value="<%=roomVO.getRoomID()%>" disabled readonly/>
                    </div>
                    <div class="col-md-3">
                        <label for="inputName" class="form-label">場地姓名</label> <input
                            type="text" class="form-control" id="inputName" name="roomName"
                            value="<%=roomVO.getRoomName()%>"/>
                    </div>
                    <div class="col-md-4">
                        <label for="inputPrice" class="form-label">場地價格</label> <input
                            type="text" class="form-control" id="inputPrice"
                            name="roomPrice" value="<%=roomVO.getRoomPrice()%>"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 col-md-8">
                        <label for="inputAddress" class="form-label">場地地址</label> <input
                            type="text" class="form-control" id="inputAddress"
                            name="roomAddress" value="<%=roomVO.getRoomAddress()%>"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 col-md-8">
                        <label for="inputContent" class="form-label">場地簡介</label> <input
                            type="text" class="form-control" id="inputContent"
                            name="roomContent" value="<%=roomVO.getRoomContent()%>"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3 mt-1">
                        <label for="inputAccount" class="form-label">場地狀態</label> <select
                            size="1" name="roomStatus">
                        <option value="0" ${(roomVO.roomStatus==0)?'selected':'' }>下架

                        <option value="1" ${(roomVO.roomStatus==1)?'selected':'' }>上架

                    </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-md-8">
                        <input type="file" class="form-control" id="inputImg"
                               name="roomImg">
                    </div>
                </div>
                <div class="row" style="margin-top: 20px">
                    <div class="col-12">
                        <input type="hidden" name="roomID"
                               value="<%=roomVO.getRoomID()%>"/>

                        <c:if test="${not empty errorMsgs}">
                            <c:forEach var="message" items="${errorMsgs}">
                                <div style="color: red">${message}</div>
                            </c:forEach>
                        </c:if>
                        <input type="hidden" name="action" value="update"/> <input
                            type="submit" class="btn btn-outline-danger" value="提交"
                            style="margin-top: 10px"/>

                    </div>
                </div>
            </div>
        </form>

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