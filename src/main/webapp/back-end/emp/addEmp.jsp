<%@ page import="com.musclebeach.emp.model.EmpVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>
<!-- line 100 -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>員工管理</title>
    <link href="css/index.css" rel="stylesheet"/>
    <link href="css/flaticon.css" rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
            rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"/>
    <style type="text/css"></style>
</head>
<body>
<%@ include file="/back-end/header_sidebar.jsp" %>
<div class="tab-content" id="v-pills-tabContent"
     style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade" id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0"></div>
    <!-- ========================================= 員工管理 ========================================= -->
    <div class="tab-pane fade show active" id="v-pills-employee"
         role="tabpanel" aria-labelledby="v-pills-employee-tab" tabindex="0">
        <h1 class="display-6" style="margin-top: 10px; margin-left: 30px">新增員工資料</h1>
        <div class="d-inline">
            <a class="col-2 btn btn-warning"
               style="margin-bottom: 10px; margin-left: 30px"
               href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp"
               role="button">回查詢頁</a>
        </div>

        <form class="g-3" method="post" action="emp.do" name="form1"
              enctype="multipart/form-data">
            <div class="container" style="width: 1000px">
                <div class="row">
                    <div class="input-group flex-nowrap col-md-3">
                        <span class="input-group-text" id="addon-wrapping">員工姓名</span> <input
                            type="text" class="form-control" name="empName"
                            aria-label="Username" aria-describedby="addon-wrapping"
                            value="<%=(empVO == null) ? "XDD" : empVO.getEmpName()%>">
                    </div>

<%--                    <div class="col-md-3">--%>
<%--                        <label for="inputName" class="form-label">員工姓名</label> <input--%>
<%--                            type="text" class="form-control" id="inputName" name="empName"--%>
<%--                            value="<%=(empVO == null) ? "XDD" : empVO.getEmpName()%>"/>--%>
<%--                    </div>--%>

                     <div class="input-group flex-nowrap col-md-3">
                        <span class="input-group-text" id="addon-wrapping">員工信箱</span> <input
                            type="text" class="form-control" name="empMail"
                            aria-label="Username" aria-describedby="addon-wrapping"
                            value="<%=(empVO == null) ? "123@gmail.com" : empVO.getEmpMail()%>">
                    </div>
<%--                    <div class="col-md-3">--%>
<%--                        <label for="inputEmail" class="form-label">信箱</label> <input--%>
<%--                            type="email" class="form-control" id="inputEmail" name="empMail"--%>
<%--                            value="<%=(empVO == null) ? "123@gmail.com" : empVO.getEmpMail()%>"/>--%>
<%--                    </div>--%>
                </div>
                <div class="row">
                    <div class="input-group flex-nowrap col-md-3">
                        <span class="input-group-text" id="addon-wrapping">員工密碼</span> <input
                            type="text" class="form-control" name="password"
                            aria-label="Username" aria-describedby="addon-wrapping"
                            value="<%=(empVO == null) ? "123456" : empVO.getPassword()%>">
                    </div>
<%--                    <div class="col-md-3">--%>
<%--                        <label for="inputPassword" class="form-label">密碼</label> <input--%>
<%--                            type="password" class="form-control" id="inputPassword"--%>
<%--                            name="password"--%>
<%--                            value="<%=(empVO == null) ? "123456" : empVO.getPassword()%>"/>--%>
<%--                    </div>--%>
                    <div class="input-group flex-nowrap col-md-3">
                        <span class="input-group-text" id="addon-wrapping">員工電話</span> <input
                            type="text" class="form-control" name="empPhone"
                            aria-label="Username" aria-describedby="addon-wrapping"
                            value="<%=(empVO == null) ? "0900000000" : empVO.getEmpPhone()%>">
                    </div>
<%--                    <div class="col-6 col-md-3">--%>
<%--                        <label for="inputTel" class="form-label">電話</label> <input--%>
<%--                            type="text" class="form-control" id="inputTel" name="empPhone"--%>
<%--                            value="<%=(empVO == null) ? "0900000000" : empVO.getEmpPhone()%>"/>--%>
<%--                    </div>--%>
                </div>
                <div class="row mb-2 mt-1">
<%--                    <div class="input-group flex-nowrap col-md-3">--%>
<%--                        <span class="input-group-text" id="f_date1">雇用日期</span> <input--%>
<%--                            type="text" class="form-control" name="hiredate"--%>
<%--                            aria-label="Username" aria-describedby="addon-wrapping">--%>
<%--                    </div>--%>
                    <div class="col-md-3">
                        <label for="f_date1" class="form-label">雇用日期</label> <input
                            type="text" class="form-control" id="f_date1" name="hiredate"/>
                    </div>
