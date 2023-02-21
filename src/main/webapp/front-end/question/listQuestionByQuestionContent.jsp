<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.musclebeach.question.model.*"%>
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
    <link href="img/favicon.ico" rel="icon">

    <!-- all css -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front-end/resources/frontStage/lib/flaticon/font/flaticon.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front-end/resources/frontStage/css/style.min.css" rel="stylesheet">

<style>
table#table-1 {
	width: 1200px;
	background-color: #CEFFCE;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
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

				<jsp:useBean id="listQuestionByQuestionContent" scope="request"
					type="java.util.Set<QuestionVO>" />

				<jsp:useBean id="questionSvc" scope="page"
					class="com.musclebeach.question.model.QuestionService" />
				
				<table id="table-1">
					<tr>
						<td>
							<h3>常見問題</h3>
						</td>
					</tr>
				</table>

				<table style="margin-top:80px">
					<tr>
						<th>常見問題</th>
						<th>內容</th>
					</tr>

					<c:forEach var="questionVO" items="${listQuestionByQuestionContent}">

						<tr>
							<td>${questionVO.questionTitle}</td>
							<td>${questionVO.questionContent}</td>

						</tr>
					</c:forEach>
				</table>
					<div style="text-align: center">
				<input type="button"
						value="返回上一頁" class="btn btn-outline-info"
						onclick="history.back()" >
		
		<%@include file="/front-end/common/footer.jsp"%>
</div>

</body>
</html>
</body>
</html>