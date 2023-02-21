<%@page import="java.sql.Date" %>
<%@ page import="com.musclebeach.emp.model.EmpVO" %>
<%@ page import="com.musclebeach.coachtime.model.CoachTimeVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<%
    CoachTimeVO coachTimeVO = (CoachTimeVO) request.getAttribute("coachTimeVO");
%>


<html>
<head>

    <link rel="stylesheet" href="css2/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css2/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css2/flaticon.css" type="text/css">
    <link rel="stylesheet" href="css2/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css2/barfiller.css" type="text/css">
    <link rel="stylesheet" href="css2/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="css2/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css2/style.css" type="text/css">


    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>教練管理</title>
    <link href="css/index.css" rel="stylesheet"/>
    <link href="css/flaticon.css" rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
            rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"/>
    <link rel="canonical"
          href="https://getbootstrap.com/docs/5.3/examples/headers/"/>
    <link rel="canonical"
          href="https://getbootstrap.com/docs/5.3/examples/sidebars/"/>
</head>

<style>
    table#table-1 {
        background-color: #CCCCFF;
        border: 2px solid black;
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

    #v-pills-coach {
        background-image: url("");
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-position: center;
        background-size: cover;
    }

</style>


<body>
<%@ include file="/back-end/header_sidebar.jsp" %>
<div class="tab-content" id="v-pills-tabContent"
     style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade " id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0">
        <img
                src="<%=request.getContextPath()%>/back-end/allcss/image/welcome.gif"
                style="width: 96%" alt=""/>
    </div>
    <!-- ========================================= 員工管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-employee" role="tabpanel"
         aria-labelledby="v-pills-employee-tab" tabindex="0"
         style="border: 2px solid green">employee section
    </div>
    <!-- ========================================= 會員管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-member" role="tabpanel"
         aria-labelledby="v-pills-member-tab" tabindex="0">member
        section
    </div>
    <!-- ========================================= 商城管理 ========================================= -->
    <div class="tab-pane fade " id="v-pills-shop" role="tabpanel"
         aria-labelledby="v-pills-shop-tab" tabindex="0"
         style="border: 2px solid brown">shop section
    </div>
    <!-- ========================================= 教練管理 ========================================= -->
    <div class="tab-pane fade show active" id="v-pills-coach"
         role="tabpanel" aria-labelledby="v-pills-coach-tab" tabindex="0"
         style="border: 2px solid rgb(214, 122, 122); height: 100%; width: 100%">

        <div class="gettouch-section" style="text-align:center ;background-color:darkcyan">
            <div class="container" >
                <div class="row">
                    <div class="col-md-4">
                        <div class="gt-text">

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="gt-text">
                            <ul >
                                <li><b style="font-size:25px">教練時段新增</b></li>
                                <li style="text-decoration:none;"><a href="selectCoachTimePage.jsp"><b style="color:black">回首頁</b></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="gt-text email">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--End -->


        <%-- 錯誤表列 --%>
        <c:if test="${not empty errorMsgs}">
            <font style="color: red">請修正以下錯誤:</font>
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li style="color: red">${message}</li>
                </c:forEach>
            </ul>
        </c:if>

        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <FORM METHOD="post" ACTION="coach.do" name="form1">
            <table id="table-2">
                <tr>
                    <td>教練編號:</td>
                    <td><input type="TEXT" name="empid" size="45"
                               disabled="disabled"
                               value="${userID}"/></td>
                </tr>
                <tr style="position: relative; left: 48%">
                    <td><b>上午時段</b></td>
                </tr>

                <tr>
                    <td style="position: relative; bottom: -10px;">可預約時段:</td>
                    <td><input type="checkbox" value=0 name="coachTime">12:00-1:00
                        <input type="checkbox" value=1 name="coachTime">1:00-2:00
                        <input type="checkbox" value=2 name="coachTime">2:00-3:00
                        <input type="checkbox" value=3 name="coachTime">3:00-4:00
                        <input type="checkbox" value=4 name="coachTime">4:00-5:00
                        <input type="checkbox" value=5 name="coachTime">5:00-6:00
                        <input type="checkbox" value=6 name="coachTime">6:00-7:00
                        <input type="checkbox" value=7 name="coachTime">7:00-8:00
                        <input type="checkbox" value=8 name="coachTime">8:00-9:00
                        <input type="checkbox" value=9 name="coachTime">9:00-10:00
                        <input type="checkbox" value=10 name="coachTime">10:00-11:00
                        <input type="checkbox" value=11 name="coachTime">11:00-12:00
                    </td>
                </tr>
                <tr style="position: relative; left: 48%">
                    <td><b>下午時段</b></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="checkbox" value=12 name="coachTime">12:00-1:00
                        <input type="checkbox" value=13 name="coachTime">1:00-2:00
                        <input type="checkbox" value=14 name="coachTime">2:00-3:00
                        <input type="checkbox" value=15 name="coachTime">3:00-4:00
                        <input type="checkbox" value=16 name="coachTime">4:00-5:00
                        <input type="checkbox" value=17 name="coachTime">5:00-6:00
                        <input type="checkbox" value=18 name="coachTime">6:00-7:00
                        <input type="checkbox" value=19 name="coachTime">7:00-8:00
                        <input type="checkbox" value=20 name="coachTime">8:00-9:00
                        <input type="checkbox" value=21 name="coachTime">9:00-10:00
                        <input type="checkbox" value=22 name="coachTime">10:00-11:00
                        <input type="checkbox" value=23 name="coachTime">11:00-12:00
                    </td>
                </tr>

                <tr>
                    <td>可預約日期:</td>
                    <td><input type="TEXT" name="coachDate" size="45"
                               id="f_date1"
                               value="<%=(coachTimeVO == null) ? "2023-02-01" : coachTimeVO.getCoachDate()%>"/></td>

                </tr>


            </table>

            <br> <input type="hidden" name="empid"
                        value="${userID}">
            <input type="hidden" name="action" value="insert">
            <input type="submit" style="position: relative; left: 45%;" value="送出新增">
        </FORM>

    </div>
    <!-- ========================================= 課程管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-class" role="tabpanel"
         aria-labelledby="v-pills-class-tab" tabindex="0"
         style="border: 2px solid purple">class section
    </div>
    <!-- ========================================= 場地管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-room" role="tabpanel"
         aria-labelledby="v-pills-room-tab" tabindex="0"
         style="border: 2px solid greenyellow">room section
    </div>
    <!-- ========================================= 論壇管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-article" role="tabpanel"
         aria-labelledby="v-pills-article-tab" tabindex="0"
         style="border: 2px solid rgb(253, 250, 66)">article
    </div>
    <!-- ========================================= 客服管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-service" role="tabpanel"
         aria-labelledby="v-pills-service-tab" tabindex="0"
         style="border: 2px solid rgb(15, 198, 42)">service section
    </div>
