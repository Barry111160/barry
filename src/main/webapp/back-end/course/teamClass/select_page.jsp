<%@ page import="com.musclebeach.course.entity.ClassType" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.course.service.ClassTypeService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Muscle Beach 後台首頁</title>
    <link
            rel="canonical"
            href="https://getbootstrap.com/docs/5.3/examples/headers/"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
    />
    <link
            rel="canonical"
            href="https://getbootstrap.com/docs/5.3/examples/sidebars/"
    />
    <link href="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/assets/dist/css/bootstrap.min.css"
          rel="stylesheet"/>
    <style type="text/css"></style>
    <link href="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/index/index.css"
          rel="stylesheet"/>
    <!-- Flaticon Font -->
    <link href="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/lib/flaticon/font/flaticon.css"
          rel="stylesheet"/>
    <title>GYM classSchedule: Home</title>

    <style>
        div#v-pills-class {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
        }

        table#table-1 {
            width: 95%;
            background-color: #CDA581;
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

    <title>GYM Course</title>


</head>
<body bgcolor='white'>
<%@ include file="../../header_sidebar.jsp" %>
<div class="tab-content" id="v-pills-tabContent"
     style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div
            class="tab-pane fade "
            id="v-pills-home"
            role="tabpanel"
            aria-labelledby="v-pills-home-tab"
            tabindex="0"
    >
        <img src="/image/welcome.gif" style="width: 96%" alt=""/>
    </div>
    <!-- ========================================= 員工管理 ========================================= -->
    <div
            class="tab-pane fade"
            id="v-pills-employee"
            role="tabpanel"
            aria-labelledby="v-pills-employee-tab"
            tabindex="0"
            style="border: 2px solid green"
    >
        employee section Lorem ipsum dolor sit amet consectetur adipisicing
        elit. Minima reprehenderit adipisci praesentium atque! Corrupti
        ratione itaque magni unde culpa ex?
    </div>
    <!-- ========================================= 會員管理 ========================================= -->
    <div
            class="tab-pane fade"
            id="v-pills-member"
            role="tabpanel"
            aria-labelledby="v-pills-member-tab"
            tabindex="0"
    >

    </div>
    <!-- ========================================= 商城管理 ========================================= -->
    <div
            class="tab-pane fade"
            id="v-pills-shop"
            role="tabpanel"
            aria-labelledby="v-pills-shop-tab"
            tabindex="0"
            style="border: 2px solid brown"
    >
        shop section
    </div>
    <!-- ========================================= 教練管理 ========================================= -->
    <div
            class="tab-pane fade"
            id="v-pills-coach"
            role="tabpanel"
            aria-labelledby="v-pills-coach-tab"
            tabindex="0"
            style="border: 2px solid rgb(214, 122, 122)"
    >
        coach section
    </div>
    <!-- ========================================= 課程管理 ========================================= -->
    <div
            class="tab-pane fade  show active"
            id="v-pills-class"
            role="tabpanel"
            aria-labelledby="v-pills-class-tab"
            tabindex="0"
            style="border: 2px solid purple "
    >


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
            <li><a href='listAllTeamClass.jsp'>瀏覽全部課程</a></li>


            <li>
                <FORM METHOD="post" ACTION="teamClass.do">
                    <b>輸入課程編號:</b>
                    <input type="text" name="classID">
                    <input type="hidden" name="action" value="getOne_For_Display">
                    <input type="submit" value="送出">
                </FORM>
            </li>

            <%
                ApplicationContext ctx = ApplicationContextUtil.getContext();
                assert ctx != null;
                ClassTypeService classTypeService = ctx.getBean(ClassTypeService.class);
                List<ClassType> list = classTypeService.selectAll();
                pageContext.setAttribute("list", list);
            %>

            <li>
                <FORM METHOD="post" ACTION="teamClass.do">
                    <b>選擇課程類別:</b>
                    <select size="1" name="typeID">
                        <c:forEach var="classTypeService" items="${list}">
                        <option value="${classTypeService.typeID}">${classTypeService.typeName}
                            </c:forEach>
                    </select>
                    <input type="hidden" name="action" value="getAllClass_For_OneType">
                    <input type="submit" value="送出">
                </FORM>
            </li>


            <li><a href='addTeamClass.jsp'>新增團體課程</a></li>

        </ul>


    </div>
    <!-- ========================================= 場地管理 ========================================= -->
    <div
            class="tab-pane fade"
            id="v-pills-room"
            role="tabpanel"
            aria-labelledby="v-pills-room-tab"
            tabindex="0"
            style="border: 2px solid greenyellow"
    >
        room section
    </div>
    <!-- ========================================= 論壇管理 ========================================= -->
    <div
            class="tab-pane fade"
            id="v-pills-article"
            role="tabpanel"
            aria-labelledby="v-pills-article-tab"
            tabindex="0"
            style="border: 2px solid rgb(253, 250, 66)"
    >
        article
    </div>
    <!-- ========================================= 客服管理 ========================================= -->
    <div
            class="tab-pane fade"
            id="v-pills-service"
            role="tabpanel"
            aria-labelledby="v-pills-service-tab"
            tabindex="0"
            style="border: 2px solid rgb(15, 198, 42)"
    >
        service section
    </div>
</div>
</main>
<script src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/index/sidebars.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {
        $("#toClass").addClass("active");
        $("#toClass").attr("aria-selected", "true");
    })
</script>

</body>
</html>