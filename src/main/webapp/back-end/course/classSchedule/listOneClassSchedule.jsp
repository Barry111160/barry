<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
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

    <style>
        table#table-1 {
            width: 95%;
            background-color: brown;
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

    <style>
        div#v-pills-class {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
        }

        table#table-2 {
            width: 100%;
            border: 2px solid black;
            text-align: center;
        }

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

        #table-2 td {
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
            height: 35px;
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


        <table id="table-2">
            <tr>
                <th>時間編號</th>
                <th>課程編號</th>
                <th>開始時間</th>
                <th>結束時間</th>


            </tr>
            <tr>
                <td><%=classScheduleVO.getTimeID()%>
                </td>
                <td><%=classScheduleVO.getClassID()%>
                </td>
                <td><%=classScheduleVO.getStartTime()%>
                </td>
                <td><%=classScheduleVO.getEndTime()%>
                </td>
                <td>
                    <FORM METHOD="post"
                          ACTION="classSchedule.do"
                          style="margin-bottom: 0px;">
                        <input type="submit" value="課程點名表">
                        <input type="hidden" name="time_id" value="<%=classScheduleVO.getTimeID()%>">
                        <input type="hidden" name="action" value="listClassSchedules_ByCompositeQuery"></FORM>
                </td>
            </tr>
        </table>
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