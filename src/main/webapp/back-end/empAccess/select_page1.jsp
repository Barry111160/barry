<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="BIG5">
    <title>員工權限管理</title>
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
<%@ include file="../header_sidebar.jsp" %>
<div class="tab-content" id="v-pills-tabContent"
     style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade" id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0"></div>
    <!-- ========================================= 員工管理 ========================================= -->
    <div class="tab-pane fade show active p-3 container"
         id="v-pills-employee" role="tabpanel"
         aria-labelledby="v-pills-employee-tab" tabindex="0"
         style="display: flex; flex-direction: column; align-items: center">

        <div>
            <div>
                <div>
                    <h2>權限查詢首頁</h2>
                    <div class="d-inline">
                        <a class="col-2 btn btn-warning"
                           style="display: inline; align-items: center;"
                           href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp"
                           role="button">回員工查詢頁</a>
                    </div>
                    <hr>

                    <h3>權限查詢:</h3>


                    <form method="post"
                          action="<%=request.getContextPath()%>/back-end/empAccess/empAccess.do"
                          style="margin-top: 20px">

                        <div class="input-group flex-nowrap">
                            <span class="input-group-text" id="addon-wrapping">員工姓名查詢</span>
                            <input type="text" class="form-control" name="empName"
                                   aria-label="Username" aria-describedby="addon-wrapping">
                            <div class="col-auto">
                                <input type="hidden" name="action" value="getName_For_Display"/> <input
                                    class="btn btn-outline-warning" type="submit" value="查詢"/>
                            </div>
                        </div>
                        <!-- 							<div class="col-auto" style="display: inline"> -->
                        <!-- 								<label for="searchEmp" class="col-form-label">依員工查詢</label> -->
                        <!-- 							</div> -->
                        <!-- 							<div class="col-auto" style="display: inline; margin-left: 10px"> -->
                        <!-- 								<select size="1" name="empName" id="searchEmp"> -->

                        <%-- 									<c:forEach var="empVO" items="${empSvc.all}"> --%>
                        <%-- 										<option value="${empVO.empName}">${empVO.empName} --%>
                        <%-- 									</c:forEach> --%>
                        <!-- 								</select> -->
                        <!-- 							</div> -->
                        <!-- 							<div class="col-auto" style="display: inline; margin-left: 10px"> -->
                        <%-- 								<input type="submit" value="查詢" class="btn btn-outline-primary">&nbsp;<span>${errorMsgs.empName}</span> --%>
                        <!-- 								<input type="hidden" name="action" value="getName_For_Display"> -->
                        <!-- 							</div> -->

                    </form>

                    <form method="post"
                          action="<%=request.getContextPath()%>/back-end/empAccess/empAccess.do"
                          style="margin-top: 20px">
                        <div class="col-auto" style="display: inline">
                            <label for="searchPhone" class="col-form-label">依權限查詢</label>
                        </div>
                        <div class="col-auto" style="display: inline; margin-left: 10px">
                            <select size="1" name="fID">
                                <c:forEach var="accessFunctionVO"
                                           items="${accessFunctionSvc.all}">
                                <option value="${accessFunctionVO.fID}">${accessFunctionVO.fName}
                                    </c:forEach>
                            </select>
                        </div>
                        <div class="col-auto" style="display: inline; margin-left: 10px">
                            <input type="submit" value="查詢"
                                   class="btn btn-outline-primary">&nbsp;<span>${errorMsgs.fID}</span>
                            <input type="hidden" name="action"
                                   value="getFunction_For_Display">
                        </div>
                    </form>
                    <br> <a class="btn btn-danger"
                            href="<%=request.getContextPath()%>/back-end/empAccess/listAllAuthority.jsp"
                            role="button">全體員工權限</a>


                </div>
            </div>
        </div>


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
        src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>
<script
        src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/sidebars.js"></script>
<script>
    $(function () {
        $("#toEmployee").addClass("active");
        $("#toEmployee").attr("aria-selected", "true");
    })
</script>
</body>
</html>
