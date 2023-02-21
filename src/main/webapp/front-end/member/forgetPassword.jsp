<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Msucle Beach: 忘記密碼</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet"/>
    <link href="css/templatemo-training-studio.css" rel="stylesheet"/>
    <link href="css/font-awesome.css" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap4.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="<%=request.getContextPath()%>/front-end/index.html" class="logo"><em>Muscle </em>Beach</a>
                    <!-- ***** Logo End ***** -->
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- ***** Header Area End ***** -->

<!-- ***** Main Banner Area Start ***** -->
<div class="main-banner" id="top">
    <video autoplay muted loop id="bg-video">
        <source src="<%=request.getContextPath()%>/front-end/member/images/gym-video.mp4"
                type="video/mp4" style="height: 100%"/>
    </video>

    <div class="video-overlay header-text" style="min-height: 100vh;">
        <div class="caption mt-5">
            <h6>work harder, get stronger</h6>
            <main class="w-100 mt-3">
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/mem.do"
                      name="passwordform" onsubmit="alert('重設密碼信件已寄出！')">
                    <div class="w-50 form-signin border border-1 rounded mx-auto"
                         style="background: rgba(255, 255, 255, 0.2)">
                        <h1 class="h3 mt-4 mb-4 fw-normal text-white">忘記密碼</h1>

                        <div class="w-50 form-floating mx-auto mb-4">
                            <input type="text" class="form-control" name="account" id="inputAccount"
                                   placeholder="會員帳號"/>
                        </div>
                        <div class="w-75 form-floating mx-auto mb-4"
                             style="position: relative">
                            <input type="email" class="form-control" name="memMail" id="inputEmail"
                                   placeholder="請輸入註冊信箱"/>
                        </div>
                        <div>
                            <c:if test="${not empty errorAccount}">
                                <c:forEach var="message" items="${errorAccount}">
                                    <div style="color: red; font-size: 20px">${message}</div>
                                </c:forEach>
                            </c:if>
                        </div>
                        <div>
                            <a href="<%=request.getContextPath()%>/front-end/member/login.jsp"
                               style="color: aqua;">回登入頁</a>
                        </div>
                        <input type="hidden" name="action" value="forget_password">
                        <button class="w-50 btn btn-lg btn-danger" type="submit">
                            確認送出
                        </button>
                        <p class="mt-3 mb-3 text-white">請至註冊信箱點擊連結，以便更改密碼</p>
                        <p class="mt-2 mb-4 text-white">&copy; 2022</p>
                    </div>
                </form>
            </main>
        </div>
    </div>
</div>
<!-- ***** Main Banner Area End ***** -->

<!-- Bootstrap -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
        integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
        crossorigin="anonymous"></script>

<!-- Plugins -->
<script src="js/scrollreveal.min.js"></script>
<script src="js/waypoints.min.js"></script>
<script src="js/jquery.counterup.min.js"></script>
<script src="js/imgfix.min.js"></script>
<script src="js/mixitup.js"></script>
<script src="js/accordions.js"></script>

<!-- Global Init -->
<script src="js/custom.js"></script>
</body>
</html>