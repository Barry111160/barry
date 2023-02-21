<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
Integer roomID = Integer.valueOf(request.getParameter("roomID"));
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>場地管理</title>
<link href="css/index.css" rel="stylesheet" />
<link href="css/flaticon.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="css/daterangepicker.css" type="text/css">
<style type="text/css">
div.d-inline {
	margin-top: 50px;
}

.picker {
	background-color: rgba(17, 15, 153, 0.1);
}
</style>

</head>
<body>
	<%@ include file="/back-end/header_sidebar.jsp"%>
	<div class="tab-content" id="v-pills-tabContent"
		style="width: 100%; height: calc(100vh - 70px);">
		<!-- ============================================ 首頁 ============================================ -->
		<div class="tab-pane fade" id="v-pills-home" role="tabpanel"
			aria-labelledby="v-pills-home-tab" tabindex="0">
			<img src="../image/welcome.gif" style="width: 95%" alt="" />
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
		<div class="tab-pane fade show active p-3 container" id="v-pills-room"
			role="tabpanel" aria-labelledby="v-pills-room-tab" tabindex="0"
			style="display: flex; flex-direction: column; align-items: center">

			<h1 class="display-6">場地時段新增</h1>

			<form class="g-3" method="post" action="roomTime.do" name="form1">
				<div class="container" style="width: 1000px">
					<div class="row">
						<div class="input-group">
							<div class="input-group flex-nowrap">
								<span class="input-group-text" id="addon-wrapping">起始時間</span> <input
									type="text" class="form-control col-4" id="from"
									name="start_time" aria-label="Username"
									aria-describedby="addon-wrapping" autocomplete="off">
							</div>
							<div class="input-group flex-nowrap">
								<span class="input-group-text" id="addon-wrapping">結束時間</span> <input
									type="text" class="form-control col-4 " id="to" name="end_time"
									aria-label="Username" aria-describedby="addon-wrapping"
									autocomplete="off">
							</div>
							<!-- 							<input class="form-control col-1" style="font-size: 14px;" -->
							<!-- 								id="from" name="start_time" placeholder="起始時間" -->
							<!-- 								autocomplete="off"> <input class="form-control col-1" -->
							<!-- 								style="font-size: 14px;" id="to" name="end_time" -->
							<!-- 								placeholder="結束時間" autocomplete="off"> -->
						</div>
					</div>
					<div class="d-inline">

						<input type="hidden" name="roomID" value="<%=roomID%>" /> <input
							type="hidden" name="action" value="insert" /> <input
							type="submit" class="btn btn-outline-danger" value="新增時段"
							style="" /> <a class="col-2 btn btn-outline-primary"
							style="display: inline; margin-top: 30px"
							href="<%=request.getContextPath()%>/back-end/roomTime/listAllRoom.jsp"
							role="button">返回</a>
					</div>
				</div>

			</form>
		</div>
		<!-- ========================================= 論壇管理 ========================================= -->
		<div class="tab-pane fade" id="v-pills-article" role="tabpanel"
			aria-labelledby="v-pills-article-tab" tabindex="0"
			style="border: 2px solid rgb(253, 250, 66)">article</div>
		<!-- ========================================= 客服管理 ========================================= -->
		<div class="tab-pane fade" id="v-pills-service" role="tabpanel"
			aria-labelledby="v-pills-service-tab" tabindex="0"
			style="border: 2px solid rgb(15, 198, 42)">service section</div>
	</div>
	</main>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="js/moment.min.js"></script>
	<script src="js/daterangepicker.js"></script>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
	<script
		src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script>
		$.datetimepicker.setLocale('zh');
		$('#from').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : false, //timepicker:true,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d', //format:'Y-m-d H:i:s',
			value : new Date()
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	            '2017/07/10',  // 起始日
		//minDate:               '-1970-01-01', // 去除今日(不含)之前
		//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
		});
		$('#to').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : false, //timepicker:true,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d', //format:'Y-m-d H:i:s',
			value : new Date()
		});
	</script>
	<script>
		// 		if ($('#from, #to').length) {
		// 			// check if element is available to bind ITS ONLY ON HOMEPAGE
		// 			var currentDate = moment().format("YYYY-MM-DD");

		// 			$('#from, #to').daterangepicker({
		// 				locale : {
		// 					format : 'YYYY-MM-DD'
		// 				},

		// 				"alwaysShowCalendars" : true,
		// 				"minDate" : currentDate,
		// 				autoApply : true,
		// 				autoUpdateInput : false,

		// 			}, function(start, end, label) {
		// 				// console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
		// 				// Lets update the fields manually this event fires on selection of range
		// 				var selectedStartDate = start.format('YYYY-MM-DD'); // selected start
		// 				var selectedEndDate = end.format('YYYY-MM-DD'); // selected end

		// 				$checkinInput = $('#from');
		// 				$checkoutInput = $('#to');

		// 				// Updating Fields with selected dates
		// 				$checkinInput.val(selectedStartDate);
		// 				$checkoutInput.val(selectedEndDate);

		// 				// Setting the Selection of dates on calender on CHECKOUT FIELD (To get this it must be binded by Ids not Calss)
		// 				var checkOutPicker = $checkoutInput.data('daterangepicker');
		// 				checkOutPicker.setStartDate(selectedStartDate);
		// 				checkOutPicker.setEndDate(selectedEndDate);

		// 				// Setting the Selection of dates on calender on CHECKIN FIELD (To get this it must be binded by Ids not Calss)
		// 				var checkInPicker = $checkinInput.data('daterangepicker');
		// 				checkInPicker.setStartDate(selectedStartDate);
		// 				checkInPicker.setEndDate(selectedEndDate);

		// 			});

		// 		} // End Daterange Picker
	</script>
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