<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>客服管理</title>
<link href="css/index.css" rel="stylesheet" />
<link href="css/flaticon.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="canonical"
	href="https://getbootstrap.com/doc	s/5.3/examples/headers/" />
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.3/examples/sidebars/" />
<style type="text/css"></style>
<style>
table#table-1 {
	width: 996px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

h1 {
	font-size: 150px;
	color: red;
}

h2 {
	color: blue;
}
</style>

</head>
<body>
	<%@ include file="/back-end/header_sidebar.jsp"%>
	<div class="tab-content" id="v-pills-tabContent"
		style="width: 100%; height: calc(100vh - 70px);">
		<!-- ============================================ 首頁 ============================================ -->
		<div class="tab-pane fade show active" id="v-pills-home"
			role="tabpanel" aria-labelledby="v-pills-home-tab" tabindex="0">
			<img src="/image/welcome.gif" style="width: 96%" alt="" />
		</div>
		<!-- ========================================= 員工管理 ========================================= -->
		<div class="tab-pane fade" id="v-pills-employee" role="tabpanel"
			aria-labelledby="v-pills-employee-tab" tabindex="0"
			style="border: 2px solid green">employee section</div>
		<!-- ========================================= 會員管理 ========================================= -->
		<div class="tab-pane fade" id="v-pills-member" role="tabpanel"
			aria-labelledby="v-pills-member-tab" tabindex="0">member
			section</div>
		<!-- ========================================= 商城管理 ========================================= -->
		<div class="tab-pane fade" id="v-pills-shop" role="tabpanel"
			aria-labelledby="v-pills-shop-tab" tabindex="0"
			style="border: 2px solid brown">shop section</div>
		<!-- ========================================= 教練管理 ========================================= -->
		<div class="tab-pane fade" id="v-pills-coach" role="tabpanel"
			aria-labelledby="v-pills-coach-tab" tabindex="0"
			style="border: 2px solid rgb(214, 122, 122)">coach section</div>
		<!-- ========================================= 課程管理 ========================================= -->
		<div class="tab-pane fade" id="v-pills-class" role="tabpanel"
			aria-labelledby="v-pills-class-tab" tabindex="0"
			style="border: 2px solid purple">class section</div>
		<!-- ========================================= 場地管理 ========================================= -->
		<div class="tab-pane fade" id="v-pills-room" role="tabpanel"
			aria-labelledby="v-pills-room-tab" tabindex="0"
			style="border: 2px solid greenyellow">room section</div>
		<!-- ========================================= 論壇管理 ========================================= -->
		<div class="tab-pane fade" id="v-pills-article" role="tabpanel"
			aria-labelledby="v-pills-article-tab" tabindex="0"
			style="border: 2px solid rgb(253, 250, 66)">article</div>
		<!-- ========================================= 客服管理 ========================================= -->
		<div class="tab-pane fade show active" id="v-pills-service"
			role="tabpanel" aria-labelledby="v-pills-service-tab" tabindex="0">

			<h1>查無資料</h1>
			<h2>五秒後自動回上一頁</h2>
			<div style="text-align: center">
				<a id=a-1
					href="<%=request.getContextPath()%>/back-end/question/select_page.jsp"
					style="font-size: 64px; color: blue;"> 或是點擊此處跳轉</a>
			</div>
			<script>
				window.onload = function() {
					setTimeout("history.back()", 5000);
				}
			</script>
		</div>
	</div>
	</main>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="index/sidebars.js"></script>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script>
		$(function() {
			$("#toService").addClass("active");
			$("#toService").attr("aria-selected", "true");
		})
	</script>
</body>
</html>