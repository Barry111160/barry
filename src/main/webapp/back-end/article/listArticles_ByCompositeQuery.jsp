<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listArticles_ByCompositeQuery" scope="request" type="java.util.List<ArticleVO>" />
<jsp:useBean id="articleTypeSvc" scope="page" class="com.articleType.model.ArticleTypeService" />


<html>
<head><title>複合查詢 - listEmps_ByCompositeQuery.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
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
<body bgcolor='white'>

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有文章資料 - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/article/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>文章編號</th>
		<th>會員編號</th>
		<th>類別名稱</th>
		<th>文章標題</th>
		<th>文章內容</th>
		<th>發文時間</th>
		<th>最後修改時間</th>
		<th>文章狀態</th>
		<th>修改</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="articleVO" items="${listArticles_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${articleVO.artID}</td>
			<td>${articleVO.memID}</td>
			<td>${articleVO.typeID}</td>
			<td>${articleVO.artTitle}</td>
			<td>${articleVO.artContent}</td>
			<td>${articleVO.artStime}</td> 
			<td>${articleVO.artLtime}</td>	
			<td>${articleVO.artStatus==1?'顯示':'隱藏'}</td>		
<%-- 			<td><c:forEach var="articleTypeVO" items="${articleTypeSvc.all}"> --%>
<%--                     <c:if test="${articleVO.typeID==articleTypeVO.typeID}"> --%>
<%-- 	                    ${articleVO.typeID}【${articleTypeVO.typeName}】 --%>
<%--                     </c:if> --%>
<%--                 </c:forEach> --%>
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/article/article.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="article"  value="${articleVO.artID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>


</body>
</html>