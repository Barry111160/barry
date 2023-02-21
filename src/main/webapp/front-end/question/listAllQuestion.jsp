<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.musclebeach.question.model.*"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%
	MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>常見問題</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free Website Template" name="keywords">
<meta content="Free Website Template" name="description">

<!-- Favicon -->
	<c:if test="${ memVO.memID == null}">
		<%@ include file="/front-end/common/header.jsp" %>
	</c:if>
	<c:if test="${ memVO.memID!=null}">
		<%@ include file="/front-end/common/headerlogin.jsp" %>
	</c:if>

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
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 5px
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
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



	<%
	ApplicationContext ctx = ApplicationContextUtil.getContext();
	assert ctx!=null;
	QuestionService questionService =ctx.getBean(QuestionService.class);
	List<QuestionVO> list = questionService.getAll();
	pageContext.setAttribute("list", list);
	%>

<head>


<title>所有常見問題</title>



</head>
<body bgcolor='white'>

	<div id=div-1>
		<table id="table-1">
			<tr>
				<td>
					<h3>常見問題</h3>
				</td>
			</tr>
		</table>

		<ul style="margin-top:80px">
			<li>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/question/question.do">
					<b>輸入欲查詢內容 (如:1月):</b> <input type="text" name="questionContent">
					<input type="hidden" name="action"
						value="listQuestionByQuestionContent"> <input type="submit"
						value="送出">
				</FORM>
			</li>
		</ul>
		<table>
			<tr>
				<th>常見問題</th>
				<th>問題內容</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="questionVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${questionVO.questionTitle}</td>
					<td>${questionVO.questionContent}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@ include file="page2.file"%>

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