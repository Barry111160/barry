<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.emp.model.EmpService" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>員工管理</title>
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
<%
    List<EmpVO> list2 = empSvc.getAll();
    pageContext.setAttribute("list2", list2);
%>
<div class="tab-content" id="v-pills-tabContent" style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade" id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0"></div>
    <!-- ========================================= 員工管理 ========================================= -->
    <div class="tab-pane fade show active p-3 container"
         id="v-pills-employee" role="tabpanel"
         aria-labelledby="v-pills-employee-tab" tabindex="0"
         style="display: flex; flex-direction: column; align-items: center">
        <div>
            <div class="d-inline display-6" style="margin-right: 644px;">查詢所有員工資料</div>
            <div class="d-inline">
                <a class="col-2 btn btn-warning"
                   href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp" role="button">回查詢頁</a>
            </div>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col" class="col-1">員工編號</th>
                <th scope="col" class="col-1">員工姓名</th>
                <th scope="col" class="col-1">員工密碼</th>
                <th scope="col" class="col-1">雇用日期</th>
                <th scope="col" class="col-1">員工生日</th>
                <th scope="col" class="col-1">手機</th>
                <th scope="col" class="col-1">信箱</th>
                <th scope="col" class="col-1">員工狀態</th>
                <th scope="col" class="col-2">教練價格</th>
                <th scope="col" class="col-2">員工圖片</th>
                <th scope="col" class="col-1">修改</th>
                <th scope="col" class="col-1">刪除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="empVO" items="${list2}">
                <tr>
                    <th scope="row">${empVO.empID}</th>
                    <td>${empVO.empName}</td>
                    <td>${empVO.password}</td>
                    <td>${empVO.hiredate}</td>
                    <td>${empVO.empBirthday}</td>
                    <td>${empVO.empPhone}</td>
                    <td>${empVO.empMail}</td>
                    <td>${(empVO.empStatus==0)?"離職":"在職"}</td>
                    <td>${empVO.coachPrice}</td>
                    <td><img
                            src="${pageContext.request.contextPath }/back-end/emp/emp.do?action=getImg&empID=${empVO.empID}">
                    </td>
                    <td>
                        <FORM id="form" METHOD="post"
                              ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
                              style="margin-bottom: 0px;">
                            <input type="submit" value="修改" class="btn btn-outline-success"> <input
                                type="hidden" name="empID" value="${empVO.empID}"> <input
                                type="hidden" name="action" value="getOne_For_Update">
                        </FORM>
                    </td>
                    <td>
                        <FORM METHOD="post"
                              ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
                              style="margin-bottom: 0px;">
                            <input type="submit" value="刪除" class="btn btn-outline-danger"> <input
                                type="hidden" name="empID" value="${empVO.empID}"> <input
                                type="hidden" name="action" value="delete">
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- ========================================= 會員管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-member" role="tabpanel"
         aria-labelledby="v-pills-member-tab" tabindex="0"></div>
    <!-- ========================================= 商城管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-shop" role="tabpanel"
         aria-labelledby="v-pills-shop-tab" tabindex="0"></div>
    <!-- ========================================= 教練管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-coach" role="tabpanel"
         aria-labelledby="v-pills-coach-tab" tabindex="0"></div>
    <!-- ========================================= 課程管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-class" role="tabpanel"
         aria-labelledby="v-pills-class-tab" tabindex="0"></div>
    <!-- ========================================= 場地管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-room" role="tabpanel"
         aria-labelledby="v-pills-room-tab" tabindex="0"></div>
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
        $("#toEmployee").addClass("active");
        $("#toEmployee").attr("aria-selected", "true");
    })
</script>
</body>

</html>