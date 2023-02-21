<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



    <div class="container-fluid p-0 nav-bar">
		<nav
				class="navbar navbar-expand-lg bg-none navbar-dark p-0 bg-secondary position-fixed justify-content-between"
				style="width: 100vw">
			<a href="<%=request.getContextPath()%>/front-end/indexlogin.jsp" class="navbar-brand">
				<h1 class="m-0 display-4 font-weight-bold text-uppercase text-white"
					style="padding-left: 15px">Muscle beach</h1>
			</a>
			<button type="button" class="navbar-toggler" data-toggle="collapse"
					data-target="#navbarCollapse">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-between"
				 id="navbarCollapse">
				<div class="navbar-nav ml-auto p-4 bg-secondary">
					<a href="<%=request.getContextPath()%>/front-end/product/shop.jsp" class="nav-item nav-link" id="toShop">線上商城</a>
					<a href="<%=request.getContextPath()%>/front-end/room/listAllRoom.jsp" class="nav-item nav-link" id="toRoom">找場地</a>
					<a href="<%=request.getContextPath()%>/front-end/coach/coachList.jsp" class="nav-item nav-link" id="toCoach">找教練</a>
					<a href="<%=request.getContextPath()%>/front-end/course/class.html" class="nav-item nav-link" id="toCourse">找課程</a>
					<div class="nav-item dropdown">
						<a href="#" class="nav-item nav-link dropdown-toggle"
						   data-toggle="dropdown" id="toMem">會員專區</a>
						<form method="post" action="<%=request.getContextPath()%>/back-end/member/mem.do">
							<div class="dropdown-menu text-capitalize">
								<a href="<%=request.getContextPath()%>/front-end/member/mem_info.jsp" class="dropdown-item" id="toMeminfo">會員資料</a>
								<a href="<%=request.getContextPath()%>/front-end/coach/memcoachclasslist.jsp" class="dropdown-item" id="toCoachMa">教練課管理</a>
								<a href="<%=request.getContextPath()%>/front-end/course/myClassOrders.html" class="dropdown-item" id="toCourseMa">團課管理</a>
								<a href="<%=request.getContextPath()%>/front-end/course/myAbsence.html" class="dropdown-item" id="toCourseAs">查看團課缺席</a>
								<a href="<%=request.getContextPath()%>/front-end/room/room_order_info.jsp"
								   class="dropdown-item">場地管理</a>
								<input class="btn text-danger" style="margin-left:10px;" type="submit" value="登出">
								<input type="hidden" name="action" value="logout"/>
							</div>
						</form>
					</div>
					<a href="<%=request.getContextPath()%>/front-end/article/listAllArticle.jsp" class="nav-link">論壇</a>
					<div class="nav-item dropdown">
						<a href="#" class="nav-item nav-link dropdown-toggle"
						   data-toggle="dropdown">聯絡我們</a>
						<div class="dropdown-menu text-capitalize">
							<a href="<%=request.getContextPath()%>/front-end/question/listAllQuestion.jsp" class="dropdown-item" id="toQA">常見問題</a>
							<a href="<%=request.getContextPath()%>/front-end/news/listAllNews.jsp" class="dropdown-item" id="toNews">最新資訊</a>
						</div>
					</div>
				</div>
			</div>
		</nav>
	</div>
