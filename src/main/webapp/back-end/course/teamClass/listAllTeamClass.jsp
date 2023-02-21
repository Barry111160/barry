<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.teamClass.model.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>

<%
    ApplicationContext ctx = ApplicationContextUtil.getContext();
    assert ctx != null;
    TeamClassService teamClassSvc = ctx.getBean(TeamClassService.class);
    List<TeamClassVO> list = teamClassSvc.getAll();
    pageContext.setAttribute("list", list);
%>

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

        /*         #table-2 tr:hover .show { */
        /*             display: block; */

        /*         } */

        /*         #table-2:hover .showth { */
        /*             display: block; */

        /*         } */

        /*         #table-2 tr { */
        /*             border-bottom: 1px solid #2a2a2a; */
        /*         } */

        /*         #table-2 tr:last-of-type { */
        /*             border-bottom: none; */
        /*         } */

        /*         .show { */
        /*             display: none; */
        /*         } */

        /*         .showth { */
        /*             display: none; */
        /*         } */

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
    <div class="tab-pane fade" id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0">
        <img src="/image/welcome.gif" style="width: 96%" alt=""/>
    </div>

    <!-- ========================================= 課程管理 ========================================= -->
    <div class="tab-pane fade show active" id="v-pills-class"
         role="tabpanel" aria-labelledby="v-pills-class-tab" tabindex="0"
         style="border: 2px solid purple">


        <table id="table-2">
            <thead>
            <tr>
                <th>課程編號</th>
                <th>員工編號</th>
                <th>課程類別</th>
                <th>課程名稱</th>
                <th>人數上限</th>
                <th>課程內容</th>
                <th>課程狀態</th>
                <th class="showth">修改</th>
                <th class="showth">刪除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="TeamClassVO" items="${list}">
                <tr>
                    <td>${TeamClassVO.classID}</td>
                    <td>${TeamClassVO.empID}</td>
                    <td>${TeamClassVO.typeID}</td>
                    <td>${TeamClassVO.className}</td>
                    <td>${TeamClassVO.peopleMax}</td>
                    <td>${TeamClassVO.classContent}</td>
                    <td>${TeamClassVO.classStatus==1?"上架":"下架"}</td>
                    <td class="show">
                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/course/teamClass/teamClass.do"
                              style="margin-bottom: 0px;">
                            <input id="show2" type="submit" value="修改"> <input
                                type="hidden" name="classID" value="${TeamClassVO.classID}">
                            <input type="hidden" name="action" value="getOne_For_Update">
                        </FORM>
                    </td>
                    <td class="show">
                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/course/teamClass/teamClass.do"
                              style="margin-bottom: 0px;">
                            <input id="show2" type="submit" value="刪除"> <input
                                type="hidden" name="classID" value="${TeamClassVO.classID}">
                            <input type="hidden" name="action" value="delete">
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
            $(document).on('click', 'td', function () {
                $(this).toggleClass('showTd');
            });
        </script>
    </div>
    </main>
    <script
            src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/js/popper.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/js/bootstrap.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/assets/dist/js/bootstrap.bundle.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/back-end/course/resources/Back_end_workspace/index/sidebars.js"></script>

    <script>
        $(function () {
            $("#toClass").addClass("active");
            $("#toClass").attr("aria-selected", "true");
        })
    </script>

</body>
</html>