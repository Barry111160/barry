<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.musclebeach.news.model.*"%>
<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%
	MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>最新消息</title>
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
  table{
  margin:0 ,auto;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
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


<jsp:useBean id="listNewsByNewsTitle" scope="request" type="java.util.Set<NewsVO>" />
<jsp:useBean id="deptSvc" scope="page" class="com.musclebeach.news.model.NewsService" />




<head>
<title >搜尋結果 - listNewsByNewsTitle.jsp</title>

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
<body bgcolor='white'>


<table  id="table-1">
	<tr><td>
		 <h3>相關最新消息</h3>
	</td></tr>
</table>
<table style="margin-top:80px" id ="table-2">
	<tr>
		<th>消息標題</th>
		<th>消息內容</th>
		<th>消息公布時間</th>
	</tr>

	<c:forEach var="newsVO" items="${listNewsByNewsTitle}">
		
		<tr>
			<td>${newsVO.newsTitle}</td>
			<td>${newsVO.newsContent}</td>
			<td>${newsVO.newsTime}</td>
		
		</tr>
	</c:forEach>
</table>
	<div style="text-align: center">
<input type="button" value="返回上一頁" class="btn btn-outline-secondary"
onclick="history.back()" >
<%@include file="/front-end/common/footer.jsp"%>
</div>

</body>
</html>