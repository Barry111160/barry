<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.articleReport.model.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>

<%
    ApplicationContext ctx = ApplicationContextUtil.getContext();
    assert ctx != null;
    ArticleReportService articleReportSvc = ctx.getBean(ArticleReportService.class);
    List<ArticleReportVO> list = articleReportSvc.getAll();
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
            href="<%=request.getContextPath()%>/back-end/resources/assets/dist/css/bootstrap.min.css"
            rel="stylesheet"/>
    <style type="text/css"></style>
    <link
            href="<%=request.getContextPath()%>/back-end/resources/index/index.css"
            rel="stylesheet"/>
    <!-- Flaticon Font -->
    <link
            href="<%=request.getContextPath()%>/back-end/resources/lib/flaticon/font/flaticon.css"
            rel="stylesheet"/>
    <!-- DataTables  -->
    <link rel="stylesheet"
          href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">


    <!-- jq DataTables -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script
            src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
    <title>所有文章檢舉</title>
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
        }

        .showTd {
            max-width: none;
            overflow: visible;
            white-space: normal;
            /* 換行 */
        }
    </style>

    <style>
        #table-2 {
            width: 100%;
            margin: 20px 0;
            border-collapse: collapse;
            text-align: center;
        }

        #table-2 thead tr {
            background-color: #333;
            color: #fff;
        }

        #table-2 th,
        #table-2 td {
            padding: 10px;
            border: 1px solid #333;
        }

        #table-2 tbody tr:nth-child(even) {
            background-color: #ddd;
        }

        #table-2 tbody tr:hover {
            background-color: #eee;
            cursor: pointer;
        }

        #table-2 thead tr th {
            text-align: center;
        }

        .show input[type="submit"] {
            background-color: #333;
            color: #fff;
            padding: 5px 10px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }

        .show input[type="submit"]:hover {
            background-color: #fff;
            color: #333;
        }

        select {
            padding: 5px 10px;
            border-radius: 5px;
            border: 1px solid #333;
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

    <!-- ========================================= 論壇管理 ========================================= -->
    <div
            class="tab-pane fade show active"
            id="v-pills-article"
            role="tabpanel"
            aria-labelledby="v-pills-article-tab"
            tabindex="0"
            style="border: 0px solid rgb(253, 250, 66) width:100%;"
    >


        <table id="table-2" class="table table-striped table-bordered" style="width:1029px">
            <thead>
            <tr>
                <th>檢舉編號</th>
                <th>文章編號</th>
                <th>會員編號</th>
                <th>檢舉內容</th>
                <th>檢舉時間</th>
                <th>檢舉處理狀態</th>
                <th>文章詳情</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="articleReportVO" items="${list}">
                <tr>
                    <td>${articleReportVO.reportID}</td>
                    <td>${articleReportVO.artID}</td>
                    <td>${articleReportVO.memID}</td>
                    <td>${articleReportVO.reportContent}</td>
                    <td>${articleReportVO.reportStime}</td>
                    <td>
                        <form method="post"
                              action="<%=request.getContextPath()%>/front-end/article/articleReport.do"
                              style="margin-bottom: 0px;">
                            <select name="reportStatus">
                                <option value="0"${(articleReportVO.reportStatus==0)?'selected':'' }>待審核</option>
                                <option value="1"${(articleReportVO.reportStatus==1)?'selected':'' }>已通過</option>
                                <option value="2"${(articleReportVO.reportStatus==2)?'selected':'' }>未通過</option>
                            </select>
                            <input type="hidden" name="reportID" value="${articleReportVO.reportID}">
                            <input type="hidden" name="action" value="update">
                            <input type="submit" value="確定修改">
                        </form>
                    </td>
                    <td class="show">
                        <form method="post"
                              action="<%=request.getContextPath()%>/front-end/article/articleReport.do"
                              style="margin-bottom: 0px;">
                            <input type="hidden" name="artID" value="${articleReportVO.artID}">
                            <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                            <input type="hidden" name="action" value="getOneArticle_For_Display">
                            <input type="submit" value="文章詳情">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="d-flex justify-content-between mb-3">
            <button class="btn btn-dark"
                    onclick="location.href='<%=request.getContextPath()%>/back-end/article/articleType/listAllArticleType.jsp';">
                檢視文章類別
            </button>
        </div>

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
    </div>
    </main>
    <script
            src="<%=request.getContextPath()%>/back-end/resources/js/popper.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/back-end/resources/js/bootstrap.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/back-end/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/back-end/resources/index/sidebars.js"></script>

    <script>
        $(function () {
            $("#toArticle").addClass("active");
            $("#toArticle").attr("aria-selected", "true");
        })
    </script>

</body>
</html>