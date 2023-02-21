<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.musclebeach.creditCard.model.*" %>
<%
    CreditCardVO creditCardVO = (CreditCardVO) request.getAttribute("creditCardVO");

%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- custom css file link  -->
    <link rel="stylesheet" href="css/style.css">
    <style>
        * {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            outline: none;
            border: none;
            text-decoration: none;
            text-transform: uppercase;
        }

        .container {
            min-height: 100vh;
            background: rgb(238, 238, 238);
            display: flex;
            align-items: center;
            justify-content: center;
            flex-flow: column;
            padding-bottom: 60px;
        }

        .container form {
            background: #fff;
            border-radius: 5px;
            box-shadow: 0 10px 15px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 600px;
            padding-top: 160px;
        }

        .container form .inputBox {
            margin-top: 20px;
        }

        .container form .inputBox span {
            display: block;
            color: #999;
            padding-bottom: 5px;
        }

        .container form .inputBox input,
        .container form .inputBox select {
            width: 100%;
            padding: 10px;
            border-radius: 10px;
            border: 1px solid rgba(0, 0, 0, .3);
            color: rgb(68, 68, 68);
        }

        .container form .flexbox {
            display: flex;
            gap: 15px;
        }

        .container form .flexbox .inputBox {
            flex: 1 1 150px;
        }

        .container form .submit-btn {
            width: 100%;
            background: linear-gradient(45deg, rgb(43, 226, 223), rgb(20, 28, 255));
            margin-top: 20px;
            padding: 10px;
            font-size: 20px;
            color: rgb(255, 255, 255);
            border-radius: 10px;
            cursor: pointer;
            transition: .2s linear;
        }

        .container form .submit-btn:hover {
            letter-spacing: 2px;
            opacity: .8;
        }

        .container .card-container {
            margin-bottom: -150px;
            position: relative;
            height: 250px;
            width: 400px;
        }

        .container .card-container .front {
            position: absolute;
            height: 80%;
            width: 100%;
            top: 0;
            left: 0;
            background: linear-gradient(45deg, rgb(43, 125, 226), rgb(20, 204, 255));
            border-radius: 5px;
            backface-visibility: hidden;
            box-shadow: 0 15px 25px rgba(0, 0, 0, 0.2);
            padding: 20px;
            transform: perspective(1000px) rotateY(0deg);
            transition: transform .4s ease-out;
        }

        .container .card-container .front .image {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding-top: 10px;
        }

        .container .card-container .front .image img {
            height: 50px;
        }

        .container .card-container .front .card-number-box {
            padding: 30px 0;
            font-size: 22px;
            color: rgb(255, 255, 255);
        }

        .container .card-container .front .flexbox {
            display: flex;
        }

        .container .card-container .front .flexbox .box:nth-child(1) {
            margin-right: auto;
        }

        .container .card-container .front .flexbox .box {
            font-size: 15px;
            color: rgb(255, 255, 255);
        }

        .container .card-container .back {
            position: absolute;
            top: 0;
            left: 0;
            height: 80%;
            width: 100%;
            background: linear-gradient(45deg, rgb(43, 226, 159), rgb(20, 24, 255));
            border-radius: 5px;
            padding: 20px 0;
            text-align: right;
            backface-visibility: hidden;
            box-shadow: 0 15px 25px rgba(0, 0, 0, 0.2);
            transform: perspective(1000px) rotateY(180deg);
            transition: transform .4s ease-out;
        }

        .container .card-container .back .stripe {
            background: rgb(0, 0, 0);
            width: 100%;
            margin: 10px 0;
            height: 50px;
        }

        .container .card-container .back .box {
            padding: 0 20px;
        }

        .container .card-container .back .box span {
            color: rgb(255, 255, 255);
            font-size: 15px;
        }

        .container .card-container .back .box .cvv-box {
            height: 50px;
            padding: 10px;
            margin-top: 5px;
            color: rgb(51, 51, 51);
            background: rgb(255, 255, 255);
            border-radius: 5px;
            width: 100%;
        }

        .container .card-container .back .box img {
            margin-top: 30px;
            height: 30px;
        }
    </style>
</head>

<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>
<div class="container">

    <div class="card-container">

        <div class="front">
            <div class="image">

            </div>
            <div class="card-number-box">################</div>
            <div class="flexbox">
                <div class="box">
                    <span>card holder</span>
                    <div class="card-holder-name">full name</div>
                </div>
                <div class="box">
                    <span>expires</span>
                    <div class="expiration">
                        <span class="exp-month">mmyy</span>

                    </div>
                </div>
            </div>
        </div>

        <div class="back">
            <div class="stripe"></div>
            <div class="box">
                <span>ccvc</span>
                <div class="cvv-box"></div>
                <img src="image/visa.png" alt="">
            </div>
        </div>

    </div>

    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/creditCard/creditCard.do" name="form1">
        <div class="inputBox">
            <span>會員編號</span>
            <input type="text" maxlength="16" name="memID"
                   value="<%= (creditCardVO==null)? "" : creditCardVO.getMemID()%>" required/>
        </div>
        <div class="inputBox">
            <span>信用卡卡號</span>
            <input type="text" maxlength="16" name="ccNumber" class="card-number-input" pattern="[0-9]{16}"
                   value="<%= (creditCardVO==null)? "" :creditCardVO.getCcNumber()%>" required/>
        </div>
        <div class="inputBox">
            <span>持卡人姓名</span>
            <input type="text" name="ccName" class="card-holder-input"
                   value="<%= (creditCardVO==null)?  "" : creditCardVO.getCcName()%>" required/>
        </div>
        <div class="flexbox">
            <div class="inputBox">
                <span>信用卡期限</span>
                <input type="text" maxlength="4" name="ccTime" class="month-input" pattern=" [0-9]{4}">
                value="<%= (creditCardVO == null) ? "" : creditCardVO.getCcTime()%>" required/>

            </div>

            <div class="inputBox">
                <span>驗證碼</span>
                <input type="text" maxlength="3" name="ccvc" class="cvv-input" pattern=" [0-9]{3}">
                value="<%= (creditCardVO == null) ? "" : creditCardVO.getCcvc()%>" required/>
            </div>
        </div>
        <input type="hidden" name="action1" value="updatememberaccessanddate">
        <input type="hidden" name="action" value="insert">
        <input type="submit" value="確認升級" class="submit-btn">
    </FORM>
</div>


<script>

    document.querySelector('.card-number-input').oninput = () => {
        document.querySelector('.card-number-box').innerText = document.querySelector('.card-number-input').value;
    }

    document.querySelector('.card-holder-input').oninput = () => {
        document.querySelector('.card-holder-name').innerText = document.querySelector('.card-holder-input').value;
    }

    document.querySelector('.month-input').oninput = () => {
        document.querySelector('.exp-month').innerText = document.querySelector('.month-input').value;
    }


    document.querySelector('.cvv-input').onmouseenter = () => {
        document.querySelector('.front').style.transform = 'perspective(1000px) rotateY(-180deg)';
        document.querySelector('.back').style.transform = 'perspective(1000px) rotateY(0deg)';
    }

    document.querySelector('.cvv-input').onmouseleave = () => {
        document.querySelector('.front').style.transform = 'perspective(1000px) rotateY(0deg)';
        document.querySelector('.back').style.transform = 'perspective(1000px) rotateY(180deg)';
    }

    document.querySelector('.cvv-input').oninput = () => {
        document.querySelector('.cvv-box').innerText = document.querySelector('.cvv-input').value;
    }

</script>


</body>

</html>