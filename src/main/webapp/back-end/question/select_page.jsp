<%@ page import="com.musclebeach.question.model.QuestionService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>客服管理</title>
    <link href="css/index.css" rel="stylesheet"/>
    <link href="css/flaticon.css" rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
            rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"/>
    <link rel="canonical"
          href="https://getbootstrap.com/doc	s/5.3/examples/headers/"/>
    <link rel="canonical"
          href="https://getbootstrap.com/docs/5.3/examples/sidebars/"/>
    <style type="text/css"></style>
    <style>
        table#table-1 {
            width: 1056px;
            background-color: #FFDEAD;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 3px ridge Gray;
            height: 80px;
            text-align: center;
        }
    </style>
</head>
<body>
<%@ include file="/back-end/header_sidebar.jsp" %>
<%
    QuestionService questionSvc = context.getBean(QuestionService.class);
    pageContext.setAttribute("questionSvc", questionSvc);
%>
<div class="tab-content" id="v-pills-tabContent"
     style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade" id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0">
        <img src="/image/welcome.gif" style="width: 96%" alt=""/>
    </div>
    <!-- ========================================= 員工管理 ========================================= -->
    <div class="tab-pane fade " id="v-pills-employee" role="tabpanel"
         aria-labelledby="v-pills-employee-tab" tabindex="0"
         style="border: 2px solid green">employee section
    </div>
    <!-- ========================================= 會員管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-member" role="tabpanel"
         aria-labelledby="v-pills-member-tab" tabindex="0">member
        section
    </div>
    <!-- ========================================= 商城管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-shop" role="tabpanel"
         aria-labelledby="v-pills-shop-tab" tabindex="0"
         style="border: 2px solid brown">shop section
    </div>
    <!-- ========================================= 教練管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-coach" role="tabpanel"
         aria-labelledby="v-pills-coach-tab" tabindex="0"
         style="border: 2px solid rgb(214, 122, 122)">coach section
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
    <div class="tab-pane fade show active" id="v-pills-service"
         role="tabpanel" aria-labelledby="v-pills-service-tab" tabindex="0"
         style="width:100%">
        <table id="table-1" style="width:100%">
            <tr>
                <td><h3>常見問題管理</h3></td>
            </tr>
        </table>

        <div style="text-align: center;">
            <h3>常見問題資料查詢:</h3>
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
        <hr>
        <div style="text-align: center;">
            <a href='listAllQuestion.jsp'><input type="button"
                                                 value="全部常見問題" class="btn btn-outline-info"></a> <a
                href='addQuestion.jsp'><br> <br> <input type="button"
                                                        value="新增常見問題" class="btn btn-outline-secondary"></a> <br>
            <br>
            <ul>


                <FORM METHOD="post" ACTION="question.do">
                    <input type="text" name="questionTitle" placeholder="請輸入欲查詢標題關鍵字">
                    <input type="hidden" name="action"
                           value="listQuestionByQuestionTitleB"> <input
                        type="submit" value="送出" class=" btn btn-outline-danger">
                </FORM>
            </ul>
            <ul>

                <FORM METHOD="post" ACTION="question.do">
                    <input type="text" name="questionContent"
                           placeholder="請輸入欲查詢內容關鍵字"> <input type="hidden"
                                                                        name="action"
                                                                        value="listQuestionByQuestionContentB"> <input
                        type="submit" value="送出" class=" btn btn-outline-danger">
                </FORM>
            </ul>


            `

            <ul>
                <FORM METHOD="post" ACTION="question.do">
                    <b>選擇消息標題:</b> <select size="1" name="questionID">
                    <c:forEach var="questionVO" items="${questionSvc.all}">
                    <option value="${questionVO.questionID}">${questionVO.questionTitle}
                        </c:forEach>
                </select> <input type="hidden" name="action" value="getOne_For_Display">
                    <input type="submit" value="送出" class="btn btn-outline-primary">
                </FORM>
            </ul>


        </div>

    </div>
</div>
</main>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="index/sidebars.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {
        $("#toService").addClass("active");
        $("#toService").attr("aria-selected", "true");
    })
</script>
</body>
</html>