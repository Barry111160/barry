<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.articleType.model.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeService" %>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeVO" %>

<%
    ApplicationContext ctx = ApplicationContextUtil.getContext();
    assert ctx != null;
    ArticleTypeService articleTypeSvc = ctx.getBean(ArticleTypeService.class);
    List<ArticleTypeVO> list = articleTypeSvc.getAll();
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
    <title>所有文章類別</title>
    <style>


        td {
            max-width: 80%;
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
        <
        style >
        #table-2 {
            margin: 20px auto;
            width: 80%;
            border-collapse: collapse;
        }

        #table-2 thead tr th {
            text-align: center;
            background-color: #f8f9fa;
            padding: 10px;
            border: 1px solid #ddd;
        }

        #table-2 tbody tr td {
            text-align: center;
            padding: 10px;
            border: 1px solid #ddd;
        }

        #table-2 tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        button {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: 0;
            border-radius: 5px;
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
            style="border: 0px solid rgb(253, 250, 66) ;width: 100%;height: 100%"
       
    >
        <div class="d-flex justify-content-between mb-3">
            <button class="btn btn-dark"
                    onclick="location.href='<%=request.getContextPath()%>/back-end/article/articleType/addArticleType.jsp';">
                新增文章類別
            </button>
        </div>
        <table id="table-2" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>類別編號</th>
                <th>類別名稱</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="articleTypeVO" items="${list}">
                <tr>
                    <td>${articleTypeVO.typeID}</td>
                    <td>${articleTypeVO.typeName}</td>
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