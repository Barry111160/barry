<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Muscle Beach: 後台登入</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="css/login.css" rel="stylesheet" />

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, 0.1);
	border: solid rgba(0, 0, 0, 0.15);
	border-width: 1px 0;
	box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1), inset 0 0.125em
		0.5em rgba(0, 0, 0, 0.15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -0.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}
</style>


</head>
<body class="text-center">

	<main class="form-signin w-100 m-auto">
		<form METHOD="post"	ACTION="emp.do">
			<img class="mb-4" src="images/logojpg.jpg" alt="" width="100%"
				height="100%" />
			<h1 class="h3 mb-3 fw-normal">後台管理系統</h1>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInput"
					name="empID" /> <label for="floatingInput">員工編號</label>
			</div>
			<div class="form-floating mb-4">
				<input type="password" class="form-control" id="floatingPassword"
					name="password" /> <label
					for="floatingPassword">您的密碼</label>
			</div>
			<input type="hidden" name="action" value="login">
<!-- 			<button class="w-100 btn btn-lg btn-primary" type="submit"> -->
<!-- 				登 入</button> -->
			<input type="submit" value="登入" class="w-100 btn btn-lg btn-primary">
			<p class="mt-5 mb-3 text-muted">&copy; 2022</p>
		</form>
	</main>

</body>
</html>