</div>
</div>
</main>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="sidebars.js"></script>

<script>
    $(function () {
        $("#toCoach").addClass("active");
        $("#toCoach").attr("aria-selected", "true");
    })
</script>
</body>

<script>
    const a = document.getElementsByClassName("coachclassperiod");

    const b = document.getElementsByClassName("orderstauts");

    const c = document.getElementsByClassName("update");

    const d = document.getElementsByClassName("delete");

    for (let i = 0; i < a.length; i++) {
        if (a[i].innerHTML === "222222222222222222222222") {
            a[i].textContent = ("已取消")
        }
        for (let j = 0; j < 24; j++) {
            //			console.log(a[i].innerHTML.charAt(j));
            if (a[i].innerHTML.charAt(j) === '0') {
                console.log("位置在" + j);
                if (j >= 12) {
                    a[i].textContent = ("下午" + (j - 12) + "點-" + (j + 1 - 12) + "點");
                    break;
                } else {
                    a[i].textContent = ("上午" + j + "點-" + (j + 1) + "點");
                    break;
                }
            }
        }

        if (b[i].innerHTML === "取消預約") {
            c[i].setAttribute("disabled", "disabled")
            d[i].setAttribute("disabled", "disabled")
        }
    }
</script>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
    java.sql.Date hiredate = null;
    try {
        hiredate = coachTimeVO.getCoachDate();
    } catch (Exception e) {
        hiredate = new java.sql.Date(System.currentTimeMillis());
    }
%>
<link rel="stylesheet" type="text/css"
      href="../datetimepicker/jquery.datetimepicker.css"/>
<script src="../datetimepicker/jquery.js"></script>
<script
        src="../datetimepicker/jquery.datetimepicker.full.js"></script>

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
        timepicker: false,       //timepicker:true,
        step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
        format: 'Y-m-d',         //format:'Y-m-d H:i:s',
        value: '<%=hiredate%>',
        // value:   new Date(),
        //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
        //startDate:	            '2017/07/10',  // 起始日
        minDate: '-1970-01-01', // 去除今日(不含)之前
        //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
    });

    // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

    //      1.以下為某一天之前的日期無法選擇
    var somedate1 = new Date(minDate);
    $('#f_date1')
        .datetimepicker(
            {
                beforeShowDay: function (date) {
                    if (date.getYear() < somedate1.getYear()
                        || (date.getYear() == somedate1.getYear() && date
                            .getMonth() < somedate1.getMonth())
                        || (date.getYear() == somedate1.getYear()
                            && date.getMonth() == somedate1
                                .getMonth() && date
                                .getDate() < somedate1.getDate())) {
                        return [false, ""]
                    }
                    return [true, ""];
                }
            });

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
</html>