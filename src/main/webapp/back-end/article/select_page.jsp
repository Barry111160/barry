<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title> Article: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Article: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Article: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllArticle.jsp'>List</a> all Articles.  <br><br></li>
</ul>

   <jsp:useBean id="articleTypeSvc" scope="page" class="com.articleType.model.ArticleTypeService" />

<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/article/article.do" name="form1">
        <b><font color=blue>萬用複合查詢:</font></b> <br>
        <b>輸入會員編號:</b>
        <input type="text" name="mem_id" value=""><br>
        
       <b>輸入文章標題:</b>
       <input type="text" name="art_title" value="" maxlength="2"><br>        

       <b>選擇文章類別:</b>
       <select size="1" name=type_id >
          <option value="">
         <c:forEach var="articleTypeVO" items="${articleTypeSvc.all}" > 
          <option value="${articleTypeVO.typeID}">${articleTypeVO.typeName}
         </c:forEach> 
        </select><br>  
       
       <b>輸入文章內容:</b>
       <input type="text" name="art_content" value=""><br>
		       
        <input type="hidden" name="action" value="listArticles_ByCompositeQuery">
    	<input type="submit" value="送出">	
     </FORM>
  </li>
</ul>


</body>
</html>