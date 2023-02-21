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
    <title>Msucle Beach: 預約場地</title>

    <link href="<%=request.getContextPath()%>/front-end/room/images/favicon.ico" rel="icon"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/front-end/room/lib/flaticon/font/flaticon.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/front-end/room/css/style.min.css" rel="stylesheet"/>
    <!-- Stepper -->
    <link href="https://cdn.jsdelivr.net/npm/bs-stepper@1.7.0/dist/css/bs-stepper.min.css" rel="stylesheet"/>

</head>
<body class="bg-white">
<!-- Navbar Start -->
<c:if test="${ memVO.memID == null}">
    <%@ include file="/front-end/common/header.jsp" %>
</c:if>
<c:if test="${ memVO.memID!=null}">
    <%@ include file="/front-end/common/headerlogin.jsp" %>
</c:if>
<!-- Navbar End -->

<!-- Main Strat -->
<div style="min-height: 100vh; position: relative; top: 120px">
    <div class="text-center" style="margin-left: 30px">
        <h1 class="display-6">
            <i class="flaticon-training text-danger"></i> Make a Reservation
        </h1>
    </div>
    <form class="container" action="<%=request.getContextPath()%>/roomOrder.do" method="post" name="roomreservation">
        <div class="bs-stepper">
            <div class="bs-stepper-header" role="tablist">
                <div class="step" data-target="#step1">
                    <button type="button" class="step-trigger" role="tab">
                        <span class="bs-stepper-circle">1</span>
                        <span class="bs-stepper-label">場地時間</span>
                    </button>
                </div>
                <div class="line"></div>
                <div class="step" data-target="#step2">
                    <button type="button" class="step-trigger" role="tab">
                        <span class="bs-stepper-circle">2</span>
                        <span class="bs-stepper-label">連絡資訊</span>
                    </button>
                </div>
                <div class="line"></div>
                <div class="step" data-target="#step3">
                    <button type="button" class="step-trigger" role="tab">
                        <span class="bs-stepper-circle">3</span>
                        <span class="bs-stepper-label">前往付款</span>
                    </button>
                </div>
                <div class="line"></div>
                <div class="step" data-target="#step4">
                    <button type="button" class="step-trigger" role="tab">
                        <span class="bs-stepper-circle">4</span>
                        <span class="bs-stepper-label">完成預約</span>
                    </button>
                </div>
            </div>
            <div class="bs-stepper-content" style="padding: 10px 40px 40px 40px">
                <div id="step1" class="content mx-auto" role="tabpanel">

                    <div class="mt-4">
                        <button
                                class="btn btn-danger"
                                type="button"
                                onclick="checkBorrow() && stepper.next()"
                        >
                            下一步
                        </button>
                    </div>
                </div>
                <div id="step2" class="content" role="tabpanel">
                    <div class="mt-4">
                        <button
                                class="btn btn-outline-danger"
                                type="button"
                                onclick="stepper.previous()"
                        >
                            上一步
                        </button>
                        <button
                                class="btn btn-danger"
                                type="button"
                                onclick="checkInfo()"
                        >
                            下一步
                        </button>
                    </div>
                </div>
                <div id="step3" class="content" role="tabpanel">
                    <div class="mt-4">
                        <button
                                class="btn btn-outline-danger"
                                type="button"
                                onclick="stepper.previous()"
                        >
                            上一步
                        </button>
                        <button class="btn btn-danger" type="submit">
                            進行付款
                        </button>
                    </div>
                </div>
                <div id="step4" class="content" role="tabpanel">
                    <div class="alert alert-success">感謝您的預約。 您可以至場地管理查看您的預約訂單🔎</div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- Main End -->

<!-- Footer Start -->
<div
        class="footer container-fluid mt-5 py-5 px-sm-3 px-md-5 text-white position-relative"
        style="top: 150px">
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
            &copy; <a class="text-white font-weight-bold" href="#">Your Site
            Name</a>. All Rights Reserved. Designed by <a
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
<script src="https://cdn.jsdelivr.net/npm/bs-stepper@1.7.0/dist/js/bs-stepper.min.js"></script>
<script>
    var stepperElem = document.querySelector(".bs-stepper");
    var stepper = new Stepper(stepperElem);
    stepper.to(4);
    var done = false;
    var currStep = 1;
    history.pushState(currStep, "");
    //切換到步驟前觸發，呼叫e.preventDefault()可阻止切換
    stepperElem.addEventListener("show.bs-stepper", function (e) {
        if (done) {
            //若程序完成，不再切換
            e.preventDefault();

        }
    });
    //切換到步驟後觸發，e.detail.indexStep為目前步驟序號(從0開始)
    stepperElem.addEventListener("shown.bs-stepper", function (e) {
        var idx = e.detail.indexStep + 1;
        currStep = idx;
        //pushState()記下歷程以支援瀏覽器回上頁功能
        history.pushState(idx, "");
    });
    //瀏覽器上一頁下一頁觸發
    window.onpopstate = function (e) {
        if (e.state && e.state != currStep) stepper.to(e.state);
    };

    //模擬送出表單，註記已完成，不再允許切換步驟
    function simulateSubmit() {
        stepper.next();
        done = true;
    }
