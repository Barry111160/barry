<%@ page import="com.musclebeach.roomOrder.model.RoomOrderVO" %>
<%@ page import="com.musclebeach.roomOrder.model.RoomOrderService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="BIG5">
    <title>場地管理</title>
    <link href="css/index.css" rel="stylesheet"/>
    <link href="css/flaticon.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
    <style type="text/css">
        th, td {
            text-align: center;
        }

        .dataTables_wrapper .dataTables_paginate .paginate_button {
            color: #333 !important;
            border: 1px solid transparent;
            border-radius: 30px;
            background-color: #dee4ec;
            padding: 4px 13px;
            margin-left: 5px;
        }

        .dataTables_wrapper .dataTables_paginate .paginate_button.current,
        .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {
            color: white !important;
            background: #8b9bb1;
        }

        .dataTables_wrapper .dataTables_paginate {
            padding-top: 15px;
            padding-right: 15px;
        }

        .dataTables_wrapper .dataTables_info {
            padding-top: 20px;
            padding-left: 15px;
        }

        .dataTables_wrapper .dataTables_filter {
            padding-right: 15px;

        }
    </style>
</head>
<body>
<%@include file="../header_sidebar.jsp" %>
<%
    RoomOrderService roomOrderService = context.getBean(RoomOrderService.class);
    List<RoomOrderVO> list = roomOrderService.getPendingOrder();
    pageContext.setAttribute("list", list);
%>
<div class="tab-content" id="v-pills-tabContent" style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div class="tab-pane fade" id="v-pills-home" role="tabpanel"
         aria-labelledby="v-pills-home-tab" tabindex="0">
    </div>
    <!-- ========================================= 員工管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-employee" role="tabpanel"
         aria-labelledby="v-pills-employee-tab" tabindex="0"></div>
    <!-- ========================================= 會員管理 ========================================= -->
    <div class="tab-pane fade p-3 container" id="v-pills-member" role="tabpanel"
         aria-labelledby="v-pills-member-tab" tabindex="0"></div>
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
    <div class="tab-pane fade active show" id="v-pills-room"
         role="tabpanel" aria-labelledby="v-pills-room-tab" tabindex="0"
         style="width: 100%;">
        <ul class="nav nav-tabs">
            <li class="nav-item"><a class="nav-link active"
                                    aria-current="page"
                                    href="<%=request.getContextPath()%>/back-end/room/room_order_pending.jsp">待處理訂單</a>
            </li>
            <li class="nav-item"><a class="nav-link"
                                    href="<%=request.getContextPath()%>/back-end/room/room_order_listAll.jsp">所有訂單</a>
            </li>
            <li class="nav-item"><a class="nav-link"
                                    href="<%=request.getContextPath()%>/back-end/room/room_order_byEmployee.jsp">負責訂單</a>
            </li>
        </ul>
        <input type="hidden" id="employeeId" name="employeeId" value="${userID}">
        <div class="mt-4">
            <table id="table_id" class="display">
                <thead>
                <tr>
                    <th>訂單編號</th>
                    <th>會員編號</th>
                    <th>場地編號</th>
                    <th>租用日期</th>
                    <th>租用時段</th>
                    <th>訂單成立</th>
                    <th>訂單處理</th>
                    <th>負責人員</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="roomOrderVO" items="${list}" varStatus="i">
                    <tr>
                        <th scope="row">${roomOrderVO.orderID}</th>
                        <td>${roomOrderVO.memID}</td>
                        <td>${roomOrderVO.roomID}</td>
                        <td>${roomOrderVO.borrowDate}</td>

                        <c:if test="${roomOrderVO.borrowTime == 0}" var="true">
                            <td>上午</td>
                        </c:if>
                        <c:if test="${roomOrderVO.borrowTime == 1}" var="true">
                            <td>下午</td>
                        </c:if>
                        <c:if test="${roomOrderVO.borrowTime == 2}" var="true">
                            <td>晚上</td>
                        </c:if>

                        <td>${roomOrderVO.createTime}</td>

                        <c:if test="${roomOrderVO.orderStatus == 1}" var="true">
                            <td>
                                <button class="confirmBtn btn btn-primary btn-sm">訂單確認</button>
                            </td>
                            <input type="hidden" id="orderID" name="orderID" value="${roomOrderVO.orderID}">
                            <td></td>
                        </c:if>
                        <c:if test="${roomOrderVO.orderStatus == 2}" var="true">
                            <input type="hidden" id="borrowTime" name="borrowTime" value="${roomOrderVO.borrowTime}">
                            <td>
                                <button class="cancelBtn btn btn-danger btn-sm">取消受理</button>
                            </td>
                            <td>${roomOrderVO.empID}</td>
                        </c:if>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- ========================================= 論壇管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-article" role="tabpanel"
         aria-labelledby="v-pills-article-tab" tabindex="0"></div>
    <!-- ========================================= 客服管理 ========================================= -->
    <div class="tab-pane fade" id="v-pills-service" role="tabpanel"
         aria-labelledby="v-pills-service-tab" tabindex="0"></div>
