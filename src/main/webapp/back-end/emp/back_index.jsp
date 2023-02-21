<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Muscle Beach: 後台管理系統</title>
<link href="css/index.css" rel="stylesheet" />
<link href="css/flaticon.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
<style type="text/css"></style>
</head>
<body>
	<!-- ======================================== header 開始 ======================================== -->
<%@ include file="/back-end/header_sidebar.jsp"%>
		<div class="tab-content" id="v-pills-tabContent">
			<!-- ============================================ 首頁 ============================================ -->
			<div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
				aria-labelledby="v-pills-home-tab" tabindex="0"
				style="height: calc(100vh - var(- -header-height)); overflow: hidden;">
				<img src="images/welcome.gif" style="width: 95%; position: relative; left: 30px" alt="" />
			</div>
			<!-- ========================================= 員工管理 ========================================= -->
			<div class="tab-pane fade" id="v-pills-employee" role="tabpanel"
				aria-labelledby="v-pills-employee-tab" tabindex="0"></div>
			<!-- ========================================= 會員管理 ========================================= -->
			<div class="tab-pane fade p-3 container"
				id="v-pills-member" role="tabpanel"
				aria-labelledby="v-pills-member-tab" tabindex="0"
				style="display: flex; flex-direction: column; align-items: center;">
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
		</div>
	</main>
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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	$(function() {
		$("#toHome").addClass("active");
		$("#toHome").attr("aria-selected", "true");
	})
	</script>
</body>
</html>