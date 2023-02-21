<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>

<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_emp_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>文章狀態修改 - update_article_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="article.do" name="form1">
<table>
	<tr>
		<td>文章編號:<font color=red><b>*</b></font></td>
		<td><%=articleVO.getArtID()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><%=articleVO.getMemID()%></td>
	</tr>
	<tr>
		<td>文章類別編號:</td>
		<td><%=articleVO.getTypeID()%></td>	</tr>
	<tr>
		<td>文章標題:</td>
		<td><%=articleVO.getArtTitle()%></td>	</tr>
	<tr>
		<td>文章狀態:</td>
		<td>
		<select name="artStatus">	
        	<option value="1"${(articleVO.artStatus==1)?'selected':'' }>顯示</option>
        	<option value="0"${(articleVO.artStatus==0)?'selected':'' }>隱藏</option>
    	</select>
    	</td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="updateStatus">
<input type="hidden" name="artID" value="<%=articleVO.getArtID()%>">
<input type="hidden" name="memID" value="<%=articleVO.getMemID()%>">
<input type="hidden" name="typeID" value="<%=articleVO.getTypeID()%>">
<input type="hidden" name="artTitle" value="<%=articleVO.getArtTitle()%>">
<input type="hidden" name="artContent" value="<%=articleVO.getArtContent()%>">
<input type="hidden" name="artStime" value="<%=articleVO.getArtStime()%>">
<input type="hidden" name="artLtime" value="<%=articleVO.getArtLtime()%>">

<input type="submit" value="送出修改"></FORM>
</body>



<style>

</style>

<script>
      
</script>
</html>