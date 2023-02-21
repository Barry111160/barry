<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.musclebeach.empAccess.model.EmpAccessVO" %>
<%@ page import="com.musclebeach.empAccess.model.EmpAccessService" %>
<%@ page import="com.musclebeach.emp.model.EmpVO" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.emp.model.EmpService" %>
<%@ page import="com.musclebeach.accessFunction.model.AccessFunctionService" %>

<%
    ApplicationContext context = ApplicationContextUtil.getContext();
    EmpAccessService empAccessSvc1 = context.getBean(EmpAccessService.class);
    pageContext.setAttribute("empAccessSvc1", empAccessSvc1);
    EmpVO username = (EmpVO) session.getAttribute("empVO");
    Integer userID = username.getEmpID();
    pageContext.setAttribute("userID", userID);

    EmpAccessService empAccessSvc = context.getBean(EmpAccessService.class);
    List<EmpAccessVO> listEmpAccess = empAccessSvc.getEmp(userID);
    pageContext.setAttribute("listEmpAccess", listEmpAccess);
    EmpService empSvc = context.getBean(EmpService.class);
    pageContext.setAttribute("empSvc", empSvc);
    AccessFunctionService accessFunctionSvc = context.getBean(AccessFunctionService.class);
    pageContext.setAttribute("accessFunctionSvc", accessFunctionSvc);
    EmpAccessService empAccessService = context.getBean(EmpAccessService.class);
    pageContext.setAttribute("empAccessService", empAccessService);
%>
<!-- ======================================== header 開始 ======================================== -->
<header class="p-3 text-bg-dark" style="height: 69.33px">
    <div class="container">
        <div
                class="d-flex flex-wrap align-items-center justify-content-between">
            <div class="header_1" style="font-size: 1.2rem">
                <i class="flaticon-barbell"></i> <span>Muscle Beach</span>
            </div>
            <div class="header_2" style="width: 360px; align-items: center; height: 37.33px;">
                <form method="post" action="<%=request.getContextPath()%>/back-end/emp/emp.do">
                    <i class="bi bi-person-fill" style="color: white"></i>
                    <span style="margin-right: 30px">Welcome, <%=username.getEmpName()%> !</span>
                    <a class="btn btn-outline-light" style="margin-right: 5px"
                       href="<%=request.getContextPath()%>/back-end/emp/update_password.jsp?empID=<%=username.getEmpID()%>"
                       role="button">修改密碼</a> <input type="hidden" name="action"
                                                         value="logout"/> <input type="submit" value="登出"
                                                                                 class="btn btn-outline-light"/>
                </form>
            </div>
        </div>
    </div>
</header>
<!-- ======================================== sidebar 開始 ======================================== -->
<main class="d-flex flex-nowrap" style="height: calc(100vh - 70px);">
    <div class="d-flex flex-column flex-shrink-0 p-3"
         style="width: 220px; background-color: rgb(110, 109, 109)">
        <ul class="nav nav-pills flex-column mb-auto" id="v-pills-tab"
            role="tablist" aria-orientation="vertical"
            style="text-align: center">
            <li class="nav-item">
                <a
                        href="<%=request.getContextPath()%>/back-end/emp/back_index.jsp"
                        class="nav-link text-white"
                        type="button"
                        role="tab"
                        aria-selected="false"
                        style="font-size: 1.5rem"
                        id="toHome">
                    <i class="bi bi-house-door" style="margin-right: 8px"></i>
                    首頁
                </a>
            </li>
            <hr/>

            <c:forEach var="empAccessVO" items="${empAccessSvc1.getEmp(userID)}">
                <c:if test="${empAccessVO.fID ==1}">
                    <li class="nav-item">
                        <a
                                href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp"
                                class="nav-link text-white"
                                type="button"
                                role="tab"
                                aria-selected="false"
                                id="toEmployee">
                            <i class="bi bi-person" style="color: white; margin: 5px"></i>
                            員工管理
                        </a>
                    </li>
                </c:if>
                <c:if test="${empAccessVO.fID ==2}">
                    <li>
                        <a
                                href="<%=request.getContextPath()%>/back-end/member/memPage.jsp"
                                class="nav-link text-white"
                                type="button"
                                role="tab"
                                aria-selected="false"
                                id="toMember">
                            <i class="bi bi-person-circle" style="color: white; margin: 5px"></i>
                            會員管理
                        </a>
                    </li>
                </c:if>
                <c:if test="${empAccessVO.fID ==3}">
                    <li>
                        <a
                                href="<%=request.getContextPath()%>/back-end/product/listAllProd.jsp"
                                class="nav-link text-white"
                                type="button"
                                role="tab"
                                aria-selected="false"
                                id="toShop">
                            <i class="bi bi-shop" style="color: white; margin: 5px"></i>
                            商城管理
                        </a>
                    </li>
                </c:if>
                <c:if test="${empAccessVO.fID ==4}">
                    <li>
                        <a
                                href="<%=request.getContextPath()%>/back-end/coachclassorder/selectCoachClassOrderPage.jsp"
                                class="nav-link text-white"
                                type="button"
                                role="tab"
                                aria-selected="false"
                                id="toCoach">
                            <i class="bi bi-universal-access" style="color: white; margin: 5px"></i>
                            教練管理
                        </a>
                    </li>
                </c:if>
                <c:if test="${empAccessVO.fID ==5}">
                    <li>
                        <a
                                href="<%=request.getContextPath()%>/back-end/course/select_page.jsp"
                                class="nav-link text-white"
                                type="button"
                                role="tab"
                                aria-selected="false"
                                id="toClass">
                            <i class="bi bi-calendar2-week" style="color: white; margin: 5px"></i>
                            課程管理
                        </a>
                    </li>
                </c:if>
                <c:if test="${empAccessVO.fID ==6}">
                    <li>
                        <a
                                href="<%=request.getContextPath()%>/back-end/room/room_page.jsp"
                                class="nav-link text-white"
                                type="button"
                                role="tab"
                                aria-selected="false"
                                id="toRoom">
                            <i class="bi bi-building" style="color: white; margin: 5px"></i>
                            場地管理
                        </a>
                    </li>
                </c:if>
                <c:if test="${empAccessVO.fID ==7}">
                    <li>
                        <a
                                href="<%=request.getContextPath()%>/back-end/article/articleReport/listAllArticleReport.jsp"
                                class="nav-link text-white"
                                type="button"
                                role="tab"
                                aria-selected="false"
                                id="toArticle">
                            <i class="bi bi-chat-right-text" style="color: white; margin: 5px"></i>
                            論壇管理
                        </a>
                    </li>
                </c:if>
                <c:if test="${empAccessVO.fID ==8}">
                    <li>
                        <a
                                href="<%=request.getContextPath()%>/back-end/news/index_ver2.jsp"
                                class="nav-link text-white"
                                type="button"
                                role="tab"
                                aria-selected="false"
                                id="toService">
                            <i class="bi bi-envelope" style="color: white; margin: 5px"></i>
                            客服管理
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
        <hr/>
        <div class="mx-auto d-flex mt-3 mb-3 text-muted">&copy; 2022</div>
    </div>