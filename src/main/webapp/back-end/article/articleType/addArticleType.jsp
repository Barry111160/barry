<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeVO" %>
<%
    ArticleTypeVO articleTypeVO = (ArticleTypeVO) request.getAttribute("articleTypeVO");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>課程資料新增</title>
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
    <link href="<%=request.getContextPath()%>/back-end/resources/assets/dist/css/bootstrap.min.css"
          rel="stylesheet"/>
    <style type="text/css"></style>
    <link href="<%=request.getContextPath()%>/back-end/resources/index/index.css"
          rel="stylesheet"/>
    <!-- Flaticon Font -->
    <link href="<%=request.getContextPath()%>/back-end/resources/lib/flaticon/font/flaticon.css"
          rel="stylesheet"/>
    <style>
        #table-1 {
            width: 50%;
            margin: 0 auto;
            text-align: center;
            background-color: #1E88E5;
            color: white;
            border-radius: 10px;
        }

        #table-2 {
            width: 50%;
            margin: 0 auto;
        }

        #addArticleTypeForm {
            padding: 20px;
            background-color: #ECEFF1;
            border-radius: 10px;
        }

        .btn-primary {
            background-color: #1E88E5;
            color: white;
            border-radius: 10px;
            float: right;
        }

        .font-weight-bold {
            width: 20%;
        }
    </style>

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
            class="tab-pane fade"
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
            style="border: 0px solid purple"
    >
        <h1>文章類別新增</h1>
        <%-- 錯誤表列 --%>
        <c:if test="${not empty errorMsgs}">
            <font style="color:red">請修正以下錯誤:</font>
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li style="color:red">${message}</li>
                </c:forEach>
            </ul>
        </c:if>
        <div class="container mt-5" id="addArticleTypeForm">
            <form method="post" action="articleType.do"
                  name="form1">
                <h5>類別名稱:<h5>
                    <input type="text" class="form-control" name="typeName" size="40"
                           value="<%= (articleTypeVO==null)? "" : articleTypeVO.getTypeName()%>"
                           placeholder="請輸入類別名稱" required maxlength="10">
                    <input type="hidden" name="action" value="insert">
                    <input type="submit" class="btn btn-dark mt-3" value="送出新增">
            </form>
        </div>


    </div>

    </main>
    <script src="<%=request.getContextPath()%>/back-end/resources/js/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/resources/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/resources/index/sidebars.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(function () {
            $("#toArticle").addClass("active");
            $("#toArticle").attr("aria-selected", "true");
        })
    </script>

</body>
</html>