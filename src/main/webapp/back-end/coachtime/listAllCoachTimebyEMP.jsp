<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.coachtime.model.CoachTimeVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    CoachTimeVO coachTimeVO = (CoachTimeVO) request.getAttribute("coachTimeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
                                <li><b style="font-size:25px">教練時段資料</b></li>
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







        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <table id="table-2">
            <c:forEach var="VO2" items="${coachTimeVO2}">
                <tr>
                    <th>建立編號</th>
                    <th>教練編號</th>
                    <th>可預約時段</th>
                    <th>可預約日期</th>
                    <th>修改</th>
                    <th>刪除</th>
                </tr>
                <tr>
                    <td>${VO2.timeID}</td>
                    <td>${VO2.empID}</td>
                    <td class="coachtTimeJs">${VO2.coachTime}</td>
                    <td>${VO2.coachDate}</td>
                    <td>
                        <FORM METHOD="post"
                              ACTION="<%=request.getContextPath()%>/back-end/coachtime/coach.do"
                              style="margin-bottom: 0px;">
                            <input type="hidden" name="timeID" value="${VO2.timeID}">
                            <input type="hidden" name="empid" value="${VO2.empID}">
                            <input type="hidden" name="coachTime" value="${VO2.coachTime}">
                            <input type="hidden" name="action" value="getOne_For_Update">
                            <input type="submit" value="修改" class="update">
                        </FORM>
                    </td>
                    <td>
                        <FORM METHOD="post"
                              ACTION="<%=request.getContextPath()%>/back-end/coachtime/coach.do"
                              style="margin-bottom: 0px;">
                            <input type="submit" value="刪除" class="delete"> <input
                                type="hidden" name="orderid"
                                value="${coachClassOrderVO.orderID}"> <input
                                type="hidden" name="action" value="delete">
                        </FORM>
                    </td>
                </tr>
            </c:forEach>

        </table>

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
</main>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="sidebars.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {
        $("#toCoach").addClass("active");
        $("#toCoach").attr("aria-selected", "true");
    })
</script>
</body>

<script>
    const e = document.getElementsByClassName("coachtTimeJs");
    const a = [];

    for (let i = 0; i < e.length; i++) {
        for (let j = 0; j < 24; j++) {
            if (e[i].innerHTML.charAt(j) === "0") {
                a.push(j);
            }
            if (j === 23) {

                for (let k = 0; k < a.length; k++) {
                    for (let l = 0; l < 24; l++) {
                        if (a[k] === l) {
                            if (l > 12 && l !== 23) {
                                a[k] = "下午" + (l - 12) + "點~下午" + (l - 12 + 1)
                                    + "點 ";
                            } else if (l === 23) {
                                a[k] = "下午" + (l - 12) + "點~上午" + (l - 12 + 1)
                                    + "點 ";
                            } else if (l < 12 && l !== 12) {
                                a[k] = "上午" + (l) + "點~上午" + (l + 1) + "點 ";
                            } else if (l === 12) {
                                a[k] = "上午" + (l) + "點~下午" + (l - 12 + 1)
                                    + "點 ";
                            }
                        }
                    }

                }

                e[i].textContent = a;
                console.log(a.length);
                a.length = 0;

            }

        }

    }
</script>
</html>