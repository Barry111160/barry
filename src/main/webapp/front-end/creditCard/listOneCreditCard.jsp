<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ page import="com.musclebeach.creditCard.model.*" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    CreditCardVO creditCardVO = (CreditCardVO) request.getAttribute("creditCardVO");
%>

<html>
<head>
    <title>信用卡資料 - listOneCreditCard.jsp</title>

    <style>
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>信用卡資料 - ListOneCreditCard.jsp</h3>
            <h4><a href="select_page.jsp">
                <width
                ="100" height="32" border="0">回首頁</a></h4>
        </td>
    </tr>
</table>

<table>
    <tr>
        <th>信用卡編號</th>
        <th>會員編號</th>
        <th>信用卡卡號</th>
        <th>持卡人姓名</th>
        <th>信用卡期限</th>
        <th>驗證碼</th>
    </tr>
    <tr>
        <td><%=creditCardVO.getCcID()%>
        </td>
        <td><%=creditCardVO.getMemID()%>
        </td>
        <td><%=creditCardVO.getCcNumber()%>
        </td>
        <td><%=creditCardVO.getCcName()%>
        </td>
        <td><%=creditCardVO.getCcTime()%>
        </td>
        <td><%=creditCardVO.getCcvc()%>
        </td>

    </tr>
</table>

</body>
</html>