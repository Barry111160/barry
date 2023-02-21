<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%@ page import="com.musclebeach.room.model.RoomVO" %>
<%@ page import="com.musclebeach.roomTime.model.RoomTimeVO" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.roomTime.model.RoomTimeService" %>

<%
    ApplicationContext context = ApplicationContextUtil.getContext();
    RoomTimeService roomTimeService = context.getBean(RoomTimeService.class);
    MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
    RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
    List<RoomTimeVO> list = roomTimeService.getAll();
    pageContext.setAttribute("list", list);
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
    <!-- datetimepicker -->
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet"/>
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
                    <div class="form-group w-25">
                        <label> 租用場地 </label>
                        <input class="form-control" value="${roomVO.roomName}" readonly/>
                    </div>
                    <div class="form-group w-25">
                        <label> 租用日期 </label>
                        <input
                                type="text"
                                class="form-control"
                                id="inputBorrowDate"
                                name="borrowDate"
                        />
                    </div>
                    <div class="form-group w-25">
                        <label> 租用時段 </label>
                        <select
                                class="form-control"
                                id="selectBorrowTime"
                                name="borrowTime"
                        >
                            <option value="" selected disabled hidden>請選擇時段</option>
                            <option value="0" id="morning" disabled>上午 （10:00 ~ 11:30）</option>
                            <option value="1" id="noon" disabled>下午 （14:00 ~ 15:30）</option>
                            <option value="2" id="night" disabled>晚上 （18:00 ~ 19:30）</option>
                        </select>
                    </div>
                    <div class="form-group w-25">
                        <label> 租用金額 </label>
                        <input class="form-control" value="${roomVO.roomPrice}" readonly/>
                    </div>
                    <div
                            class="form-group border border-5 border-warning"
                            style="
                  padding: 10px 10px 0 70px;
                  width: 680px;
                  margin-left: 350px;
                  position: absolute;
                  bottom: 200px;
                "
                    >
                        <p>■ 租借說明</p>
                        <p>1. 最晚請於欲租日期三⽇前於本官網線上預約。</p>
                        <p>
                            2.
                            一次僅接受一個時段預約，欲預約多個時段請於預約成功後再進行操作。
                        </p>
                        <p>3. 僅開放30日內之預約（如：1/1開放1/5 ~ 2/6之預約）。</p>
                        <p>4. 開放時間外及三⽇內之租⽤請洽客服。</p>
                    </div>
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
                    <div class="row">
                        <div class="col-2 form-group">
                            <label>會員編號</label>
                            <input class="form-control" value="<%=memVO.getMemID()%>" readonly/>
                        </div>
                        <div class="col-3 form-group">
                            <label>姓名</label>
                            <input class="form-control" value="<%=memVO.getMemName()%>" readonly/>
                        </div>
                        <div class="col-3 form-group">
                            <label>聯絡電話</label>
                            <input
                                    class="form-control"
                                    id="inputTel"
                                    name="memberTel"
                                    value="<%=memVO.getMemPhone()%>"
                                    pattern="09\d{8}"
                                    oninput="setCustomValidity('');"
                                    oninvalid="setCustomValidity('請輸入正確的手機號碼格式:09xxxxxxxx');"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label>服務條款</label>
                        <div
                                class="border border-danger p-4"
                                style="height: 550px; overflow-y: auto"
                        >
                            《租借規範》 <br/>
                            <p>
                                01.
                                場地租金將依照本公司官網「找場地之租借價格」計費。租金內含空調、一般照明、投影設備。
                            </p>
                            <p>
                                02. 如租借(人)單位擬取消場地，需依下列規定辦理：<br/>
                                A. 取消通知於租賃日 3日前送達(告知)者，已付金額全額退還。 B.
                                取消通知於租賃日 3日內送達(告知)者，已付金額不予退還。
                                <br/>
                                如租借(人)單位擬異動租借時段，煩請取消原訂單再重新下訂。
                            </p>
                            <p>
                                03.提前或超時使用需另付費。<br/>
                                加時費計價方式：依照場地租借金額除以二為每半小時加時費，不足半小時以半小時計。
                            </p>
                            <p>
                                04.
                                活動結束後，請於撤場時間內將所有相關物品撤離，逾時使用以超時計價。
                            </p>
                            <p>
                                05.
                                如遇不可歸責於雙方當事人之事由，或不可抗力原因，例如：天災、事變、戰爭、嚴重傳染疾病、法令禁止或限制…
                                等原因，租借(人)單位得與場地方協調訂金退款或變更活動時間。
                                *場租折扣除外。
                            </p>
                            <p>06.本公司保有接受訂單與否之權利。</p>
                            <p>
                                07. 自行攜帶之電力設備，單品項用電功率超過500W 或
                                總使用電力超過600W，需付費向櫃台申請租用專用迴路。<br/>
                                如自行使用現場插座造成跳電或其他設備毀損等情節，租借方須負責賠償事宜。
                            </p>
                            <p>
                                08.
                                租借(人)單位使用本公司會場，如造成設備、裝潢、器材、公共設施損壞，將依法由法務追償賠償費用。
                            </p>
                            <p>
                                09.本會場之燈光音響設備，不提供 租借(人)單位
                                自行操作，如擅自使用造成損壞，將依法由法務追償賠償費用。
                            </p>
                            <p>10. 本規則如有未盡事宜，本公司保有隨時修改之權利。</p>
                            《場地規範》
                            <p>
                                01.
                                租借(人)單位租用場地需負保管及維護之責任，如污染或毀損場地、硬體器材，將依物品價格、毀損程度與污漬情況追償賠償費用。
                            </p>
                            <p>
                                02.
                                本會場嚴禁進行任何黏、貼、釘、掛等動作，若有任何毀損，租借(人)單位應負責恢復原狀或照價賠償。
                            </p>
                            <p>
                                03.
                                租借(人)單位使用場地，如有下列情形之一，本公司得隨時終止活動，並請求損害賠償：
                                <br/>
                                A. 違背政府法令者。 B. 違反公共秩序或善良風俗者。 C.
                                活動項目與申請登記性質不符或將場地轉讓他人使用者。
                                <br/>D. 有損建築或設備，本公司勘驗不宜繼續使用者。 E.
                                其他違反本公司場地管理規則，經本公司認定不宜使用者。<br/>
                                前項申請使用場地經撤銷其准許者，其已繳之各項費用，全數不予退還，作為懲罰性違約金，其餘一切損失均由租借方賠償，不得異議。
                            </p>
                            <p>
                                04.
                                本場地禁止從事下列活動，如：明火使用、濺水拍攝、噴漆、燒煙、高瓦數電器用品、寵物活動...等。
                            </p>
                            <p>
                                05.
                                本會場禁止攜帶違禁品、易燃物、易爆物進入各場地，特效方面禁用爆竹、爆裂物、煙火、金粉(粉塵)等危險特效，其餘各式活動特效使用時，應嚴加管制並注意安全，必要時本公司得禁止相關特效，如因此造成第三人生命、身體及財物或環境設施損害時，租借（人）單位需負擔相關法律及賠償責任。
                            </p>
                            <p>06. 基於本會場空間使用管理限制，不開放攜帶寵物入內。</p>
                            <p>
                                07.
                                本會場及全棟大樓範圍內，全面禁煙、嚼食檳榔、口香糖及舉行違反善良風俗之活動；租借(人)單位在活動進行中應負責維持會場秩序及保障與會人員安全。<br/>
                                租用期間僅可使用租借範圍內開放場域，其他樓層為大樓商辦空間，嚴禁任意進入及使用。
                            </p>
                            <p>
                                08.
                                各場地活動進行之音量不得妨礙其他場地之進行，如違反規定不聽勸阻，本公司有權切斷場地之電力、燈光、空調及視聽系統，立即終止活動之進行，所繳費用概不退還。
                            </p>
                            <p>
                                09.
                                本公司有權於租借期間使用監視設備或主動派員至現場勘查。本會場監控設備嚴禁觸碰、屏蔽，監控設備僅供保全維安使用，目的為保護本公司與申請單位彼此雙方之權益，維護場館環境安全。如有擅自觸碰監控設備之情事，所造成之後續相關責任歸屬概由租借(人)單位負責賠償。
                                如違反規定不聽勸阻，本公司有權立即終止活動之進行，所繳費用概不退還。
                            </p>
                            <p>
                                10.
                                使用期間如有孳生事端、訟爭，租借(人)單位應維護本會場免受引起的一切對抗行為、控告、索賠，否則概由租借(人)單位賠償本公司所發生之一切損失，包括但不限於支出之賠償、訴訟或律師費用等。
                            </p>
                            <p>
                                11.
                                租借(人)單位所為課程之內容，不得有詐欺、違反公平交易法或其他相關法規之情事，否則租借(人)單位應自負其法律責任，與本公司無涉。
                            </p>
                            <p>
                                12.
                                租借(人)單位所聘請之國內外講師，請自行負責其具有合法的工作證，且保證舉辦活動之合法性，如有違反相關規定，與本公司無涉。
                            </p>
                            <p>
                                13.
                                租借(人)單位需自行保管攜入之物品，本會場恕不負責任何保管及賠償責任。
                            </p>

                            ⟪ 設備規範 ⟫
                            <p>
                                01.
                                嚴禁擅自搬離本會場內任何物品及器材設備，如有違規立即報警處理，租借(人)單位需自行承擔一切法律責任。
                            </p>
                            <p>
                                02.
                                臨時借用物品、器材應事先徵得本公司同意並完成租借流程，使用完畢應負責原狀或返還。
                            </p>
                            <p>
                                03.
                                因租借(人)單位(包含使用人及承包商)或與會人員故意或過失，而造成租賃標的之損壞，租借(人)單位及行為人應負連帶責任。
                            </p>
                            <p>
                                04.
                                活動期間所需之視聽設備器材限由本會場提供，如因會議性質致需使用特殊會議設備，租借(人)單位得自行攜入，但應於活動前告知。
                            </p>
                            《隱私權聲明》
                            <p>
                                ＊請詳細閱讀本隱私權聲明。一旦透過您在本網站提供資訊，即表示您接受本「隱私權聲明」中所說明的規則。如您不同意本隱私權聲明者，請立即停止使用本網站服務，並請立即刪除您的資料，感謝您的配合。
                            </p>
                            <p>
                                ＊我們需要或要求的資訊至少包括您的姓名、行動電話號碼。透過報名表單收集這些資訊的原因包括：
                                (1) 進行個人身份識別以及線上交易服務 。 (2)
                                讓我們在必要時能聯絡您以便提供客戶服務。 (3)
                                於場地租借時確認您的身份。
                            </p>
                            《免責聲明》
                            <p>
                                ＊雖然我們盡力保護使用者隱私權，我們仍有權依據我方營運流程、交易安全、商業政策、網站維護更新等需求，或依據相關法令負有法定義務或考量公共利益之情形，而利用或公開您所提供的資訊。但不限於針對威脅我們利益
                                (如客戶詐欺) 的人士，或其活動可能損害或傷害其他人的人士，
                                此外我們可能必須向第三方提供客戶資訊，例如信用卡公司，以解決一般業務中所發生的糾紛。
                            </p>
                            <p>
                                ＊請注意：資訊中包含通往其他網站的連結。這些網站可能會收集您的個人識別資訊。本「隱私權聲明」不涵蓋這些網站的資訊收集規則或規定。
                            </p>
                            <p>
                                ＊我們採取某些步驟以協助保護您提供給我們的個人識別資訊。例如：我們使用SSL加密技術來保障信用卡使用安全。如果您對資訊安全有任何疑問，請來信詢問。
                            </p>
                            <p>
                                ＊我們有權隨時變更隱私權規則，如有變更者，我們將一併修改本「隱私權聲明」及其生效日期，以確保您知道我們收集哪些資訊、我們可能使用資訊的方式，以及我們可能向誰公開資訊。請務必定期查看本聲明以瞭解我們如何保護您的資訊。如您不同意變更者，請立即停止使用本網站之服務，感謝您的配合。
                            </p>
                            <p>＊如果您對本《隱私權聲明》有任何疑問，請洽詢客服信箱。</p>
                        </div>
                        <div class="position-relative" style="left: 20px">
                            <input
                                    class="form-check-input"
                                    type="checkbox"
                                    value=""
                                    id="flexCheckDefault"
                            />
                            <label class="form-check-label" for="flexCheckDefault">
                                我已閱讀及同意 Muscle Beach 服務條款
                            </label>
                        </div>
                    </div>
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
                    <h3 class="text-danger">請確認您的預約資訊</h3>
                    <div class="row">
                        <div class="col-2 form-group">
                            <label> 租用場地 </label>
                            <input class="form-control" value="${roomVO.roomName}" disabled/>
                        </div>
                        <div class="col-3 form-group">
                            <label> 租用日期 </label>
                            <input class="form-control" id="ConfirmDate"/>
                        </div>
                        <div class="col-3 form-group">
                            <label> 租用時段 </label>
                            <span class="form-control" id="ConfirmTime"></span>
                        </div>
                        <div class="col-3 form-group">
                            <label> 租用金額 </label>
                            <input class="form-control" value="${roomVO.roomPrice}" disabled/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-2 form-group">
                            <label>會員編號</label>
                            <input class="form-control" value="<%=memVO.getMemID()%>" disabled/>
                        </div>
                        <div class="col-3 form-group">
                            <label>姓名</label>
                            <input class="form-control" value="<%=memVO.getMemName()%>" disabled/>
                        </div>
                        <div class="col-3 form-group">
                            <label>聯絡電話</label>
                            <span class="form-control" id="ConfirmTel"
                                  style="background-color: #e9ecef"><%=memVO.getMemPhone()%></span>
                        </div>
                    </div>
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
                        <input type="hidden" id="roomID" value="<%=roomVO.getRoomID()%>" name="roomID"/>

                        <input type="hidden" name="action" value="pay_room_order"/>
                    </div>
                </div>
                <div id="step4" class="content" role="tabpanel">
                    <div class="alert alert-success">感謝您的預約。</div>
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
        console.log(borrowDate);
        $.ajax({
            url: `<%=request.getContextPath()%>/getRoomTime`,
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
                    title: '無可預約時間，系統忙碌中\n⛔取得可預約時段失敗',
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
<script>
    $(function () {
        $("#toRoom").addClass("active");
        $("#toRoom").attr("aria-selected", "true");
    })
</script>
</body>
</html>