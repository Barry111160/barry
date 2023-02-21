<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.musclebeach.coachclassorder.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  CoachClassOrderVO coachClassOrderVO = (CoachClassOrderVO) request.getAttribute("coachClassOrderVO");
 
%>

<html>






<!-- 					<li> -->
<!-- 						<FORM METHOD="post" ACTION="coach.do"> -->
<!-- 							<b>前台以會員編號查詢所有訂單資料:</b> <select size="1" name="memid"> -->
<%-- 								<c:forEach var="CoachClassOrderVO" items="${coachSvc.all}"> --%>
<%-- 									<option value="${CoachClassOrderVO.memID}">${CoachClassOrderVO.memID} --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> <input type="hidden" name="action" -->
<!-- 								value="getMem_For_DisplayFront"> <input type="submit" -->
<!-- 								value="送出"> -->
<!-- 						</FORM> -->
<!-- 					</li> -->







<head>
<title>員工資料 </title>


<!-- Favicon -->
    <link href="<%=request.getContextPath()%>/front-end/resources/img/favicon.ico" rel="icon">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Flaticon Font -->
    <link href="<%=request.getContextPath()%>/front-end/resources/lib/flaticon/font/flaticon.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<%=request.getContextPath()%>/front-end/resources/css/style.min.css" rel="stylesheet">
    
    <style>
		

  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    width:100%;
  }
    table#table-2 {
	background-color: cyan;
    border: 3px solid black;
    text-align: center;
    width:100%;
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
	width: 600px;
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
		 <h3>訂單明細</h3>
		 <h4><a href="../indexlogin.jsp">回首頁</a></h4>
	</td></tr>
</table>






<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table id="table-2">
<c:forEach var="VO2" items="${coachClassOrderVO2}">
	<tr>
		<th>教練訂單編號</th>
		<th>教練編號</th>
		<th>訂單成立時間</th>
		<th>教練訂單狀態</th>
		<th>課程時間</th>
		<th>課程時段</th>
	</tr>
	<tr>
	
		<td>${VO2.orderID}</td>
		<td>${VO2.empID}</td>
		<td><fmt:formatDate value="${VO2.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td class="orderstauts">${VO2.orderstatus==0 ? '取消預約':'成功預約 '}</td>
		<td>${VO2.classTime}</td> 
		<td class="coachclassperiod" >${VO2.coachclassperiod}</td>	
	</tr>
		</c:forEach>

</table>



    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/resources/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/resources/lib/waypoints/waypoints.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="<%=request.getContextPath()%>/front-end/resources/mail/jqBootstrapValidation.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/resources/mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="<%=request.getContextPath()%>/front-end/resources/js/main.js"></script>

</body>

<script>

const a = document.getElementsByClassName("coachclassperiod");

const b = document.getElementsByClassName("orderstauts");

const c = document.getElementsByClassName("update");

const d = document.getElementsByClassName("delete");

for (let i = 0 ; i<a.length ; i++){
	if(a[i].innerHTML==="222222222222222222222222"){
		a[i].textContent=("已取消")
	}
	for(let j=0;j<24;j++){
//			console.log(a[i].innerHTML.charAt(j));
		if(a[i].innerHTML.charAt(j)==='0'){
			console.log("位置在"+j);
			if(j>=12){
				a[i].textContent=("下午"+(j-12)+"點-"+(j+1-12)+"點");
				break;
			}
			else{
				a[i].textContent=("上午"+j+"點-"+(j+1)+"點");
				break;
			}
		}
	}
}

</script>


</html>