<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<%
	MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
%>
<head>
<meta charset="utf-8">
<title>最新消息查詢</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free Website Template" name="keywords">
<meta content="Free Website Template" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- all css -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/front-end/resources/frontStage/lib/flaticon/font/flaticon.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/front-end/resources/frontStage/css/style.min.css"
	rel="stylesheet">
<style>
table#table-1 {
	background-color: #CEFFCE;
	border: 2px solid black;
	text-align: center;
	width: 1200px;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 5px
}

table#table-1 h3 {
	font-size: 64px;
	color: red;
	margin-bottom: 1px;
}

h1 {
	color: blue;
	text-align: center;
}
}
</style>
</head>

<body class="bg-white">

	<!-- header -->
	<c:if test="${ memVO.memID == null}">
		<%@ include file="/front-end/common/header.jsp" %>
	</c:if>
	<c:if test="${ memVO.memID!=null}">
		<%@ include file="/front-end/common/headerlogin.jsp" %>
	</c:if>
<head>


<title>常見問題查詢</title>



</head>
<body bgcolor='white'>

	<table id="table-1" >
		<tr>
			<td>
				<h3>查無資料</h3>
			</td>
		</tr>
	</table>
	<h1>五秒後自動回上一頁</h1>
	<div style="text-align:center;margin-top:150px" >
	<a id=a-1
		href="<%=request.getContextPath()%>/front-end/question/listAllQuestion.jsp"
		style="font-size: 64px; color: blue;"> 或是點擊此處跳轉</a>
</div>

	<script>
		window.onload = function() {
			setTimeout("history.back()", 5000);
		}
	</script>

	<%@include file="/front-end/common/footer.jsp"%>
	<!-- Back to Top -->
	<a href="#" class="btn btn-outline-primary back-to-top"><i
		class="fa fa-angle-double-up"></i></a>


	<!-- all js -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/resources/frontStage/lib/easing/easing.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/resources/frontStage/lib/waypoints/waypoints.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/resources/frontStage/mail/jqBootstrapValidation.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/resources/frontStage/mail/contact.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/resources/frontStage/js/main.js"></script>
</body>

</html>