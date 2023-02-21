<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.mem.model.MemVO" %>

<%
    MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
%>
<!DOCTYPE html>
<html>
<head>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="Free Website Template" name="keywords"/>
    <meta content="Free Website Template" name="description"/>
    <title>Msucle Beach: 會員資料</title>

    <link href="../images/favicon.ico" rel="icon"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/front-end/member/lib/flaticon/font/flaticon.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/front-end/member/css/style.min.css" rel="stylesheet"/>

</head>
<body class="bg-white" id="body">
<!-- Navbar Start -->
<c:if test="${ memVO.memID == null}">
    <%@ include file="/front-end/common/header.jsp" %>
</c:if>
<c:if test="${ memVO.memID!=null}">
    <%@ include file="/front-end/common/headerlogin.jsp" %>
</c:if>
<!-- Navbar End -->

<!-- Main Strat -->
<div style="height: 400px; position: relative; top: 100px">
    <div style="margin-left: 30px">
        <h1 class="display-6">
            <i class="flaticon-barbell text-danger"></i>會員資料
        </h1>
    </div>
    <div class="container" style="width: 1500px">
        <div class="row">
            <div class="col-md-1">
                <label for="inputId" class="form-label">會員編號</label> <input
                    type="text" class="form-control" id="showId"
                    value="<%=memVO.getMemID()%>" disabled readonly/>
            </div>
            <div class="col-md-3">
                <label for="inputName" class="form-label">會員姓名</label> <input
                    type="text" class="form-control" id="showName" name="memName"
                    value="<%=memVO.getMemName()%>" disabled readonly/>
            </div>
            <div class="col-md-3">
                <label for="inputAccount" class="form-label">帳號</label> <input
                    type="text" class="form-control" id="showAccount"
                    value="<%=memVO.getAccount()%>" disabled readonly/>
            </div>
            <div class="col-md-2">
                <label for="showAccess" class="form-label">會員資格</label>
                <%
                    if (memVO.getMemAccess() == 0) {
                %>
                <input type="text" class="form-control" id="showAccess"
                       value="一般會員" disabled readonly/>
                <%
                } else if (memVO.getMemAccess() == 1) {
                %>
                <input type="text" class="form-control" id="showAccess"
                       value="健身會員" disabled readonly/>
                <%
                } else {
                %>
                <input type="text" class="form-control" id="showAccess"
                       value="停課會員" disabled readonly/>
                <%
                    }
                %>
            </div>
            <%
                if (memVO.getMemAccess() == 1) {
            %>
            <div class="col-md-3">
                <label for="showMembership" class="form-label">會籍有效期限</label> <input
                    type="text" class="form-control" id="showMembership"
                    value="<%=memVO.getMembership()%>" disabled readonly/>
            </div>
            <%
                }
            %>
        </div>
        <div class="row mt-3">
            <div class="col-6 col-md-3">
                <label for="inputTel" class="form-label">手機</label> <input
                    type="text" class="form-control" id="showTel" name="memPhone"
                    value="<%=memVO.getMemPhone()%>" disabled readonly/>
            </div>
            <div class="col-6 col-md-3">
                <label for="inputBirth" class="form-label">生日</label> <input
                    type="text" class="form-control" id="showBirth"
                    value="<%=memVO.getMemBirthday()%>" disabled readonly/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-12 col-md-6">
                <label for="inputEmail" class="form-label">信箱</label> <input
                    type="email" class="form-control" id="showEmail" name="memMail"
                    value="<%=memVO.getMemMail()%>" disabled readonly/>
            </div>
            <div class="col-12 col-md-8 mt-3">
                <label for="inputAddress" class="form-label">通訊地址</label> <input
                    type="text" class="form-control" id="showAddress"
                    name="memAddress" value="<%=memVO.getMemAddress()%>" disabled
                    readonly/>
            </div>

        </div>
        <div class="row mt-4">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#staticBackdrop">修改密碼
            </button>

            <!-- Modal -->
            <form>
                <div class="modal fade" id="staticBackdrop"
                     data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog" style="width: 350px">
                        <div class="modal-content position-relative">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel"></h5>
                            </div>
                            <div class="modal-body w-75 mx-auto">
                                <label for="inputOldPassword" class="form-label">請輸入舊密碼</label>
                                <input type="password" class="form-control" id="inputOldPassword" name="oldPassword"
                                       required/>
                                <label for="inputNewPassword" class="form-label mt-2">請輸入新密碼</label>
                                <input type="password" class="form-control" id="inputNewPassword" name="newPassword"/>
                                <label for="ConfirmPassword" class="form-label mt-2">請確認新密碼</label>
                                <input type="password" class="form-control" id="ConfirmPassword"
                                       name="ConfirmPassword"/>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                <input type="button" id="submitBotton" class="btn btn-primary" value="確定變更">
                                <input type="hidden" id="memberPassword" value="<%=memVO.getPassword()%>">
                                <input type="hidden" id="memberId" value="<%=memVO.getMemID()%>">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <a class="btn btn-outline-primary" href="<%=request.getContextPath()%>/front-end/member/mem_info_update.jsp"
               role="button">修改資料</a>
        </div>
    </div>
    <!-- Main End -->

    <!-- Footer Start -->
    <div
            class="footer container-fluid mt-3 py-5 px-sm-3 px-md-5 text-white"
            style="position: relative; top: 30px">
        <div style="display: inline; padding: 0px">
            <div class="col-lg-0 col-md-10" style="margin: auto">
                <h5 class="text-white mb-4">聯絡我們</h5>
                <p style="display: inline-block">
                    <i class="fa fa-map-marker-alt mr-2"></i>住址
                </p>
                <p style="display: inline-block; position: absolute; right: 420px">
                    <i class="fa fa-phone-alt mr-2"></i>0987-654-321
                </p>
                <p style="display: inline; float: right" ;>
                    <i class="fa fa-envelope mr-2"></i>abc信箱@gmail.com
                </p>
            </div>
        </div>
        <div class="container border-top border-dark pt-2">
            <p class="m-0 text-center text-white">
                &copy; <a class="text-white font-weight-bold" href="#">Your
                Site Name</a>. All Rights Reserved. Designed by <a
                    class="text-white font-weight-bold" href="https://htmlcodex.com">HTML
                Codex</a>
            </p>
        </div>
    </div>
    <!-- Footer End -->

    <!-- Back to Top -->
    <a href="#" class="btn btn-outline-primary back-to-top"><i
            class="fa fa-angle-double-up"></i></a>

    <!-- JavaScript Libraries -->
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/member/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/member/lib/waypoints/waypoints.min.js"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-lpyLfhYuitXl2zRZ5Bn2fqnhNAKOAaM/0Kr9laMspuaMiZfGmfwRNFh8HlMy49eQ"
            crossorigin="anonymous"></script>
    <!-- Contact Javascript File -->
    <script src="<%=request.getContextPath()%>/front-end/member/mail/jqBootstrapValidation.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/member/mail/contact.js"></script>
    <!-- Template Javascript -->
    <script src="<%=request.getContextPath()%>/front-end/member/js/main.js"></script>
    <script>
        $("#submitBotton").click(function () {
            const regrex = /^(?=.*\d)(?=.*[a-zA-Z]).{8,}$/
            var memberPassword = $("#memberPassword").val();
            var oldPassword = $("#inputOldPassword").val();
            var newPassword = $("#inputNewPassword").val();
            var confirmPassword = $("#ConfirmPassword").val();
            var memberId = $("#memberId").val();
            if (memberPassword === "" || oldPassword === "" || newPassword === "") {
                Swal.fire({
                    icon: 'info',
                    title: '輸入資料不完整',
                })
            } else if (!regrex.test(newPassword)) {
                Swal.fire({
                    icon: 'info',
                    title: '請輸入正確的密碼格式:\n含英數至少8個字元',
                })
            } else if (confirmPassword !== newPassword) {
                Swal.fire({
                    icon: 'info',
                    title: '密碼不吻合',
                })
            } else if (memberPassword === oldPassword) {
                $.ajax({
                    url: `<%=request.getContextPath()%>/updatePassword`,
                    type: "POST",
                    data: {newPassword: newPassword, memberId: memberId},
                    success: function (response) {
                        $("#staticBackdrop").removeClass("show"); // hide modal
                        $("#staticBackdrop").css("display", "none");
                        $("#staticBackdrop").attr("aria-hidden", "true");
                        $("#staticBackdrop").removeAttr("aria-modal");
                        $("#staticBackdrop").removeAttr("role");
                        $("#body").removeAttr("style");
                        $("#body").removeClass("modal-open"); // hide modal
                        Swal.fire({
                            icon: 'info',
                            title: response,
                        })
                        $(".modal-backdrop").remove(); // remove background
                    },
                    error: function () {
                        $("#staticBackdrop").removeClass("show");
                        $("#staticBackdrop").css("display", "none");
                        $("#staticBackdrop").attr("aria-hidden", "true");
                        Swal.fire({
                            icon: 'info',
                            title: '系統忙碌中⛔變更失敗',
                        })
                        $(".modal-backdrop").remove(); // remove background
                    }
                })
            } else {
                Swal.fire({
                    icon: 'info',
                    title: '舊密碼不符合，請重新填入',
                })
            }
        }) // end function
    </script>
    <script>
        $(function () {
            $("#toMem").addClass("active");
            $("#toMem").attr("aria-selected", "true");
        })
    </script>
    <script>
        $(function () {
            $("#toMeminfo").addClass("active");
            $("#toMeminfo").attr("aria-selected", "true");
        })
    </script>
</body>
</html>