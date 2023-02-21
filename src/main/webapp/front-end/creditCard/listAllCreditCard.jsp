<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.creditCard.model.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%
    ApplicationContext ctx = ApplicationContextUtil.getContext();
    assert ctx != null;
    CreditCardService creditCardSvc = ctx.getBean(CreditCardService.class);
    List<CreditCardVO> list = creditCardSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<html>
<head>
    <title>所有信用卡資料 - listAllEmp.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>所有信用卡資料 - listAllCreditCard.jsp</h3>
            <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
    <%@ include file="page1.file" %>
    <c:forEach var="creditCardVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

        <tr>
            <td>${creditCardVO.ccID}</td>
            <td>${creditCardVO.memID}</td>
            <td>${creditCardVO.ccNumber}</td>
            <td>${creditCardVO.ccName}</td>
            <td>${creditCardVO.ccTime}</td>
            <td>${creditCardVO.ccvc}</td>
            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/creditCard/creditCard.do"
                      style="margin-bottom: 0px;">
                    <input type="submit" value="修改">
                    <input type="hidden" name="ccID" value="${creditCardVO.ccID}">
                    <input type="hidden" name="action" value="getOne_For_Update"></FORM>
            </td>
            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/creditCard/creditCard.do"
                      style="margin-bottom: 0px;">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="ccID" value="${creditCardVO.ccID}">
                    <input type="hidden" name="action" value="delete"></FORM>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>