</script>
<script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script>
    $("#inputBorrowDate").datetimepicker({
        date: null, //一開始輸入框的日期為空
        format: "YYYY-MM-DD", //日期的顯示格式
        locale: moment.locale("zh-tw"), //國別
        daysOfWeekDisabled: [], //隱藏的天數0為周日6為星期六
        minDate: moment().add(1, "days"), //顯示最小天數
        maxDate: moment().add(33, "days"), //顯示最大天數，30為最大的顯示範圍為一個月為限
        disabledDates: [
            //隱藏的日期
            moment().add(1, "days"), //前一日
            moment().add(2, "days"), //前二日
            moment().add(3, "days"), //前三日
            // "2023-10-10", //特別日期
            // "2023-12-25",
        ],
    });
    const confirmDate = document.querySelector("#ConfirmDate");
    $("#inputBorrowDate").on("dp.change", function (e) {
        confirmDate.value = e.date._d.toLocaleDateString("sv-SE");
        confirmDate.disabled = true;
    });
    const confirmTime = document.querySelector("#ConfirmTime");
    $("#selectBorrowTime").on("change", function () {
        $("#selectBorrowTime option:selected").each(function () {
            $("#ConfirmTime").html($(this).html());
            $("#ConfirmTime").css("background-color", "#e9ecef");
            confirmTime.disabled = true;
        });
    });
    const confirmTel = document.querySelector("#ConfirmTel");
    $("#inputTel").on("input", function () {
        $("#ConfirmTel").html($(this).val().trim());
        confirmTel.disabled = true;
    });
</script>

<!-- 驗證下一步 -->
<script language="javascript">
    const roomID = document.querySelector("#roomID").value;
    const inputBorrowDate = document.querySelector("#inputBorrowDate");
    var borrowDate;
    $("#inputBorrowDate").on("dp.change", function (e) {
        // 使用者所選的日期
        inputBorrowDate.value = e.date._d.toLocaleDateString("sv-SE");
        borrowDate = inputBorrowDate.value;
        //	console.log(roomID);
        //	console.log(borrowDate);
        $.ajax({
            url: "/CGA105G5/getRoomTime",
            type: "POST",
            dataType: 'text',
            data: {roomID: roomID, borrowDate: borrowDate},
            success: function (response) { // 取得可預約字串回應
                //	console.log(typeof(response));

                var a = response[0];
                var b = response[1];
                var c = response[2];

                if (a === '0') { // set morning to available
                    $("#morning").prop("disabled", false);
                    $("#morning").addClass("font-weight-bold");
                }
                if (b === '0') { // set morning to noon
                    $("#noon").prop("disabled", false);
                    $("#noon").addClass("font-weight-bold");
                }
                if (c === '0') { // set morning to night
                    $("#night").prop("disabled", false);
                    $("#night").addClass("font-weight-bold");
                }

            },
            error: function () {
                Swal.fire({
                    icon: 'info',
                    title: '系統忙碌中⛔取得可預約時段失敗',
                })
            }
        })
    });

    function checkBorrow() {
        if (roomreservation.borrowDate.value == "") {
            Swal.fire("請選擇租用日期");
            roomreservation.borrowDate.focus();
            return false;
        }
        if (roomreservation.borrowTime.value == "") {
            Swal.fire("請選擇租用時段");
            return false;
        }
        return true;
    }

    function checkInfo() {
        var phonereg = /^09\d{8}$/;
        const tel = document.querySelector("#inputTel").value;
        const checkbox = document.querySelector("#flexCheckDefault");
        if (tel.trim().length === 0 || !phonereg.test(tel)) {
            return Swal.fire("請輸入正確的手機號碼格式:09xxxxxxxx");
        }
        if (!checkbox.checked) {
            return Swal.fire("請閱讀並勾選同意服務條款");
        }
        return stepper.next();
    }
</script>
<script>
    $(function () {
        $("#toRoom").addClass("active");
        $("#toRoom").attr("aria-selected", "true");
    })
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/room/lib/easing/easing.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/room/lib/waypoints/waypoints.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-lpyLfhYuitXl2zRZ5Bn2fqnhNAKOAaM/0Kr9laMspuaMiZfGmfwRNFh8HlMy49eQ"
        crossorigin="anonymous"></script>

<!-- Contact Javascript File -->
<script src="<%=request.getContextPath()%>/front-end/room/mail/jqBootstrapValidation.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/room/mail/contact.js"></script>

<!-- Template Javascript -->
<script src="<%=request.getContextPath()%>/front-end/room/js/main.js"></script>
</body>
</html>