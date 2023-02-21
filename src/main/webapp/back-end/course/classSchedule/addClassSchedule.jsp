<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.musclebeach.classSchedule.model.*" %>

<%
    ClassScheduleVO classScheduleVO = (ClassScheduleVO) request.getAttribute("classScheduleVO");
%>
<!DOCTYPE html>
<html lang="en">
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>


    <style>
        table#table-1 {
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

        div#v-pills-class {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
        }

        #table-2 {

            border: 2px solid black;
            text-align: center;
        }

        #table-2 td {
            width: 50%;
            background-color: azure;
            color: silver;
            text-align: center;
            transition: all 0.5s ease-in-out;
        }

        #table-2 tr:hover td {
            background-color: lightblue;
            transition: all 0.5s ease-in-out;
        }

        #table-2 tr td {
            color: maroon;

            transition: all 0.5s ease-in-out;
        }

        #table-2 tr:hover td {
            color: blue;

            border-left: 5px solid rgba(79, 192, 210, 0.6);
            transition: all 0.5s ease-in-out;
        }

        #table-2 tr:hover .show {
            display: block;

        }

        #table-2 tr {
            border-bottom: 1px solid #2a2a2a;
        }

        #table-2 tr:last-of-type {
            border-bottom: none;
        }
    </style>

    <style>
        table {
            width: 500px;
            background-color: white;
            margin-top: 1px;
            margin-bottom: 1px;
        }

        table, th, td {
            border: 0px solid #CCCCFF;
        }

        th, td {
            padding: 1px;
        }
    </style>

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

    <!-- ========================================= 課程管理 ========================================= -->
    <div
            class="tab-pane fade show active"
            id="v-pills-class"
            role="tabpanel"
            aria-labelledby="v-pills-class-tab"
            tabindex="0"
            style="border: 2px solid purple "
    >
        <table id="table-1">
            <tr>
                <td>
                    <h3>課程時間表新增 </h3></td>
            </tr>
        </table>


        <%-- 錯誤表列 --%>
        <c:if test="${not empty errorMsgs}">
            <font style="color:red">請修正以下錯誤:</font>
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li style="color:red">${message}</li>
                </c:forEach>
            </ul>
        </c:if>

        <FORM METHOD="post" ACTION="classSchedule.do"
              name="form1">
            <table id="table-2">
                <tr>
                    <td>課程編號:</td>
                    <td><input type="TEXT" name="classID" size="50"
                               value="<%= (classScheduleVO==null)? "1" : classScheduleVO.getClassID()%>" required/></td>
                </tr>
                <tr>

                    <td>開始時間:</td>
                    <td><input type="TEXT" name="startTime" size="50"
                               value="<%= (classScheduleVO==null)? "2023-01-12 09:00:00" : classScheduleVO.getStartTime()%>"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td>結束時間:</td>
                    <td><input type="TEXT" name="endTime" size="50"
                               value="<%= (classScheduleVO==null)? "2023-01-12 12:00:00" : classScheduleVO.getEndTime()%>"
                               required/>
                    </td>
                </tr>


            </table>
            <br>
            <input type="hidden" name="action" value="insert">
            <input type="submit" value="送出新增"></FORM>
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

