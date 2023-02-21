<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>會員管理</title>
<link href="css/index.css" rel="stylesheet" />
<link href="css/flaticon.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
<style type="text/css"></style>
</head>
<body>
	<%@include file="../header_sidebar.jsp" %>
		<div class="tab-content" id="v-pills-tabContent" style="width: 100%; height: calc(100vh - 70px);">
			<!-- ============================================ 首頁 ============================================ -->
			<div class="tab-pane fade" id="v-pills-home" role="tabpanel"
				aria-labelledby="v-pills-home-tab" tabindex="0"></div>
			<!-- ========================================= 員工管理 ========================================= -->
			<div class="tab-pane fade" id="v-pills-employee" role="tabpanel"
				aria-labelledby="v-pills-employee-tab" tabindex="0"></div>
			<!-- ========================================= 會員管理 ========================================= -->
			<div class="tab-pane fade show active p-3 container"
				id="v-pills-member" role="tabpanel"
				aria-labelledby="v-pills-member-tab" tabindex="0"
				style="display: flex; flex-direction: column; align-items: center; overflow: hidden">
				<h1 class="display-6" style="margin-top: 20px">會員管理及資料查詢</h1>
				
				<div class="row g-3 align-items-center" style="margin-top: 5px">
					<a class="col-3 btn btn-outline-warning" href="listAllMember.jsp"
						role="button" style="width:150px; text-align:center">查詢所有會員</a>
				</div>
				
				<form class="row g-3" METHOD="post"
					ACTION="mem.do" style="margin-top: 18px">
					<div class="col-auto">
						<label for="searchID" class="col-form-label">依會員編號查詢</label>
					</div>
					<div class="col-auto">
						<input type="text" id="searchID" class="form-control"
							name="memID" />
					</div>
					<div class="col-auto">
						<input type="hidden" name="action" value="search_By_ID" /> <input
							class="btn btn-outline-primary" type="submit" value="查詢" />
					</div>
				</form>
				
				<div class="row" style="height: 15px; padding-left: 50px">
				<c:if test="${not empty errorID}">
						<c:forEach var="message" items="${errorID}">
							<div style="color: red">${message}</div>
						</c:forEach>
				</c:if>
				</div>
				
				<form class="row g-3" METHOD="post"
					ACTION="mem.do" style="margin-top: 10px">
					<div class="col-auto">
						<label for="searchName" class="col-form-label">依會員姓名查詢</label>
					</div>
					<div class="col-auto">
						<input type="text" id="searchName" class="form-control"
							name="memName" />
					</div>
					<div class="col-auto">
						<input type="hidden" name="action" value="search_By_Name" /> <input
							class="btn btn-outline-primary" type="submit" value="查詢" />
					</div>
				</form>
				
				<div class="row" style="height: 15px; padding-left: 50px">
				<c:if test="${not empty errorName}">
						<c:forEach var="message" items="${errorName}">
							<div style="color: red">${message}</div>
						</c:forEach>
				</c:if>
				</div>
				
				<form class="row g-3 align-items-center" METHOD="post"
					ACTION="mem.do" style="margin-top: 10px">
					<div class="col-auto">
						<label for="searchPhone" class="col-form-label">依會員電話查詢</label>
					</div>
					<div class="col-auto">
						<input type="text" id="searchPhone" class="form-control"
							name="memPhone" />
					</div>
					<div class="col-auto">
						<input type="hidden" name="action" value="search_By_Phone" /> <input
							class="btn btn-outline-primary" type="submit" value="查詢" />
					</div>
				</form>
				
				<div class="row" style="height: 15px; padding-left: 50px">
				<c:if test="${not empty errorPhone}">
						<c:forEach var="message" items="${errorPhone}">
							<div style="color: red">${message}</div>
						</c:forEach>
				</c:if>
				</div>
				
				<form class="row g-3 align-items-center" METHOD="post"
					ACTION="mem.do" style="margin-top: 10px">
					<div class="col-auto">
						<label for="searchBirth" class="col-form-label">依會員生日查詢</label>
					</div>
					<div class="col-auto">
						<input type="text" id="searchBirth" class="form-control" name="memBirth" />
					</div>
					<div class="col-auto">
						<input type="hidden" name="action" value="search_By_Birth" /> <input
							class="btn btn-outline-primary" type="submit" value="查詢" />
					</div>
				</form>
				
				<div class="row" style="height: 15px; padding-left: 200px">
				<c:if test="${not empty errorBirth}">
						<c:forEach var="message" items="${errorBirth}">
							<div style="color: red">${message}</div>
						</c:forEach>
				</c:if>
				</div>
			</div>
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
			<div>
				<img alt="" src="images/member.jpg" style="position: absolute; top: 220px; left: 810px; height: 60%">
			</div>
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
	$(function() {
		$("#toMember").addClass("active");
		$("#toMember").attr("aria-selected", "true");
	})
	</script>
</body>
</html>