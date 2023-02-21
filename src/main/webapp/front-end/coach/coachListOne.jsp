<%@page import="com.musclebeach.coachtime.model.CoachTimeVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.musclebeach.emp.model.*"%>
<%@ page import="java.util.*"%>
<%@page import="com.musclebeach.coachtime.model.*"%>
<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO");
    MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
%>

<%
  CoachTimeVO coachTimeVO = (CoachTimeVO) request.getAttribute("coachTimeVO");
%>


<html>
 <meta charset="utf-8">
    <title>Gymnast - Gym Website Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free Website Template" name="keywords">
    <meta content="Free Website Template" name="description">

 <link href="<%=request.getContextPath()%>/front-end/resources/img/" rel="icon">
 <link
         href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
         rel="stylesheet">
 <script src="https://kit.fontawesome.com/64c29c5210.js"
         crossorigin="anonymous"></script>

 <link href="<%=request.getContextPath()%>/front-end/resources/lib/flaticon/font/flaticon.css" rel="stylesheet">
 <link href="<%=request.getContextPath()%>/front-end/resources/css/style.min.css" rel="stylesheet">

    <style>
        .login {
            display: inline;
            position: relative;
            Left: calc(100% - 150px);
            top: 5px;
        }

        .register {
            display: inline;
            position: relative;
            Left: calc(100% - 150px);
            top: 5px;
            color: white;
        }
    </style>

</head>

<body class="bg-white">
<c:if test="${ memVO.memID == null}">
    <%@ include file="/front-end/common/header.jsp" %>
</c:if>
<c:if test="${ memVO.memID!=null}">
    <%@ include file="/front-end/common/headerlogin.jsp" %>
</c:if>
    

<!-- Team Start -->
    <div class="container pt-5 team">
        <div class="d-flex flex-column text-center mb-5">
            <h4 class="text-primary font-weight-bold">我們的教練</h4>
            <h4 class="display-4 font-weight-bold">教練資訊</h4>
            
        </div>
        <div class="row  bg-secondary"  >
            
            <div class="col-lg-3 col-md-6 mb-5 " >
                <div class="card border-0 bg-secondary text-center text-white"
                    style="position: relative; bottom: -40px;">
                   <img src="${pageContext.request.contextPath}/back-end/emp/emp.do?action=getImg&empID=${empVO.empID}">
                    <div class="card-body bg-secondary">
                        <h4 class="card-title text-primary" ><b style="color: white;"><%=empVO.getEmpName()%></b></h4>
                        
                    </div>
                </div>
            </div>
            <section style="width:70%">
            <ul style="color: white; list-style:none " >
                <h2 style="color: gray;">專長  <span style="color: red;">Specialty Expertise</span> </h2>
                <li class ="coachcontent">
                    <%=empVO.getCoachContent()%>
                </li>

            </ul >
            <hr style="background-color: darkgray; position: relative; left: 20px;">
            <ul style="color: white; list-style:none">
                <h2 style="color: gray;">證照  <span style="color: red;">Certifications</span> </h2>

                <li>
                    RTS美國國際阻力專業學院
                </li>
                <li>
                    ViPR
                </li>
                <li>
                    Purmotion AIR Fit
                </li>
                <li>
                    AASFP拳擊
                </li>
                <li>
                    軟式藥球訓練
                </li>
                
                <li style="list-style: none;">
                    <a style="list-style: none; text-decoration: none; position: relative; Right: 35%; top:40px; font-size: 25px;" href="coachList.jsp"><b>返回</b></a>
<!--                     <a style="list-style: none; text-decoration: none; position: relative; Left: 88%; font-size: 25px;" href="/CGA105G5/front-end/coachtime/addEmp.jsp" ><b>預約</b></a> -->
                
		
					
					
            <!--      	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coachtime/coach.do" style="margin-bottom: 0px;"> -->
                	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/coachtime/coach.do" style="margin-bottom: 0px;">
			    	 
			    	 <select size="0" name="empID"  style="display: none;" >				
										<option value="${empVO.empID}">${empVO.empID}								
					</select> 	
					<select size="0" name="memid"  style="display: none;">				
										<option value="3">								
					</select>


					<table style="position: relative; Left: 50%;">
					<tr>
					<td style="color: red;font-size: 25px;"><b>可選擇的預約日期</b>
					
					<select name="coachDate" style="font-size: 20px;">
					<c:forEach var="VO2" items="${coachTimeVO2}">
					
					<option value ="${VO2}">${VO2}</option>
					
					</c:forEach>
					
					</select>
					<input type="hidden" name="action" value="memberaddcoachorder">
			    	 <input type="submit" value="預約" style="list-style: none; text-decoration: none; font-size: 25px;">
					</td>
					</tr>
					</table>  	 
			    	 </FORM>
					
                </li>
            </ul>
            
        </section>
        
        </div>
    </div>

    <!-- Team End -->



  <%@include file="/front-end/common/footer.jsp"%>


    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/resources/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/resources/lib/waypoints/waypoints.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/resources/mail/jqBootstrapValidation.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/resources/mail/contact.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/resources/js/main.js"></script>

</body>

<%
java.sql.Date hiredate = null;
try {
	hiredate = coachTimeVO.getCoachDate();
} catch (Exception e) {
	hiredate = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/coach/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/coach/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/coach/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

 <script>



     const a = document.getElementsByClassName("coachcontent");


     const br = document.createElement("span");
     br.innerHTML = "<br/>";
     a[0].innerText = a[0].innerText.replaceAll(',','<br>');
     a[0].innerHTML=a[0].innerText;

 </script>
<script>

		
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	       value: '<%=hiredate%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	     var somedate1 = new Date(minDate);
	      $('#f_date1').datetimepicker({
	          beforeShowDay: function(date) {
	       	  if (  date.getYear() <  somedate1.getYear() || 
			           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
			           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	              ) {
	                   return [false, ""]
	              }
	              return [true, ""];
	      }});

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