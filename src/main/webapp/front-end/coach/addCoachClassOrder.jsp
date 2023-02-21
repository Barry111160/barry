<%@page import="com.musclebeach.coachtime.model.CoachTimeVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.musclebeach.coachclassorder.model.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%
CoachClassOrderVO coachClassOrderVO = (CoachClassOrderVO) request.getAttribute("coachClassOrderVO");
	MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
%>

<%
CoachTimeVO coachTimeVO = (CoachTimeVO) request.getAttribute("coachTimeVO");

%>


<%
CoachTimeVO coachTimeVO2 = (CoachTimeVO) request.getAttribute("coachTimeVO2");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>教練訂單新增</title>

<link href="<%=request.getContextPath()%>/front-end/resources/img/favicon.ico" rel="icon">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/64c29c5210.js"
	crossorigin="anonymous"></script>

<link href="<%=request.getContextPath()%>/front-end/resources/lib/flaticon/font/flaticon.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/resources/css/style.min.css" rel="stylesheet">


<style>
.coachdata {
	border: 0;
	background-color: #003C9D;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
}

.coachdata:hover {
	color: #003C9D;
	background-color: #fff;
	border: 2px #003C9D solid;
}

table#table-1 {
	background-color: #CCCCFF;
/* 	border: 2px solid black; */
	text-align: center;
	width: 100%;
}

table#table-2 {
	background-color: cyan;
	border: 3px solid black;
	text-align: center;
	width: 100%;
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

* {
        font-family: 微軟正黑體;
    }

    #username,
    #password,
    #fullname,
    #comfirm_password,
    #username2,
    #password2 {
        width: 200px;
        height: 20px;
        margin: 10px;
        color: #df5334;
    }

    h5 {
        margin: 20px;
        color: #a3a2a3;
    }

    h5:hover {
        color: black;
    }

    

    .system_name {
        /*定位對齊*/
        position: relative;
        margin: auto;
        top: 100px;
        text-align: center;
    }

    .submit {
        color: white;
        background: #df5334;
        width: 200px;
        height: 30px;
        margin: 10px;
        padding: 5px;
        border-radius: 5px;
        border: 0px;
    }

    .submit:hover {
        background: #db6937;
    }

    #container2 {
        visibility: hidden;
        /*剛開始消失*/
        height: 350px;
    }


    #copyright {
        text-align: center;
        color: #a3a2a3;
        margin: -200px 0px 0px 0px;
        font-size: 14px;
    }

    input {
        padding: 5px;
        border: none;
        border: solid 1px #ccc;
        border-radius: 5px;
    }
</style>

</head>
<body bgcolor='white'>
<c:if test="${ memVO.memID == null}">
	<%@ include file="/front-end/common/header.jsp" %>
</c:if>
<c:if test="${ memVO.memID!=null}">
	<%@ include file="/front-end/common/headerlogin.jsp" %>
</c:if>
	<br>
	<br>
	<br>
	<br style="display:block;margin-bottom: 2px;font-size:2px;line-height: 12px;">

	<table id="table-1">
		<tr>
			<td>
				<h3>預約教練</h3>
				<h4>
					<a href="coachList.jsp">回MUSCLE BEACH首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/coachclassorder/coach.do" name="form1">
		<table id="table-2">
			<tr>
				<td>教練編號:</td>
				<td><input type="TEXT" name="empID" size="45" disabled="disabled"
					value="<%=(coachTimeVO == null) ? "6" : coachTimeVO.getEmpID()%>" /></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memid" size="45" disabled="disabled"
					value="<%=(coachClassOrderVO == null) ? "4" : coachClassOrderVO.getMemID()%>" /></td>
			</tr>
			<tr>
				<td>課程時間:</td>
				<td><input type="TEXT" name="coachDate" size="45"  disabled="disabled"
					value="<%=(coachTimeVO == null) ? "2012-02-01" : coachTimeVO.getCoachDate()%>" /></td>
			</tr>
