<%@ page import="com.musclebeach.mem.model.MemService" %>
<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>所有會員資料</title>
    <link href="css/index.css" rel="stylesheet"/>
    <link href="css/flaticon.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style type="text/css"></style>
</head>
<body>
<%@include file="../header_sidebar.jsp" %>
<%
    MemService memService = context.getBean(MemService.class);
    List<MemVO> list = memService.getAll();
    pageContext.setAttribute("list", list);
%>
<div class="tab-content" id="v-pills-tabContent" style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade" id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0"></div>
    <!-- ========================================= 員工管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-employee" role="tabpanel"
         aria-labelledby="v-pills-employee-tab" tabindex="0"></div>
    <!-- ========================================= 會員管理 ========================================= -->
    <div class="tab-pane fade show active p-3" id="v-pills-member"
         role="tabpanel" aria-labelledby="v-pills-member-tab" tabindex="0"
         style="margin-top: 10px; width: 100%;">
        <div>
            <div class="d-inline display-6" style="margin-right: 500px;">查詢所有會員資料</div>
            <div class="d-inline">
                <a class="col-2 btn btn-warning mb-4" href="memPage.jsp"
                   role="button">回查詢頁</a>
            </div>
        </div>
        <table class="table table-striped table-bordered table-sm">
            <thead>
            <tr>
                <th scope="col">會員編號</th>
                <th scope="col">會員<br>姓名
                </th>
                <th scope="col">會員帳號</th>
                <th scope="col">會員電話</th>
                <th scope="col" style="width: 100px">出生日期</th>
                <th scope="col" style="">會員地址</th>
                <th scope="col">會員信箱</th>
                <th scope="col">會員狀態</th>
                <th scope="col" style="width: 50px">會員身分</th>
                <th scope="col" style="width: 100px">會籍效期</th>
                <th scope="col">修改<br>狀態
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="memVO" items="${list}" varStatus="i">
                <tr>
                    <th scope="row">${memVO.memID}</th>
                    <td>${memVO.memName}</td>
                    <td>${memVO.account}</td>
                    <td>${memVO.memPhone}</td>
                    <td>${memVO.memBirthday}</td>
                    <td>${memVO.memAddress}</td>
                    <td>${memVO.memMail}</td>

                    <c:if test="${memVO.memStatus == 0}" var="true">
                        <td>未啟用</td>
                    </c:if>
                    <c:if test="${memVO.memStatus == 1}" var="true">
                        <td>啟用</td>
                    </c:if>
                    <c:if test="${memVO.memStatus == 2}" var="true">
                        <td>除籍</td>
                    </c:if>

                    <c:if test="${memVO.memAccess == 0}" var="true">
                        <td>一般會員</td>
                    </c:if>
                    <c:if test="${memVO.memAccess == 1}" var="true">
                        <td>健身會員</td>
                    </c:if>
                    <c:if test="${memVO.memAccess == 2}" var="true">
                        <td>停課會員</td>
                    </c:if>

                    <td>${memVO.membership}</td>

                    <c:if test="${memVO.memStatus == 2}" var="true">
                        <td><input type="button" class="btn btn-secondary"
                                   value="除籍" disabled></td>
                    </c:if>
                    <c:if test="${memVO.memStatus != 2}" var="true">
                        <td>
                            <FORM>
                                <input type="hidden" id="memberId" name="memID"
                                       value="${memVO.memID}"> <input type="hidden"
                                                                      id="memberName" name="memName"
                                                                      value="${memVO.memName}">
                                <input type="button" class="submitBtn btn btn-danger"
                                       value="除籍">
                            </FORM>
                        </td>
                    </c:if>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- ========================================= 商城管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-shop" role="tabpanel"
         aria-labelledby="v-pills-shop-tab" tabindex="0"></div>
    <!-- ========================================= 教練管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-coach" role="tabpanel"
         aria-labelledby="v-pills-coach-tab" tabindex="0"></div>
    <!-- ========================================= 課程管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-class" role="tabpanel"
         aria-labelledby="v-pills-class-tab" tabindex="0"></div>
    <!-- ========================================= 場地管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-room" role="tabpanel"
         aria-labelledby="v-pills-room-tab" tabindex="0"></div>
    <!-- ========================================= 論壇管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-article" role="tabpanel"
         aria-labelledby="v-pills-article-tab" tabindex="0"></div>
    <!-- ========================================= 客服管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-service" role="tabpanel"
         aria-labelledby="v-pills-service-tab" tabindex="0"></div>
</div>
</main>
<!-- JavaScript Libraries -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(".submitBtn").click(function () {
        var currentRow = $(this).closest("tr");
        const memberId = currentRow.find("th:eq(0)").text();
        const memberName = currentRow.find("td:eq(0)").text();

        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            title: '您確定要將此會員除籍?' + '\n' + '會員姓名：' + memberName + '\n'
                + '會員編號：' + memberId,
            text: "注意! 一旦除籍，狀態將永久保存，無法修改復原",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, set it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: `<%=request.getContextPath()%>/spanMember`,
                    type: "POST",
                    data: {memberId: memberId},
                    success: function (response) {
                        swalWithBootstrapButtons.fire(
                            response,
                            'Your work has been saved.',
                            'success'
                        )
                        $(".swal2-confirm").click(function () {
                            window.location.reload();
                        })
                    },
                    error: function () {
                        alert("Error: " + error);
                    }
                });

            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    '此會員狀態未被改變',
                    'error'
                )
            }
        })
    });
</script>
<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script
        src="js/sidebars.js"></script>
<script>
    $(function () {
        $("#toMember").addClass("active");
        $("#toMember").attr("aria-selected", "true");
    })
</script>
</body>
</html>