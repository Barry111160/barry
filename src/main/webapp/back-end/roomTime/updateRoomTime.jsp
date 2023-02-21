<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.musclebeach.room.model.RoomVO" %>


<%
RoomVO roomVO = (RoomVO) request.getAttribute("roomVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
Integer roomID = roomVO.getRoomID();
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
<style type="text/css"></style>
<link
	href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" />
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

			<h1 class="display-6">場地時段變更</h1>

			<FORM METHOD="post" ACTION="roomTime.do" name="form1">
				<table>

					<br>

					<tr>
						<td><h2>場地名稱:</h2></td>
						<td><h2><%=roomVO.getRoomName()%></h2></td>
					</tr>

				</table>
				<br>
				<div class="form-group w-90">
					<label> 維護日期 </label> <input type="text" class="form-control"
						id="inputBorrowDate" name="borrowDate" />
				</div>
				<div class="form-group w-90">
					<label> 維護時段 </label> <select class="form-control"
						id="selectBorrowTime" name="borrowTime">
						<option value="" selected disabled hidden>請選擇時段</option>
						<option value="0" id="morning" disabled>上午 （10:00 ~
							11:30）</option>
						<option value="1" id="noon" disabled>下午 （14:00 ~ 15:30）</option>
						<option value="2" id="night" disabled>晚上 （18:00 ~ 19:30）</option>
					</select>
				</div>
				<br> <input type="hidden" name="action" value="update">
				<input type="hidden" id="roomID" name="roomID"
					value="<%=roomVO.getRoomID()%>"> <input type="submit"
					value="送出修改" class="btn btn-outline-danger">
				<div class="d-inline">
					<a class="col-2 btn btn-outline-primary" style="display: inline"
						href="<%=request.getContextPath()%>/back-end/roomTime/listAllRoom.jsp"
						role="button">返回</a>
				</div>
			</FORM>
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
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
			src="https://cdn.jsdelivr.net/npm/bs-stepper@1.7.0/dist/js/bs-stepper.min.js"></script>
	<script
			src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>
	<script
			src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	<script>
		$("#inputBorrowDate").datetimepicker({
			date : null, //一開始輸入框的日期為空
			format : "YYYY-MM-DD", //日期的顯示格式
			locale : moment.locale("zh-tw"), //國別
			daysOfWeekDisabled : [], //隱藏的天數0為周日6為星期六
			minDate : moment().add(1, "days"), //顯示最小天數
			maxDate : moment().add(33, "days"),//顯示最大天數，30為最大的顯示範圍為一個月為限
			disabledDates : [ //隱藏的日期
				moment().add(1, "days"), //前一日
				moment().add(2, "days"), //前二日
				moment().add(3, "days"),//前三日 // "2023-10-10", //特別日期 // "2023-12-25",
			]
		});
	</script>
	<script language="javascript">
		const roomID = document.querySelector("#roomID").value;
		// 	console.log(roomID);
		const inputBorrowDate = document.querySelector("#inputBorrowDate");
		var borrowDate;
		$("#inputBorrowDate").on("dp.change", function(e) {
			// 使用者所選的日期
			inputBorrowDate.value = e.date._d.toLocaleDateString("sv-SE");
			borrowDate = inputBorrowDate.value;
			//	console.log(roomID);
			//	console.log(borrowDate);
			$.ajax({
				url : `<%=request.getContextPath()%>
		/getRoomTime`,
				type : "POST",
				dataType : 'text',
				data : {
					roomID : roomID,
					borrowDate : borrowDate
				},
				success : function(response) { // 取得可預約字串回應
					//	console.log(typeof(response));

					var a = response[0];
					var b = response[1];
					var c = response[2];

					if (a === '0') { // set morning to available
						$("#morning").prop("disabled", false);
						$("#morning").addClass("font-weight-bold");
					}
					if (b === '0') { // set morning to noon
						$("#noon").prop("disabled", false);
						$("#noon").addClass("font-weight-bold");
					}
					if (c === '0') { // set morning to night
						$("#night").prop("disabled", false);
						$("#night").addClass("font-weight-bold");
					}

				},
				error : function() {
					Swal.fire({
						icon : 'info',
						title : '系統忙碌中⛔取得可預約時段失敗',
					})
				}
			})
		});
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