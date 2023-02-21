<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.coachtime.model.CoachTimeService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
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

<style>

    @import url('https://fonts.googleapis.com/css?family=Lato:400,700');

    *, *:before, *:after {
        -webkit-box-sizing: inherit;
        -moz-box-sizing: inherit;
        box-sizing: inherit;
    }

    ::-webkit-input-placeholder {
        color: #56585b;
    }

    ::-moz-placeholder {
        color: #56585b;
    }

    :-moz-placeholder {
        color: #56585b;
    }

    html {
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    body {
        font-family: 'Lato', sans-serif;
        margin: 0;
        /*background: url('images/coachs.jpg') no-repeat center center fixed ;*/
        /*-webkit-background-size: cover;*/
        /*-moz-background-size: cover;*/
        /*background-size: cover;*/
        color: #0a0a0b;
        overflow: hidden;

    }

    ul, nav {
        list-style: none;
        padding: 0;
    }

    a {
        color: #fff;
        text-decoration: none;
        cursor: pointer;
        opacity: 0.9;
    }

    a:hover {
        opacity: 1;
    }

    h1 {
        font-size: 3rem;
        font-weight: 700;
        color: #fff;
        margin: 0 0 1.5rem;
    }

    i {
        font-size: 1.3rem;
    }


    a.btn {
        color: #fff;
        padding: 10px;
        border: 1px solid rgba(255, 255, 255, 0.5);
        -webkit-transition: all 0.2s;
        -moz-transition: all 0.2s;
        transition: all 0.2s;
    }

    a.btn:hover {
        background: #d73851;
        border: 1px solid #d73851;
        color: #fff;
    }

    .cover {
        height: 100vh;
        width: 100%;
        background: -webkit-gradient(linear, left top, left bottom, from(rgba(0, 0, 0, 0.05)), to(rgba(0, 0, 0, 0)));
        background: -webkit-linear-gradient(top, rgba(0, 0, 0, 0.05) 0%, rgba(0, 0, 0, 0) 100%);
        background: linear-gradient(to bottom, rgba(0, 0, 0, 0.05) 0%, rgba(0, 0, 0, 0) 100%);
        padding: 20px 50px;
        display: -webkit-box;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        flex-direction: column;
        -webkit-box-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        align-items: center;
    }

    .flex-form input[type="submit"] {
        background: #ef3f5a;
        border: 1px solid #ef3f5a;
        color: #fff;
        padding: 0 30px;
        cursor: pointer;
        -webkit-transition: all 0.2s;
        -moz-transition: all 0.2s;
        transition: all 0.2s;
    }

    .flex-form input[type="submit"]:hover {
        background: #d73851;
        border: 1px solid #d73851;
    }

    .flex-form {
        display: -webkit-box;
        display: flex;
        z-index: 10;
        position: relative;
        width: 500px;
        box-shadow: 4px 8px 16px rgba(0, 0, 0, 0.3);
    }

    .flex-form > * {
        border: 0;
        padding: 0 0 0 10px;
        background: #fff;
        line-height: 50px;
        font-size: 1rem;
        border-radius: 0;
        outline: 0;
        -webkit-appearance: none;
    }

    input[type="search"] {
        flex-basis: 500px;
    }

    #madeby {
        position: absolute;
        bottom: 0;
        right: 0;
        padding: 25px 100px;
        color: #fff;
    }

    @media all and (max-width: 800px) {
        body {
            font-size: 0.9rem;
        }

        .flex-form {
            width: 100%;
        }

        input[type="search"] {
            flex-basis: 100%;
        }

        .flex-form > * {
            font-size: 0.9rem;
        }


        h1 {
            font-size: 2rem;
        }

        .cover {
            padding: 20px;
        }

        #madeby {
            padding: 30px 20px;
        }
    }

    @media all and (max-width: 360px) {
        header nav li {
            margin: 0 10px;
        }

        .flex-form {
            display: -webkit-box;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            flex-direction: column;
        }

        input[type="search"] {
            flex-basis: 0;
        }

        label {
            display: none;
        }
    }


</style>


<body>
<%@ include file="/back-end/header_sidebar.jsp" %>
<div class="tab-content" id="v-pills-tabContent"
     style="width: 100%; height: calc(100vh - 70px);">
    <%
        CoachTimeService coachTimeService = context.getBean(CoachTimeService.class);
        pageContext.setAttribute("coachtimeSvc", coachTimeService);
    %>
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade " id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0">
        <img
                src="<%=request.getContextPath()%>/back-end/coachtime/allcss/image/welcome.gif"
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


        <div class="container" style="position:relative">
            <img src="images/coachs.jpg" alt=""
                 style="width:100%;height:100%;object-fit:cover;background-color:transparent;opacity:0.4;">

            <div class="cover" style="position:absolute;top:0;left:0">

                <FORM METHOD="post" ACTION="coach.do" style="margin-bottom: 0px;">
                    <select size="0" name="empid" style="display: none;">
                        <option value="${userID}">
                    </select>
                    <td><p style="font-size: 25px;"><b>教練時段</b>
                        <input type="hidden" name="action" value="getOneEmpid">
                        <input type="submit" value="新增"
                               style="list-style: none; text-decoration: none; font-size: 16px;"></p></td>
                </FORM>


                <FORM METHOD="post" ACTION="coach.do" class="flex-form">
                    <br> <b>輸入教練編號查詢所有時間 :</b>
                    <input type="text" name="empid">
                    <input type="hidden" name="action" value="getAll_For_DisplaybyEMP">
                    <input type="submit" value="送出">
                </FORM>


                <FORM METHOD="post" ACTION="coach.do" style="margin-bottom: 0px;">
                    <select size="0" name="empid" style="display: none;">
                        <option value="${userID}">
                    </select>
                    <td><p style="font-size: 25px;"><b>查看自己所有可預約時段</b>
                        <input type="hidden" name="action" value="getAll_For_DisplaybyEMP">
                        <input type="submit" value="查詢"
                               style="list-style: none; text-decoration: none; font-size: 16px;"></p></td>
                </FORM>


                <ul style="text-align: center; list-style-type: none;">
                    <li><a href="<%=request.getContextPath()%>/back-end/coachclassorder/selectCoachClassOrderPage.jsp"
                           style="text-decoration: none; display: inline-block; position: relative;font-size:26px;color:white"><b
                            style="color:red">切換至教練課程訂單管理</b></a>
                    <li/>
                </ul>
                <ul style="text-align: center; list-style-type: none;">

                    <li><a href='listAllCoachTime.jsp'><br style="color:black"><b
                            style="color:black">所有教練時段資料</b><br></a></li>
                </ul>
            </div>
        </div>

        <%-- 錯誤表列 --%>
        <c:if test="${not empty errorMsgs}">
            <font style="color: red">請修正以下錯誤:</font>
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li style="color: red">${message}</li>
                </c:forEach>
            </ul>
        </c:if>


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
</html>