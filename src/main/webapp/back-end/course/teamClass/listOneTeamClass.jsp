<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ page import="com.musclebeach.teamClass.model.*" %>
<%
    TeamClassVO teamClassVO = (TeamClassVO) request.getAttribute("teamClassVO");
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


        #table-2 tr td {
            color: maroon;

            transition: all 0.5s ease-in-out;
        }


    </style>
</head>
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
            <tr>
                <th>課程編號</th>
                <th>員工編號</th>
                <th>課程類別</th>
                <th>課程名稱</th>
                <th>課程人數上限</th>
                <th>課程內容</th>
                <th>課程狀態</th>
            </tr>
            <tr>
                <td><%=teamClassVO.getClassID()%>
                </td>
                <td><%=teamClassVO.getEmpID()%>
                </td>
                <td><%=teamClassVO.getTypeID()%>
                </td>
                <td><%=teamClassVO.getClassName()%>
                </td>
                <td><%=teamClassVO.getPeopleMax()%>
                </td>
                <td><%=teamClassVO.getClassContent()%>
                </td>
                <td><%=teamClassVO.getClassStatus() == 1 ? "上架" : "下架"%>
                </td>
            </tr>
        </table>
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
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(function () {
            $("#toClass").addClass("active");
            $("#toClass").attr("aria-selected", "true");
        })
    </script>

    </body>
</html>
