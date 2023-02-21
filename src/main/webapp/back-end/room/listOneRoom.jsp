<%@ page import="com.musclebeach.room.model.RoomVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    RoomVO roomVO = (RoomVO) request.getAttribute("roomVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
            width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
<%@ include file="/back-end/header_sidebar.jsp" %>
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

        <div>
            <div class="d-inline display-6" style="margin-right: 664px;">查詢所有場地資料</div>
            <div class="d-inline">
                <a class="col-2 btn btn-warning"
                   href="<%=request.getContextPath()%>/back-end/room/select_page.jsp" role="button">回查詢頁</a>
            </div>
        </div>


        <table class="table">
            <thead>
            <tr>
                <th scope="col" class="col-1">編號</th>
                <th scope="col" class="col-1">名稱</th>
                <th scope="col" class="col-2">地址</th>
                <th scope="col" class="col-2">介紹</th>
                <th scope="col" class="col-1">價格</th>
                <th scope="col" class="col-1">狀態</th>
                <th scope="col" class="col-1">修改</th>
                <th scope="col" class="col-2">場地圖片</th>
            </tr>
            </thead>
            <tbody>
            <%-- 					<c:forEach var="roomVO" items="${list}"> --%>
            <tr>
                <th scope="row">${roomVO.roomID}</th>
                <td>${roomVO.roomName}</td>
                <td>${roomVO.roomAddress}</td>
                <td>${roomVO.roomContent}</td>
                <td>${roomVO.roomPrice}</td>
                <td>${(roomVO.roomStatus==0)?"下架":"上架"}</td>
                <td style="padding-top: 2px; padding-bottom: 2px">
                    <FORM id="form" METHOD="post"
                          ACTION="<%=request.getContextPath()%>/back-end/room/room.do"
                          style="margin-bottom: 0px;">
                        <input type="submit" value="修改" class="btn btn-outline-danger"
                               style="margin-top: 6px"> <input type="hidden"
                                                               name="roomID" value="${roomVO.roomID}"> <input
                            type="hidden" name="action" value="getOne_For_Update">
                    </FORM>
                </td>

                <td><img
                        src="${pageContext.request.contextPath }/back-end/room/room.do?action=getImg&roomID=${roomVO.roomID}">
                </td>
            </tr>
            <%-- 					</c:forEach> --%>
        </table>
    </div>
    <!-- ========================================= 論壇管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-article" role="tabpanel"
         aria-labelledby="v-pills-article-tab" tabindex="0"
         style="border: 2px solid rgb(253, 250, 66)">article
    </div>
    <!-- ========================================= 客服管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-service" role="tabpanel"
         aria-labelledby="v-pills-service-tab" tabindex="0"
         style="border: 2px solid rgb(15, 198, 42)">service section
    </div>
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