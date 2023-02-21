<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.order.model.entity.OrderDetail" %>

<%
    List<OrderDetail> detailList = (List<OrderDetail>) request.getAttribute("detailVO");
    pageContext.setAttribute("detailList", detailList);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>商城管理</title>
    <link
            rel="canonical"
            href="https://getbootstrap.com/docs/5.3/examples/headers/"
    />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js"></script>

    <link
            rel="canonical"
            href="https://getbootstrap.com/docs/5.3/examples/sidebars/"
    />
    <link href="<%=request.getContextPath()%>/back-end/resources/assets/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style type="text/css"></style>
    <link href="<%=request.getContextPath()%>/back-end/resources/index/index.css" rel="stylesheet"/>
    <!-- Flaticon Font -->
    <link href="<%=request.getContextPath()%>/back-end/resources/lib/flaticon/font/flaticon.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/back-end/resources/css/listAll_dataTable.css" rel="stylesheet">
    <link href="https://kit.fontawesome.com/db0445c7fa.css" rel="stylesheet" crossorigin="anonymous">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
            rel="stylesheet"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"/>

</head>
<body>
<%@ include file="../header_sidebar.jsp" %>
<div class="tab-content" id="v-pills-tabContent"
     style="width: 100%; height: calc(100vh - 70px);">
    <!-- ============================================ 首頁 ============================================ -->
    <div
            class="tab-pane fade"
            id="v-pills-home"
            role="tabpanel"
            aria-labelledby="v-pills-home-tab"
            tabindex="0"
    >
        <img src="/image/welcome.gif" style="width: 96%" alt=""/>
    </div>
    <!-- ========================================= 商城管理 ========================================= -->
    <div
            class="tab-pane fade show active"
            id="v-pills-shop"
            role="tabpanel"
            aria-labelledby="v-pills-shop-tab"
            tabindex="0"
            style="border: 2px solid white"
    >

        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <div class="col-sm-6">
                        <a href="<%=request.getContextPath()%>/back-end/order/listAllOrder.jsp"
                           class="nav-link">
                            <p><i class="fa-solid fa-truck"></i>訂單管理</p>
                        </a>
                    </div>
                </div>
                <div class="row  mt-3">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">所有訂單明細</h4>
                                <div class="table-responsive">
                                    <table id="dataTables"
                                           class="display compact hover cell-border stripe table-hover"
                                           style="width: 100%; font-size: 12px">
                                        <thead>
                                        <tr>
                                            <th>訂單編號</th>
                                            <th>商品名稱</th>
                                            <th>數量</th>
                                            <th>金額</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="detailVO" items="${detailList}">
                                            <tr>
                                                <td>${detailVO.orderID}</td>
                                                <td>${detailVO.product.proName}</td>
                                                <td>${detailVO.detQty}</td>
                                                <td>${detailVO.detPrice}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <script>
                                        $(document).ready(function () {
                                            $('#dataTables').DataTable({
                                                "order": [[6, 'desc']],
                                                "language": {
                                                    "processing": "處理中...",
                                                    "loadingRecords": "載入中...",
                                                    "paginate": {
                                                        "first": "第一頁",
                                                        "previous": "上一頁",
                                                        "next": "下一頁",
                                                        "last": "最後一頁"
                                                    },
                                                    "emptyTable": "目前沒有資料",
                                                    "datetime": {
                                                        "previous": "上一頁",
                                                        "next": "下一頁",
                                                        "hours": "時",
                                                        "minutes": "分",
                                                        "seconds": "秒",
                                                        "amPm": [
                                                            "上午",
                                                            "下午"
                                                        ],
                                                        "unknown": "未知",
                                                        "weekdays": [
                                                            "週日",
                                                            "週一",
                                                            "週二",
                                                            "週三",
                                                            "週四",
                                                            "週五",
                                                            "週六"
                                                        ],
                                                        "months": [
                                                            "一月",
                                                            "二月",
                                                            "三月",
                                                            "四月",
                                                            "五月",
                                                            "六月",
                                                            "七月",
                                                            "八月",
                                                            "九月",
                                                            "十月",
                                                            "十一月",
                                                            "十二月"
                                                        ]
                                                    },
                                                    "searchBuilder": {
                                                        "add": "新增條件",
                                                        "condition": "條件",
                                                        "deleteTitle": "刪除過濾條件",
                                                        "button": {
                                                            "_": "複合查詢 (%d)",
                                                            "0": "複合查詢"
                                                        },
                                                        "clearAll": "清空",
                                                        "conditions": {
                                                            "array": {
                                                                "contains": "含有",
                                                                "equals": "等於",
                                                                "empty": "空值",
                                                                "not": "不等於",
                                                                "notEmpty": "非空值",
                                                                "without": "不含"
                                                            },
                                                            "date": {
                                                                "after": "大於",
                                                                "before": "小於",
                                                                "between": "在其中",
                                                                "empty": "為空",
                                                                "equals": "等於",
                                                                "not": "不為",
                                                                "notBetween": "不在其中",
                                                                "notEmpty": "不為空"
                                                            },
                                                            "number": {
                                                                "between": "在其中",
                                                                "empty": "為空",
                                                                "equals": "等於",
                                                                "gt": "大於",
                                                                "gte": "大於等於",
                                                                "lt": "小於",
                                                                "lte": "小於等於",
                                                                "not": "不為",
                                                                "notBetween": "不在其中",
                                                                "notEmpty": "不為空"
                                                            },
                                                            "string": {
                                                                "contains": "含有",
                                                                "empty": "為空",
                                                                "endsWith": "字尾為",
                                                                "equals": "等於",
                                                                "not": "不為",
                                                                "notEmpty": "不為空",
                                                                "startsWith": "字首為",
                                                                "notContains": "不含",
                                                                "notStartsWith": "開頭不是",
                                                                "notEndsWith": "結尾不是"
                                                            }
                                                        },
                                                        "data": "欄位",
                                                        "leftTitle": "群組條件",
                                                        "logicAnd": "且",
                                                        "logicOr": "或",
                                                        "rightTitle": "取消群組",
                                                        "title": {
                                                            "_": "複合查詢 (%d)",
                                                            "0": "複合查詢"
                                                        },
                                                        "value": "內容"
                                                    },
                                                    "editor": {
                                                        "close": "關閉",
                                                        "create": {
                                                            "button": "新增",
                                                            "title": "新增資料",
                                                            "submit": "送出新增"
                                                        },
                                                        "remove": {
                                                            "button": "刪除",
                                                            "title": "刪除資料",
                                                            "submit": "送出刪除",
                                                            "confirm": {
                                                                "_": "您確定要刪除您所選取的 %d 筆資料嗎？",
                                                                "1": "您確定要刪除您所選取的 1 筆資料嗎？"
                                                            }
                                                        },
                                                        "error": {
                                                            "system": "系統發生錯誤(更多資訊)"
                                                        },
                                                        "edit": {
                                                            "button": "修改",
                                                            "title": "修改資料",
                                                            "submit": "送出修改"
                                                        },
                                                        "multi": {
                                                            "title": "多重值",
                                                            "info": "您所選擇的多筆資料中，此欄位包含了不同的值。若您想要將它們都改為同一個值，可以在此輸入，要不然它們會保留各自原本的值。",
                                                            "restore": "復原",
                                                            "noMulti": "此輸入欄需單獨輸入，不容許多筆資料一起修改"
                                                        }
                                                    },
                                                    "autoFill": {
                                                        "cancel": "取消"
                                                    },
                                                    "buttons": {
                                                        "copySuccess": {
                                                            "_": "複製了 %d 筆資料",
                                                            "1": "複製了 1 筆資料"
                                                        },
                                                        "copyTitle": "已經複製到剪貼簿",
                                                        "excel": "Excel",
                                                        "pdf": "PDF",
                                                        "print": "列印",
                                                        "copy": "複製",
                                                        "colvis": "欄位顯示",
                                                        "colvisRestore": "重置欄位顯示",
                                                        "csv": "CSV",
                                                        "pageLength": {
                                                            "-1": "顯示全部",
                                                            "_": "顯示 %d 筆"
                                                        },
                                                        "createState": "建立狀態",
                                                        "removeAllStates": "移除所有狀態",
                                                        "removeState": "移除",
                                                        "renameState": "重新命名",
                                                        "savedStates": "儲存狀態",
                                                        "stateRestore": "狀態 %d",
                                                        "updateState": "更新"
                                                    },
                                                    "searchPanes": {
                                                        "collapse": {
                                                            "_": "搜尋面版 (%d)",
                                                            "0": "搜尋面版"
                                                        },
                                                        "emptyPanes": "沒搜尋面版",
                                                        "loadMessage": "載入搜尋面版中...",
                                                        "clearMessage": "清空",
                                                        "count": "{total}",
                                                        "countFiltered": "{shown} ({total})",
                                                        "title": "過濾條件 - %d",
                                                        "showMessage": "顯示全部",
                                                        "collapseMessage": "摺疊全部"
                                                    },
                                                    "stateRestore": {
                                                        "emptyError": "名稱不能空白。",
                                                        "creationModal": {
                                                            "button": "建立",
                                                            "columns": {
                                                                "search": "欄位搜尋",
                                                                "visible": "欄位顯示"
                                                            },
                                                            "name": "名稱：",
                                                            "order": "排序",
                                                            "paging": "分頁",
                                                            "scroller": "卷軸位置",
                                                            "search": "搜尋",
                                                            "searchBuilder": "複合查詢",
                                                            "select": "選擇",
                                                            "title": "建立新狀態",
                                                            "toggleLabel": "包含："
                                                        },
                                                        "duplicateError": "此狀態名稱已經存在。",
                                                        "emptyStates": "名稱不可空白。",
                                                        "removeConfirm": "確定要移除 %s 嗎？",
                                                        "removeError": "移除狀態失敗。",
                                                        "removeJoiner": "和",
                                                        "removeSubmit": "移除",
                                                        "removeTitle": "移除狀態",
                                                        "renameButton": "重新命名",
                                                        "renameLabel": "%s 的新名稱：",
                                                        "renameTitle": "重新命名狀態"
                                                    },
                                                    "select": {
                                                        "columns": {
                                                            "_": "選擇了 %d 欄資料",
                                                            "1": "選擇了 1 欄資料"
                                                        },
                                                        "rows": {
                                                            "1": "選擇了 1 筆資料",
                                                            "_": "選擇了 %d 筆資料"
                                                        },
                                                        "cells": {
                                                            "1": "選擇了 1 格資料",
                                                            "_": "選擇了 %d 格資料"
                                                        }
                                                    },
                                                    "zeroRecords": "沒有符合的資料",
                                                    "aria": {
                                                        "sortAscending": "：升冪排列",
                                                        "sortDescending": "：降冪排列"
                                                    },
                                                    "info": "顯示第 _START_ 至 _END_ 筆結果，共 _TOTAL_ 筆",
                                                    "infoEmpty": "顯示第 0 至 0 筆結果，共 0 筆",
                                                    "infoFiltered": "(從 _MAX_ 筆結果中過濾)",
                                                    "infoThousands": ",",
                                                    "lengthMenu": "顯示 _MENU_ 筆結果",
                                                    "search": "搜尋：",
                                                    "searchPlaceholder": "請輸入關鍵字",
                                                    "thousands": ","
                                                }
                                            });
                                        });
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</main>
<script src="<%=request.getContextPath()%>/back-end/resources/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/resources/index/sidebars.js"></script>
<script src="https://kit.fontawesome.com/db0445c7fa.js" crossorigin="anonymous"></script>
<script>
    $(function () {
        $("#toShop").addClass("active");
        $("#toShop").attr("aria-selected", "true");
    })
</script>
</body>
</html>
