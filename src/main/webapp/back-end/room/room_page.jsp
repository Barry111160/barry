<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>場地管理</title>
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
				aria-labelledby="v-pills-home-tab" tabindex="0">
			</div>
			<!-- ========================================= 員工管理 ========================================= -->
			<div class="tab-pane fade" id="v-pills-employee" role="tabpanel"
				aria-labelledby="v-pills-employee-tab" tabindex="0"></div>
			<!-- ========================================= 會員管理 ========================================= -->
			<div class="tab-pane fade p-3 container" id="v-pills-member"
				role="tabpanel" aria-labelledby="v-pills-member-tab" tabindex="0"
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
			<div class="tab-pane fade show active container"
				id="v-pills-room" role="tabpanel" aria-labelledby="v-pills-room-tab"
				tabindex="0"
				style="height: calc(100vh - var(- -header-height)); overflow: hidden; width: 100%; padding: 30px 0 0 50px;">
				<div class="row mt-3">
					<div class="col-sm-4">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">
									<i class="bi bi-building-fill"></i> About Rooms
								</h5>
								<p class="card-text">
								<ul>
									<li>新增 / 上下架</li>
									<li>場地內容及資訊</li>
									<li>租借價格</li>
								</ul>
								</p>
								<a href="<%=request.getContextPath()%>/back-end/room/select_page.jsp" class="btn btn-outline-warning"><i
									class="bi bi-arrow-return-right"></i> 進入地點管理</a>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">
									<i class="bi bi-calendar-day-fill"></i> About Schedule
								</h5>
								<p class="card-text">
								<ul>
									<li>新增 / 修改</li>
									<li>可預約日期：僅受理三天後的預約</li>
									<li>可預約時段：上午 / 中午 / 下午</li>
								</ul>
								</p>
								<a href="<%=request.getContextPath()%>/back-end/roomTime/listAllRoom.jsp" class="btn btn-outline-warning"><i
									class="bi bi-arrow-return-right"></i> 進入時段管理</a>
							</div>
						</div>
					</div>
				</div>
				<div class="row" style="margin-top: 43px;">
					<div class="col-sm-8">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">
									<i class="bi bi-clipboard2-fill"></i> About Orders
								</h5>
								<p class="card-text">
								<ul>
									<li>確認訂單：請同仁盡快進行處理</li>
									<li>取消受理：受理同仁需通知會員取消成功，可退款者(三日前取消)於七個工作天刷退</li>
									<li>訂單查詢：可依訂單編號 / 員工編號 / 會員編號</li>
								</ul>
								</p>
								<a href="<%=request.getContextPath()%>/back-end/room/room_order_pending.jsp" class="btn btn-outline-warning"><i
									class="bi bi-arrow-return-right"></i> 進入訂單管理</a>
							</div>
						</div>
					</div>
				</div>
				<div class="position-relative"
					style="left: 690px; top: -365px; width: 293px; height: 414px;">
					<img src="images/pic.png" alt=""
						style="width: 100%; height: 100%" />
				</div>
			</div>
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
	$(function() {
		$("#toRoom").addClass("active");
		$("#toRoom").attr("aria-selected", "true");
	})
	</script>
</body>
</html>