<%--                    <div class="input-group flex-nowrap col-md-3">--%>
<%--                        <span class="input-group-text" id="f_date2">員工生日</span> <input--%>
<%--                            type="text" class="form-control" name="empBirthday"--%>
<%--                            aria-label="Username" aria-describedby="addon-wrapping">--%>
<%--                    </div>--%>
                    <div class="col-6 col-md-3">
                        <label for="f_date2" class="form-label">員工生日</label> <input
                            type="text" class="form-control" id="f_date2" name="empBirthday"/>
                    </div>
                </div>
                <div class="row">
                    <div class="input-group flex-nowrap col-md-3">
                        <span class="input-group-text" id="addon-wrapping">教練獎金</span> <input
                            type="text" class="form-control" name="coachPrice"
                            aria-label="Username" aria-describedby="addon-wrapping"
                            value="<%=(empVO == null) ? "0" : empVO.getCoachPrice()%>">
                    </div>
<%--                    <div class="col-md-2">--%>
<%--                        <label for="inputPrice" class="form-label">教練獎金</label> <input--%>
<%--                            type="text" class="form-control" id="inputPrice"--%>
<%--                            name="coachPrice"--%>
<%--                            value="<%=(empVO == null) ? "0" : empVO.getCoachPrice()%>"/>--%>
<%--                    </div>--%>
                    <div class="input-group flex-nowrap col-md-3">
                        <span class="input-group-text" id="addon-wrapping">教練介紹</span> <input
                            type="text" class="form-control" name="coachContent"
                            aria-label="Username" aria-describedby="addon-wrapping"
                            value="<%=(empVO == null) ? "無" : empVO.getCoachContent()%>">
                    </div>
<%--                    <div class="col-12 col-md-6">--%>
<%--                        <label for="inputContent" class="form-label">教練介紹</label> <input--%>
<%--                            type="text" class="form-control" id="inputContent"--%>
<%--                            name="coachContent"--%>
<%--                            value="<%=(empVO == null) ? "無" : empVO.getCoachContent()%>"/>--%>
<%--                    </div>--%>
                </div>
                <div class="row">
                    <div class="col-md-3 mt-1">
                        <label for="inputAccount" class="form-label">員工狀態</label> <select
                            size="1" name="empStatus">
                        <option value="0" ${(empVO.empStatus==0)?'selected':'' }>離職


                        <option value="1" ${(empVO.empStatus==1)?'selected':'' }>在職


                    </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-md-8">
                        <input type="file" class="form-control" id="inputImg"
                               name="empImg">
                    </div>
                </div>
                <div class="row" style="margin-top: 20px">
                    <div class="col-12">
                        <c:if test="${not empty errorMsgs}">
                            <c:forEach var="message" items="${errorMsgs}">
                                <div style="color: red">${message}</div>
                            </c:forEach>
                        </c:if>
                        <input type="hidden" name="action" value="insert"/> <input
                            type="submit" class="btn btn-primary" value="送出"
                            style="margin-top: 10px"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!-- ========================================= 會員管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-shop" role="tabpanel"
         aria-labelledby="v-pills-shop-tab" tabindex="0"></div>
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
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="http://localhost:8081/CGA105G5/back_end/js/sidebars.js"></script>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
    java.sql.Date hiredate = null;
    try {
        hiredate = empVO.getHiredate();
    } catch (Exception e) {
        hiredate = new java.sql.Date(System.currentTimeMillis());
    }
%>
<%
    java.sql.Date empBirthday = null;
    try {
        empBirthday = empVO.getEmpBirthday();
    } catch (Exception e) {
        empBirthday = new java.sql.Date(System.currentTimeMillis());
    }
%>
<link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css"/>
<script
        src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
<script
        src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
    .xdsoft_datetimepicker .xdsoft_datepicker {
        width: 300px; /* width:  300px; */
    }

    .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
        height: 151px; /* height:  151px; */
    }
</style>

<script>
    $.datetimepicker.setLocale('zh');
    $('#f_date1').datetimepicker({
        theme: '', //theme: 'dark',
        timepicker: false, //timepicker:true,
        step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
        format: 'Y-m-d', //format:'Y-m-d H:i:s',
        value: new Date()
        //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
        //startDate:	            '2017/07/10',  // 起始日
        //minDate:               '-1970-01-01', // 去除今日(不含)之前
        //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
    });
    $('#f_date2').datetimepicker({
        theme: '', //theme: 'dark',
        timepicker: false, //timepicker:true,
        step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
        format: 'Y-m-d', //format:'Y-m-d H:i:s',
        value: new Date()
    });
</script>
<script>
    $(function () {
        $("#toEmployee").addClass("active");
        $("#toEmployee").attr("aria-selected", "true");
    })
</script>
</html>