<!-- 			coachTimeVO2.getCoachTime() -->
			<tr>
				<td><input type="hidden" name="coachTimevalue" size="45" 
					value="<%=(coachTimeVO2 == null) ? "" : coachTimeVO2.getCoachTime()%>" /></td>
				<tr style="position: relative;left:48% ">
				<td><b>上午時段</b></td>
			</tr>
			
			<tr>
				<td style="position: relative; bottom: -10px; ">可預約時段:</td>
			<td><input type="checkbox" value=0 name="coachTime" onclick="justone(this.value)">12:00-1:00
				<input type="checkbox" value=1 name="coachTime" onclick="justone(this.value)">1:00-2:00
				<input type="checkbox" value=2 name="coachTime" onclick="justone(this.value)">2:00-3:00
				<input type="checkbox" value=3 name="coachTime" onclick="justone(this.value)">3:00-4:00 
				<input type="checkbox" value=4 name="coachTime" onclick="justone(this.value)">4:00-5:00
				<input type="checkbox" value=5 name="coachTime" onclick="justone(this.value)">5:00-6:00
				<input type="checkbox" value=6 name="coachTime" onclick="justone(this.value)">6:00-7:00
				<input type="checkbox" value=7 name="coachTime" onclick="justone(this.value)">7:00-8:00
				<input type="checkbox" value=8 name="coachTime" onclick="justone(this.value)">8:00-9:00
				<input type="checkbox" value=9 name="coachTime" onclick="justone(this.value)">9:00-10:00
				<input type="checkbox" value=10 name="coachTime" onclick="justone(this.value)">10:00-11:00
				<input type="checkbox" value=11 name="coachTime" onclick="justone(this.value)">11:00-12:00
			</td>
			</tr>
			<tr>
			<td>(只能選擇一個時段)</td>
			</tr>
			<tr style="position: relative;left:48% ">
				<td><b>下午時段</b></td>
			</tr>
			<tr>
				<td> </td>
				<td><input type="checkbox" value=12 name="coachTime" onclick="justone(this.value)">12:00-1:00 
					<input type="checkbox" value=13 name="coachTime" onclick="justone(this.value)">1:00-2:00
					<input type="checkbox" value=14 name="coachTime" onclick="justone(this.value)">2:00-3:00
					<input type="checkbox" value=15 name="coachTime" onclick="justone(this.value)">3:00-4:00 
					<input type="checkbox" value=16 name="coachTime" onclick="justone(this.value)">4:00-5:00
					<input type="checkbox" value=17 name="coachTime" onclick="justone(this.value)">5:00-6:00
					<input type="checkbox" value=18 name="coachTime" onclick="justone(this.value)">6:00-7:00
					<input type="checkbox" value=19 name="coachTime" onclick="justone(this.value)">7:00-8:00
					<input type="checkbox" value=20 name="coachTime" onclick="justone(this.value)">8:00-9:00
					<input type="checkbox" value=21 name="coachTime" onclick="justone(this.value)">9:00-10:00
					<input type="checkbox" value=22 name="coachTime" onclick="justone(this.value)">10:00-11:00
					<input type="checkbox" value=23 name="coachTime" onclick="justone(this.value)">11:00-12:00
				</td> 
				
			</tr> 
			
			<tr>
				<td><select name="orderstatus" style="display:none"
					value="${coachClassOrderVO.orderstatus}">
						<option value="1"
							${(coachClassOrderVO.orderstatus==0)? 'selected':'' }>取消預約
						<option value="0"
							${(coachClassOrderVO.orderstatus==1)? 'selected':'' }>成功預約	
				</select></td>
			</tr>
				
			<tr>
		</table>
		<br> 
		<input type="hidden" name="empID" value="<%=coachTimeVO.getEmpID()%>">
		<input type="hidden" name="memid" value="<%= coachClassOrderVO.getMemID()%>">
		<input type="hidden" name="coachDate" value="<%=coachTimeVO.getCoachDate()%>">
		<input type="hidden" name="action" value="insert"> 
		<input onclick="ok()"type="submit" style="position: relative; left: 46%;width:100px;" value="預約" >
	</FORM>


		 <%@include file="/front-end/common/footer.jsp"%>


</body>

<script>
const a =document.getElementsByName("coachTimevalue");

const b =document.getElementsByName("coachTime");

for(let i =0;i<24;i++){
	console.log(a[0].value.charAt(i));



	if(a[0].value.charAt(i)==='2'){
			b[i].setAttribute("disabled","disabled");
		
	}else{
			b[i].style.width=22;
			b[i].style.height=22;
			}

	}
	
	function justone(thisValue){
// 		console.log("我有運作");
		let selection = document.form1.coachTime.length;
		for (let j = 0; j<selection ; j++){
// 			console.log("我有運作2");
			if(eval("document.form1.coachTime[ " + j + " ].checked")==true){
// 				console.log("我有運作3");
				document.form1.coachTime[j].checked = false;
				if(document.form1.coachTime[j].value==thisValue){
					document.form1.coachTime[j].checked = true;
// 					console.log("我有運作4");
				}
			}
		}
	}
const coach = document.getElementsByName("coachTime");
	function ok(){
		if(coach[0].checked===false && coach[1].checked===false && coach[2].checked===false && coach[3].checked===false && coach[4].checked===false && coach[5].checked===false && coach[6].checked===false && coach[7].checked===false && coach[8].checked===false && coach[9].checked===false && coach[10].checked===false && coach[11].checked===false && coach[12].checked===false && coach[13].checked===false && coach[14].checked===false && coach[15].checked===false && coach[16].checked===false && coach[17].checked===false && coach[18].checked===false && coach[19].checked===false && coach[20].checked===false && coach[21].checked===false && coach[22].checked===false && coach[23].checked===false ){
			alert("預約失敗,請填寫時段");
		}else{
		alert("預約成功");
		}
	}
	
	
</script>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<%
java.sql.Date hiredate = null;
try {
	hiredate = coachClassOrderVO.getClassTime();
} catch (Exception e) {
	hiredate = new java.sql.Date(System.currentTimeMillis());
}
%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/coach/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/coach/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/coach/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%> ', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
<script>
	$(function () {
		$("#toCoach").addClass("active");
		$("#toCoach").attr("aria-selected", "true");
	})
</script>
</html>