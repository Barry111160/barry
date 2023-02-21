<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.musclebeach.article.model.ArticleVO" %>
<%@ page import="com.musclebeach.article.model.ArticleService" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.article.model.ArticleService" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ApplicationContext ctx = ApplicationContextUtil.getContext();
	assert ctx != null;
	ArticleService articleService = ctx.getBean(ArticleService.class);
    List<ArticleVO> list = articleService.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有文章資料 - listAllArticle.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>所有文章資料 - listAllArticle.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章編號</th>
		<th>會員編號</th>
		<th>文章類別編號</th>
		<th>文章標題</th>
		<th>文章內容</th>
		<th>發表時間</th>
		<th>最後編輯時間</th>
		<th>文章狀態</th>
		<th>修改</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${articleVO.artID}</td>
			<td>${articleVO.memID}</td>
			<td>${articleVO.typeID}</td>
			<td>${articleVO.artTitle}</td>
			<td>${articleVO.artContent}</td>
			<td>${articleVO.artStime}</td> 
			<td>${articleVO.artLtime}</td>
			<td>${articleVO.artStatus==1?'顯示':'隱藏'}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/article/article.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="article"  value="${articleVO.artID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>