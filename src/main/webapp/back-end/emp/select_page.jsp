<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工管理</title>
<link href="css/index.css" rel="stylesheet" />
<link href="css/flaticon.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<style type="text/css"></style>
<body>
	<%@ include file="../header_sidebar.jsp"%>
	<div class="tab-content" id="v-pills-tabContent"
		style="width: 100%; height: calc(100vh - 70px);">
		<!-- ============================================ 首頁 ============================================ -->
		<div class="tab-pane fade active" id="v-pills-home" role="tabpanel"
			aria-labelledby="v-pills-home-tab" tabindex="0"></div>
		<!-- ========================================= 員工管理 ========================================= -->
		<div class="tab-pane fade show p-3 container" id="v-pills-employee"
			role="tabpanel" aria-labelledby="v-pills-employee-tab" tabindex="0"
			style="display: flex; flex-direction: column; align-items: center">
			<h1 class="display-6">員工管理及資料查詢</h1>
			<form class="row g-3" METHOD="post" ACTION="emp.do"
				style="margin-top: 10px;">
				<div class="input-group flex-nowrap">
					<span class="input-group-text" id="addon-wrapping">員工編號查詢</span> <input
						type="text" class="form-control" name="empID"
						aria-label="Username" aria-describedby="addon-wrapping">
					<div class="col-auto" style="display: inline;">
						<input type="hidden" name="action" value="getOne_For_Display" />
						<input class="btn btn-outline-warning" type="submit" value="查詢" />
					</div>
				</div>
				<!-- 				<div class="col-auto"> -->
				<!-- 					<label for="searchName" class="col-form-label">依員工編號查詢</label> -->
				<!-- 				</div> -->
				<!-- 				<div class="col-auto"> -->
				<!-- 					<input type="text" id="searchName" class="form-control" -->
				<!-- 						name="empID" /> -->
				<!-- 				</div> -->

			</form>

			<div class="row" style="height: 15px; padding-left: 50px">
				<c:if test="${not empty errorID}">
					<c:forEach var="message" items="${errorID}">
						<div style="color: red">${message}</div>
					</c:forEach>
				</c:if>
			</div>

			<form class="row g-3 align-items-center" METHOD="post"
				ACTION="emp.do" style="margin-top: 10px">
				<div class="input-group flex-nowrap">
					<span class="input-group-text" id="addon-wrapping">員工姓名查詢</span> <input
						type="text" class="form-control" name="empName"
						aria-label="Username" aria-describedby="addon-wrapping">
					<div class="col-auto">
						<input type="hidden" name="action" value="search_By_Name" /> <input
							class="btn btn-outline-warning" type="submit" value="查詢" />
					</div>
				</div>
				<!-- 				<div class="col-auto"> -->
				<!-- 					<label for="searchPhone" class="col-form-label">依員工姓名查詢</label> -->
				<!-- 				</div> -->
				<!-- 				<div class="col-auto"> -->
				<!-- 					<input type="text" id="searchPhone" class="form-control" -->
				<!-- 						name="empName" /> -->
				<!-- 				</div> -->
				<!-- 				<div class="col-auto"> -->
				<!-- 					<input type="hidden" name="action" value="search_By_Name" /> <input -->
				<!-- 						class="btn btn-outline-primary" type="submit" value="查詢" /> -->
				<!-- 				</div> -->
			</form>

			<div class="row" style="height: 15px; padding-left: 50px">
				<c:if test="${not empty errorName}">
					<c:forEach var="message" items="${errorName}">
						<div style="color: red">${message}</div>
					</c:forEach>
				</c:if>
			</div>

			<a href='listAllEmp.jsp' class="col-3 btn btn-outline-danger"
				style="margin-top: 30px">查詢所有員工</a> <a href='addEmp.jsp'
				class="col-3 btn btn-outline-danger" style="margin-top: 30px">新增員工資料</a>
			<br> <a class="btn btn-danger"
				href="<%=request.getContextPath()%>/back-end/empAccess/select_page1.jsp"
				role="button">權限管理</a>
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

	<!-- 	<script> -->
	<%-- // 		for(var element of ${list}){ --%>
	<!-- // 			console.log(element); -->
	<%-- // 			if(${element.fID == 1}){ --%>
	<!-- // 				$("#v-pills-employee-tab").attr("disabled","true"); -->
	<%-- // 			}else if(${element.fID == 2}){ --%>
	<!-- // 				$("#v-pills-member-tab").prop("disabled":true); -->
	<%-- // 			}else if(${element.fID == 3}){ --%>
	<!-- // 				$("#v-pills-shop-tab").prop("disabled":true); -->
	<%-- // 			}else if(${element.fID == 4}){ --%>
	<!-- // 				$("#v-pills-coach-tab").prop("disabled":true); -->
	<%-- // 			}else if(${element.fID == 5}){ --%>
	<!-- // 				$("#v-pills-class-tab").prop("disabled":true); -->
	<%-- // 			}else if(${element.fID == 6}){ --%>
	<!-- // 				$("#v-pills-room-tab").prop("disabled":true); -->
	<%-- // 			}else if(${element.fID == 7}){ --%>
	<!-- // 				$("#v-pills-article-tab").prop("disabled":true); -->
	<%-- // 			}else if(${element.fID == 8}){ --%>
	<!-- // 				$("#v-pills-service-tab").prop("disabled":true); -->
	<!-- // 			} -->
	<!-- // 		} -->
	<!-- 	</script> -->

	<script>
		$(function() {
			$("#toEmployee").addClass("active");
			$("#toEmployee").attr("aria-selected", "true");
		})
	</script>
</body>
</html>