<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Msucle Beach: 會員登入</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/front-end/member/css/templatemo-training-studio.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/front-end/member/css/font-awesome.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/front-end/member/css/bootstrap4.min.css" rel="stylesheet"
          type="text/css"/>
</head>
<body>
<!-- ***** Preloader Start ***** -->
<div id="js-preloader" class="js-preloader">
    <div class="preloader-inner">
        <span class="dot"></span>
        <div class="dots">
            <span></span> <span></span> <span></span>
        </div>
    </div>
</div>
<!-- ***** Preloader End ***** -->

<!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="#" class="logo"><em>Muscle </em>Beach</a>
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
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/article/article.do"
                      name="loginform">
                    <div class="w-50 form-signin border border-1 rounded mx-auto"
                         style="background: rgba(255, 255, 255, 0.2)">
                        <h1 class="h3 mt-4 mb-4 fw-normal text-white">會員登入</h1>

                        <div class="w-50 form-floating mx-auto mb-4">
                            <input type="text" class="form-control" name="account"
                                   id="inputAccount" placeholder="會員帳號"/>
                        </div>
                        <div class="w-50 form-floating mx-auto mb-4"
                             style="position: relative">
                            <input type="password" class="form-control" name="password"
                                   id="inputPassword" placeholder="請輸入8-20位英數混合密碼"/> <i
                                id="checkEye" class="bi bi-eye-slash"
                                style="position: absolute; top: 27%; right: 5%"></i>
                        </div>
                        <div>
                            <c:if test="${not empty errorAccount}">
                                <c:forEach var="message" items="${errorAccount}">
                                    <div style="color: red; font-size: 20px">${message}</div>
                                </c:forEach>
                            </c:if>
                        </div>
                        <div>
                            <a href="<%=request.getContextPath()%>/front-end/member/forgetPassword.jsp"
                               style="color: aqua;">忘記密碼</a>
                        </div>
                        <input type="hidden" name="action" value="login">
                        <button class="w-50 btn btn-lg btn-warning" type="submit"
                                value="Submit">登 入
                        </button>
                        <p class="mt-3 mb-3 text-white">
                            還沒註冊嗎? 趕快<a href="<%=request.getContextPath()%>/front-end/member/register.jsp"
                                               style="color: aqua;">加入我們</a>
                        </p>
                        <p class="mt-3 mb-4 text-white">&copy; 2022</p>
                    </div>
                </form>
            </main>
        </div>
    </div>
</div>
<!-- ***** Main Banner Area End ***** -->

<!-- jQuery -->
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $("#checkEye").click(function () {
        if ($(this).hasClass("bi-eye-slash")) {
            $("#inputPassword").attr("type", "text");
        } else {
            $("#inputPassword").attr("type", "password");
        }
        $(this).toggleClass("bi-eye-slash").toggleClass("bi-eye");
    });
</script>

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
<script src="<%=request.getContextPath()%>/front-end/member/js/scrollreveal.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/member/js/waypoints.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/member/js/jquery.counterup.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/member/js/imgfix.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/member/js/mixitup.js"></script>
<script src="<%=request.getContextPath()%>/front-end/member/js/accordions.js"></script>

<!-- Global Init -->
<script src="<%=request.getContextPath()%>/front-end/member/js/custom.js"></script>
</body>
</html>