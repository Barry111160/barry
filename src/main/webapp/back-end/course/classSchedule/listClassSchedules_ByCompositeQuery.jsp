<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.musclebeach.classSchedule.model.*" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%
    ApplicationContext ctx = ApplicationContextUtil.getContext();
    assert ctx != null;
    ClassScheduleService classScheduleService = ctx.getBean(ClassScheduleService.class);
%>
<jsp:useBean id="listClassSchedules_ByCompositeQuery" scope="request"
             type="java.util.List<com.musclebeach.classSchedule.model.ClassScheduleVO>"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Muscle Beach 後台首頁</title>
    <link rel="canonical"
          href="https://getbootstrap.com/docs/5.3/examples/headers/"/>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"/>
    <link rel="canonical"
          href="https://getbootstrap.com/docs/5.3/examples/sidebars/"/>
    <link
            href="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/assets/dist/css/bootstrap.min.css"
            rel="stylesheet"/>
    <style type="text/css"></style>
    <link
            href="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/index/index.css"
            rel="stylesheet"/>
    <!-- Flaticon Font -->
    <link
            href="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/lib/flaticon/font/flaticon.css"
            rel="stylesheet"/>
    <!-- DataTables  -->
    <link rel="stylesheet"
          href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">


    <!-- jq DataTables -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script
            src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
    <title>所有課程資料</title>
    <style>


        td {
            max-width: 250px;
            /* 設置最大寬度 */
            overflow: hidden;
            /* 隱藏超出部分 */
            text-overflow: ellipsis;
            /* 添加省略號 */
            white-space: nowrap;
            /* 不換行 */
            color: maroon;
        }

        .showTd {
            max-width: none;
            overflow: visible;
            white-space: normal;
            /* 換行 */
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
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        th {
            background-color: #9D7553;
            color: #DABEA7;

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
            border-left: 5px solid rgba(79, 192, 210, 0.6);
            transition: all 0.5s ease-in-out;
        }


        #table-2 tr {
            border-bottom: 1px solid #2a2a2a;
        }

        #table-2 tr:last-of-type {
            border-bottom: none;
        }


        #show {
            cursor: pointer;
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
            <thead>
            <tr>
                <th>時間編號</th>
                <th>課程編號</th>
                <th>開始時間</th>
                <th>結束時間</th>
                <th>會員編號</th>
                <th>團課點名</th>
            </tr>
            </thead>
            <%--                 <%@ include file="page1_ByCompositeQuery.file" %> --%>
            <tbody>
            <c:forEach var="classScheduleVO" items="${listClassSchedules_ByCompositeQuery}">
                <%--                 begin="<%=pageIndex%>"  end="<%=pageIndex+rowsPerPage-1%>"> --%>
                <tr>
                    <td>${classScheduleVO.timeID}</td>
                    <td>${classScheduleVO.teamClassVO.className}</td>
                    <td>${classScheduleVO.startTime}</td>
                    <td>${classScheduleVO.endTime}</td>
                    <td>${classScheduleVO.memID}</td>

                    <td>
                        <FORM METHOD="post"
                              ACTION="<%=request.getContextPath()%>/back-end/absentMember/absentMember.do"
                              style="margin-bottom: 0px;">
                            <input type="submit" value="缺席">
                            <input type="hidden" name="classID" value="${classScheduleVO.classID}">
                            <input type="hidden" name="timeID" value="${classScheduleVO.timeID}">
                            <input type="hidden" name="memID" value="${classScheduleVO.memID}">
                            <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                            <!--送出本網頁的路徑給Controller-->
                                <%--                                 <input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
                            <!--送出當前是第幾頁給Controller-->
                            <input type="hidden" name="action" value="insertandgetCount">
                        </FORM>
                    </td>
                    <!-- 			<td> -->
                        <%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
                    <!-- 			     <input type="submit" value="刪除"> -->
                        <%-- 			     <input type="hidden" name="empno"      value="${empVO.empno}"> --%>
                        <%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
                        <%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
                    <!-- 			     <input type="hidden" name="action"     value="delete"></FORM> -->
                    <!-- 			</td> -->
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--             <%@ include file="page2_ByCompositeQuery.file" %> --%>
    </div>
    </main>
    <script src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/js/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/assets/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/index/sidebars.js"></script>
    <script>
        $(document).ready(function () {
            $('#table-2').DataTable({
                "searching": true,
                "ordering": true,
                language: {
                    "lengthMenu": "顯示 _MENU_ 筆資料",
                    "sProcessing": "處理中...",
                    "sZeroRecords": "没有查詢到结果",
                    "sInfo": "目前有 _MAX_ 筆資料",
                    "sInfoEmpty": "目前共有 0 筆紀錄",
                    "sInfoFiltered": " ",
                    "sInfoPostFix": "",
                    "sSearch": "搜尋:",
                    "sUrl": "",
                    "sEmptyTable": "尚未有資料紀錄存在",
                    "sLoadingRecords": "載入資料中...",
                    "sInfoThousands": ",",
                    "oPaginate": {
                        "sFirst": "首頁",
                        "sPrevious": "上一頁",
                        "sNext": "下一頁",
                        "sLast": "末頁"
                    },
                    "order": [[0, "desc"]],
                    "oAria": {
                        "sSortAscending": ": 以升序排列此列",
                        "sSortDescending": ": 以降序排列此列"
                    }
                },
            });
        });
    </script>
    <script>
        $(function () {
            $("#toClass").addClass("active");
            $("#toClass").attr("aria-selected", "true");
        })
    </script>
    <!-- <h4> -->
    <!-- ☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br> -->
    <!-- ☆此頁作為複合查詢時之結果練習，<font color=red>已增加分頁、送出修改、刪除之功能</font></h4> -->


    <!-- <br>本網頁的路徑:<br><b> -->
    <%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
    <%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>

</body>
</html>