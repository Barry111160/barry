<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Msucle Beach: 會員註冊</title>

    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap"
          rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/front-end/member/css/font-awesome.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/front-end/member/css/templatemo-training-studio.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/front-end/member/datetimepicker/jquery.datetimepicker.css"
          rel="stylesheet" type="text/css"/>
    <style type="text/css"></style>
    <style>
        .xdsoft_datetimepicker .xdsoft_datepicker {
            width: 300px; /* width:  300px; */
        }

        .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
            height: 151px; /* height:  151px; */
        }
    </style>

</head>
<body>


<!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="<%=request.getContextPath()%>/front-end/index.html" class="logo"><em>Muscle </em>Beach</a>
                    <!-- ***** Logo End ***** -->
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- ***** Header Area End ***** -->

<!-- ***** Main Banner Area Start ***** -->
<div class="main-banner" id="top">
    <video autoplay muted loop id="bg-video">
        <source src="<%=request.getContextPath()%>/front-end/member/images/gym-video.mp4"
                type="video/mp4" style="height: 100%"/>
    </video>

    <div class="video-overlay header-text" style="min-height: 100vh;">
        <div class="caption mt-5">
            <h3 class="text-white">會員註冊</h3>
            <main class="w-100 mt-3">
                <form method="post" action="<%=request.getContextPath()%>/back-end/member/mem.do"
                      name="addform"
                      onsubmit="alert('註冊成功！請至註冊信箱點擊驗證連結以啟用帳號💌 \n 🖤 Muscle Beach 歡迎您的加入 🖤')">
                    <div class="w-100 form-signin border border-1 rounded mx-auto"
                         style="background: rgba(255, 255, 255, 0.2)">
                        <div class="container text-white">
                            <div class="row my-3">
                                <div class="col-md-3">
                                    <label for="inputName" class="form-label">會員姓名</label> <input
                                        type="text" class="form-control" id="inputName"
                                        name="memName"
                                        value="<%=(memVO == null) ? "" : memVO.getMemName()%>" required/>
                                </div>
                                <div class="col-md-3">
                                    <label for="inputAccount" class="form-label">帳號</label> <input
                                        type="text" class="form-control" id="inputAccount"
                                        name="account"
                                        value="<%=(memVO == null) ? "" : memVO.getAccount()%>" required/>
                                </div>
                                <div class="col-md-3">
                                    <label for="inputPassword" class="form-label">密碼</label> <input
                                        type="password" class="form-control" id="inputPassword"
                                        name="password" pattern="^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$"
                                        oninput="setCustomValidity('');"
                                        oninvalid="setCustomValidity('請輸入正確的密碼格式:含英數至少8個字元');"
                                        placeholder="含英數至少8個字元" required/>
                                </div>
                                <div class="col-md-3">
                                    <label for="ConfirmPassword" class="form-label">請再次輸入密碼</label>
                                    <input id="ConfirmPassword" name="ConfirmPassword"
                                           class="form-control" type="password"
                                           oninput="setCustomValidity('');"
                                           onchange="if(document.getElementById('inputPassword').value != document.getElementById('ConfirmPassword').value){setCustomValidity('密碼不吻合');}"
                                           required/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6 col-md-3">
                                    <label for="inputTel" class="form-label">手機號碼</label> <input
                                        type="text" class="form-control" id="inputTel"
                                        name="memPhone" maxlength="10"
                                        pattern="09\d{8}" oninput="setCustomValidity('');"
                                        oninvalid="setCustomValidity('請輸入正確的手機號碼格式:09xxxxxxxx');"
                                        value="<%=(memVO == null) ? "" : memVO.getMemPhone()%>" required/>
                                </div>
                                <div class="col-6 col-md-3">
                                    <label for="inputBirth" class="form-label">生日</label>
                                    <input type="text" class="form-control" id="inputBirth"
                                           name="memBirthday" required/>
                                </div>
                                <div class="col-12 col-md-6">
                                    <label for="inputEmail" class="form-label">信箱</label>
                                    <input
                                            type="email" class="form-control" id="inputEmail"
                                            name="memMail"
                                            value="<%=(memVO == null) ? "" : memVO.getMemMail()%>" required/>
                                </div>
                            </div>
                            <div class="col-12 col-md-8 my-3">
                                <label for="inputAddress" class="form-label">通訊地址</label> <input
                                    type="text" class="form-control" id="inputAddress"
                                    name="memAddress"
                                    value="<%=(memVO == null) ? "" : memVO.getMemAddress()%>" required/>
                            </div>
                            <div>
                                <c:if test="${not empty errorMsgs}">
                                    <c:forEach var="message" items="${errorMsgs}">
                                        <div style="color: red; font-size: 20px">${message}</div>
                                    </c:forEach>
                                </c:if>
                            </div>
                            <div class="col-12 my-4">
                                <input
                                        type="hidden" name="action" value="insert"/>
                                <input
                                        type="submit" class="btn btn-primary" value="Submit"/>
                            </div>
                        </div>
                        <p class="mt-3 mb-3 text-white">&copy; 2022</p>
                    </div>
                </form>
            </main>
        </div>
    </div>
</div>
<!-- ***** Main Banner Area End ***** -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
    java.sql.Date memBirthday = null;
    try {
        memBirthday = memVO.getMemBirthday();
    } catch (Exception e) {
        memBirthday = new java.sql.Date(System.currentTimeMillis());
    }
%>

<script src="<%=request.getContextPath()%>/front-end/member/datetimepicker/jquery.js"></script>
<script
        src="<%=request.getContextPath()%>/front-end/member/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
    $.datetimepicker.setLocale('zh');
    $('#inputBirth').datetimepicker({
        theme: '', //theme: 'dark',
        timepicker: false, //timepicker:true,
        step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
        format: 'Y-m-d', //format:'Y-m-d H:i:s',
        value: '', // value:   new Date(),
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
<!-- Bootstrap -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
        integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
        crossorigin="anonymous"></script>

<!-- Plugins -->
<script src="<%=request.getContextPath()%>/front-end/member/js/scrollreveal.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/member/js/waypoints.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/member/js/jquery.counterup.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/member/js/imgfix.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/member/js/mixitup.js"></script>
<script src="<%=request.getContextPath()%>/front-end/member/js/accordions.js"></script>

<!-- Global Init -->
<script src="<%=request.getContextPath()%>/front-end/member/js/custom.js"></script>
</body>
</html>