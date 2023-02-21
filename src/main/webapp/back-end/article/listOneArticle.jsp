<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.musclebeach.article.model.*" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
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
    <title>所有課程資料</title>
    <style>
        div#v-pills-article {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
        }

        table#table-1 {
            background-color: #CCCCFF;
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
        table {
            width: 800px;
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
    </style>

</head>
<body bgcolor='white'>
<%@ include file="../header_sidebar.jsp" %>
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
            style="border: 2px solid rgb(253, 250, 66)"
    >


        <table>
            <thead>
            <tr>
                <th>文章編號</th>
                <th>會員編號</th>
                <th>文章類別編號</th>
                <th>文章標題</th>
                <th>文章內容</th>
                <th>發表時間</th>
                <th>最後編輯時間</th>
                <th>文章狀態</th>

            </tr>
            </thead>
            <tbody>

                <tr>
                    <td>${articleVO.artID}</td>
                    <td>${articleVO.memID}</td>
                    <td>${articleVO.typeID}</td>
                    <td>${articleVO.artTitle}</td>
                    <td>${articleVO.artContent}</td>
                    <td>${articleVO.artStime}</td>
                    <td>${articleVO.artLtime}</td>
                    <td>
                        <FORM METHOD="post" ACTION="article.do" name="form1"
                              style="margin-bottom: 0px;">
                            <select name="artStatus">
                                <option value="1"${(articleVO.artStatus==1)?'selected':'' }>顯示</option>
                                <option value="0"${(articleVO.artStatus==0)?'selected':'' }>隱藏</option>
                            </select>
                    </td>

                    <td>

                        <input type="hidden" name="action" value="updateStatus">
                        <input type="hidden" name="artID" value="<%=articleVO.getArtID()%>">
                        <input type="hidden" name="memID" value="<%=articleVO.getMemID()%>">
                        <input type="hidden" name="typeID" value="<%=articleVO.getTypeID()%>">
                        <input type="hidden" name="artTitle" value="<%=articleVO.getArtTitle()%>">
                        <input type="hidden" name="artContent" value="<%=articleVO.getArtContent()%>">
                        <input type="hidden" name="artStime" value="<%=articleVO.getArtStime()%>">
                        <input type="hidden" name="artLtime" value="<%=articleVO.getArtLtime()%>">
                        <input type="submit" value="送出修改">
                        </FORM>
                    </td>


                </tr>

            </tbody>
        </table>

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
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(function () {
            $("#toArticle").addClass("active");
            $("#toArticle").attr("aria-selected", "true");
        })
    </script>

</body>
</html>