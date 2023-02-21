<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    MemVO memVO = (MemVO) request.getAttribute("MemVO");
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>會員資料修改 - updateMem.jsp</title>

    <link href="css/index.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"/>
    <link href="css/flaticon.css" rel="stylesheet"/>
    <style type="text/css"></style>

</head>
<body>
<%@include file="../header_sidebar.jsp" %>
<div class="tab-content" id="v-pills-tabContent">
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade" id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0">
        <img src="image/welcome.gif" style="width: 96%" alt=""/>
    </div>
    <!-- ========================================= 員工管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-employee" role="tabpanel"
         aria-labelledby="v-pills-employee-tab" tabindex="0">
    </div>
    <!-- ========================================= 會員管理 ========================================= -->
    <div class="tab-pane fade show active" id="v-pills-member"
         role="tabpanel" aria-labelledby="v-pills-member-tab" tabindex="0"
         style="display: flex; flex-direction: column; align-items: center">
        <h1 class="display-6" style="margin-top: 10px">會員資料修改</h1>

        <form class="g-3" method="post" action="mem.do" name="form1">
            <div class="container" style="width: 1000px">
                <div class="row">
                    <div class="col-md-2">
                        <label for="inputId" class="form-label">會員編號</label> <input
                            type="text" class="form-control" id="inputId"
                            value="<%=memVO.getMemID()%>" disabled readonly/>
                    </div>
                    <div class="col-md-3">
                        <label for="inputName" class="form-label">會員姓名</label> <input
                            type="text" class="form-control" id="inputName" name="memName"
                            value="<%=memVO.getMemName()%>"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label for="inputAccount" class="form-label">帳號</label> <input
                            type="text" class="form-control" id="inputAccount"
                            value="<%=memVO.getAccount()%>" disabled readonly/>
                    </div>
                    <div class="col-md-3">
                        <label for="inputPassword" class="form-label">密碼</label> <input
                            type="password" class="form-control" id="inputPassword"
                            name="password" value="<%=memVO.getPassword()%>"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6 col-md-3">
                        <label for="inputTel" class="form-label">電話</label> <input
                            type="text" class="form-control" id="inputTel" name="memPhone"
                            value="<%=memVO.getMemPhone()%>"/>
                    </div>
                    <div class="col-6 col-md-3">
                        <label for="inputBirth" class="form-label">生日</label> <input
                            type="text" class="form-control" id="inputBirth"
                            value="<%=memVO.getMemBirthday()%>" disabled readonly/>
                    </div>
                </div>
                <div class="col-12 col-md-6">
                    <label for="inputEmail" class="form-label">信箱</label> <input
                        type="email" class="form-control" id="inputEmail" name="memMail"
                        value="<%=memVO.getMemMail()%>"/>
                </div>
                <div class="col-12 col-md-8">
                    <label for="inputAddress" class="form-label">通訊地址</label> <input
                        type="text" class="form-control" id="inputAddress"
                        name="memAddress" value="<%=memVO.getMemAddress()%>"/>
                </div>
                <div class="row" style="margin-top: 20px">
                    <div class="col-12">
                        <input type="hidden" name="memID" value="<%=memVO.getMemID()%>"/>
                        <input type="hidden" name="memAccount"
                               value="<%=memVO.getAccount()%>"/> <input type="hidden"
                                                                        name="memBirthday"
                                                                        value="<%=memVO.getMemBirthday()%>"/> <input
                            type="hidden" name="memStatus" value="<%=memVO.getMemStatus()%>"/>
                        <input type="hidden" name="memAccess"
                               value="<%=memVO.getMemAccess()%>"/> <input type="hidden"
                                                                          name="membership"
                                                                          value="<%=memVO.getMembership()%>"/>
                        <c:if test="${not empty errorMsgs}">
                            <c:forEach var="message" items="${errorMsgs}">
                                <div style="color: red">${message}</div>
                            </c:forEach>
                        </c:if>
                        <input type="hidden" name="action" value="update"/> <input
                            type="submit" class="btn btn-primary" value="Submit"
                            style="margin-top: 10px"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!-- ========================================= 商城管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-shop" role="tabpanel"
         aria-labelledby="v-pills-shop-tab" tabindex="0"
         style="border: 2px solid brown">shop section
    </div>
    <!-- ========================================= 教練管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-coach" role="tabpanel"
         aria-labelledby="v-pills-coach-tab" tabindex="0"
         style="border: 2px solid rgb(214, 122, 122)">coach
        section
    </div>
    <!-- ========================================= 課程管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-class" role="tabpanel"
         aria-labelledby="v-pills-class-tab" tabindex="0"
         style="border: 2px solid purple">class section
    </div>
    <!-- ========================================= 場地管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-room" role="tabpanel"
         aria-labelledby="v-pills-room-tab" tabindex="0"
         style="border: 2px solid greenyellow">room section
    </div>
    <!-- ========================================= 論壇管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-article" role="tabpanel"
         aria-labelledby="v-pills-article-tab" tabindex="0"
         style="border: 2px solid rgb(253, 250, 66)">article
    </div>
    <!-- ========================================= 客服管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-service" role="tabpanel"
         aria-labelledby="v-pills-service-tab" tabindex="0"
         style="border: 2px solid rgb(15, 198, 42)">service
        section
    </div>
</div>
</main>
</body>
</html>