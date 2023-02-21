<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.coachtime.model.CoachTimeVO" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    CoachTimeVO coachTimeVO = (CoachTimeVO) request.getAttribute("coachTimeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>


<html>
<head>
    <title>員工資料 - listOneEmp.jsp</title>


    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Font Awesome -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
            rel="stylesheet">

    <!-- Flaticon Font -->
    <link href="lib/flaticon/font/flaticon.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.min.css" rel="stylesheet">

    <style>
        .login {
            display: inline;
            position: relative;
            Left: calc(100% - 150px);
            top: 5px;
        }

        .register {
            display: inline;
            position: relative;
            Left: calc(100% - 150px);
            top: 5px;
            color: white;
        }

        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
            width: 100%;
        }

        table#table-2 {
            background-color: cyan;
            border: 3px solid black;
            text-align: center;
            width: 100%;
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
    </style>

</head>
<body bgcolor='white'>


<table id="table-1">
    <tr>
        <td>
            <h3>教練時段資料 - ListOneEmp CoachTime</h3> <a
                href="selectCoachTimePage.jsp">回首頁</a>
        </td>
    </tr>
</table>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table id="table-2">
    <tr>
        <th>建立編號</th>
        <th>教練編號</th>
        <th>可預約時段</th>
        <th>預約日期</th>
    </tr>
    <tr>
        <td>${coachTimeVO.timeID}</td>
        <td>${coachTimeVO.empID}</td>
        <td>${coachTimeVO.coachTime}</td>
        <td>${coachTimeVO.coachDate}</td>
    </tr>
</table>


<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="sidebars.js"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="allcss/lib/easing/easing.min.js"></script>
<script src="allcss/lib/waypoints/waypoints.min.js"></script>
<script src="js/main.js"></script>
<script src="mail/jqBootstrapValidation.min.js"></script>
<script src="mail/contact.js"></script>
<script>
    $(function () {
        $("#toCoach").addClass("active");
        $("#toCoach").attr("aria-selected", "true");
    })
</script>

</body>
</html>