</div>
</main>
<script>
    $(document).ready(function () {
        $("#table_id").DataTable({
            pageLength: 7,
            lengthChange: false,
            // lengthMenu: [5, 10],
            language: {
                processing: "處理中...",
                loadingRecords: "載入中...",
                lengthMenu: "顯示_MENU_項結果",
                zeroRecords: "沒有符合的結果",
                info: "本頁為第 _START_ 至第 _END_ 筆訂單，共 _TOTAL_ 筆待處理訂單",
                infoEmpty: "顯示第 0 至 0 項結果，共 0 項",
                infoFiltered: "(從_MAX_項結果中過濾)",
                infoPostFix: "",
                search: "搜尋 : ",
                paginate: {
                    first: "第一頁",
                    previous: "上一頁",
                    next: "下一頁",
                    last: "最後一頁",
                },
                aria: {
                    sortAscending: ": 升冪排列",
                    sortDescending: ": 降冪排列",
                },
            },
        });
    });
</script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    const employeeID = $("#employeeId").val();
    $(".confirmBtn").click(function () {
        var currentRow = $(this).closest("tr");
        const orderID = currentRow.find("th:eq(0)").text();

        $.ajax({
            url: `<%=request.getContextPath()%>/roomOrder.do`,
            type: "POST",
            data: {action: "confirm", orderID: orderID, employeeID: employeeID},
            success: function (response) {
                Swal.fire(
                    '訂單已確認',
                    '將發送確認信至會員信箱📬',
                    'success')
                $(".swal2-confirm").click(function () {
                    window.location.reload();
                })
            },
            error: function (error) {
                alert(error);
            }
        });
    });
    $(".cancelBtn").click(function () {
        var currentRow = $(this).closest("tr");
        const orderId = currentRow.find("th:eq(0)").text();
        const roomID = currentRow.find("td:eq(1)").text();
        const borrowDate = currentRow.find("td:eq(2)").text();
        const borrowTime = $("#borrowTime").val();

        $.ajax({
            url: "../../roomOrder.do",
            type: "POST",
            data: {
                action: "cancel",
                orderId: orderId,
                roomID: roomID,
                borrowDate: borrowDate,
                borrowTime: borrowTime,
                employeeID: employeeID
            },
            success: function (response) {
                Swal.fire(
                    '訂單已取消',
                    '將發送確認信至會員信箱📬',
                    'success')
                $(".swal2-confirm").click(function () {
                    window.location.reload();
                })
            },
            error: function (error) {
                alert(error);
            }
        });
    });
</script>
<script
        src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>
<script
        src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/sidebars.js"></script>
<script>
    $(function () {
        $("#toRoom").addClass("active");
        $("#toRoom").attr("aria-selected", "true");
    })
</script>
</body>
</html>