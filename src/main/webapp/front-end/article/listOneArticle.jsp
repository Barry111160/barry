<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.musclebeach.articleMessage.model.*"%>
<%@ page import="com.musclebeach.article.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeService" %>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeVO" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.mem.model.MemVO" %>
<%@ page import="com.musclebeach.articleLike.model.ArticleLikeVO" %>
<%@ page import="com.musclebeach.articleFavorite.model.ArticleFavoriteVO" %>
<%@ page import="com.musclebeach.articleFavorite.model.ArticleFavoriteService" %>
<%@ page import="com.musclebeach.articleLike.model.ArticleLikeService" %>

<%

	ApplicationContext ctx = ApplicationContextUtil.getContext();
	assert ctx != null;
	ArticleTypeService articleTypeService = ctx.getBean(ArticleTypeService.class);
	ArticleLikeService artLikeService = ctx.getBean(ArticleLikeService.class);
	ArticleFavoriteService articleFavoriteService = ctx.getBean(ArticleFavoriteService.class);
	List<ArticleTypeVO> typeList = articleTypeService.getAll();
	pageContext.setAttribute("typeList", typeList);
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
	List<ArticleMessageVO> articleMessageVO = (List<ArticleMessageVO>) request.getAttribute("articleMessageVO");
	pageContext.setAttribute("articleMessageVO",articleMessageVO);
	MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");


if (memVO!=null){
	ArticleLikeVO artLikeVO = artLikeService.getOneArticleLike(articleVO.getArtID(), memVO.getMemID());
	pageContext.setAttribute("artLikeVO", artLikeVO);
	// 傳遞按讚資訊

	// 傳遞收藏資訊

	ArticleFavoriteVO articleFavoriteVO = articleFavoriteService.getOneArticleFavorite(articleVO.getArtID(),memVO.getMemID());
	pageContext.setAttribute("articleFavoriteVO", articleFavoriteVO);
	// 傳遞收藏資訊
}
%>

<html>

<head>
<title>${articleVO.artTitle}</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS v5.2.1 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">

<style>
#article_aside::-webkit-scrollbar {
	width: 5px;
}

#article_aside::-webkit-scrollbar-thumb {
	border-radius: 100px;
	-webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
	background: gray;
}
</style>



</head>

<body>

 <!-- 文章類別useBean -->

 <!-- 文章類別useBean -->

  <!-- 頁首 -->
 <c:if test="${ memVO.memID == null}">
	 <%@ include file="/front-end/article/headerSignIn.jsp" %>
 </c:if>
 <c:if test="${ memVO.memID!=null}">
	 <%@ include file="/front-end/article/headerSignOut.jsp" %>
 </c:if>
  <!-- 頁首 -->
  
   
  <!-- 側邊欄位 -->
 <%@ include file="/front-end/article/aside.jsp" %>
  <!-- 側邊欄位 -->

	<!-- 主要 -->
	<main
		style="left: 208px; top: 73.333px; right: 0; background-color: white; height: calc(100vh - 73.33px); width: calc(100vw-208px); position: absolute; overflow: auto;">
		<div id="article_list"
			style="width: 800px; position: relative; left: 136px;">


			<div class="col-md-12">
				<div class="card shadow-sm mb-4 mt-2"
					style="max-width: 650px; position: relative;">
					<div class="card-body d-flex flex-column position-static">
						<article class="blog-post mt-3">
<%-- 檢舉錯誤表列 --%>
      <div style="float: right; margin-top: 50px">
<c:if test="${not empty errorMsgs1}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs1}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>
 <%-- 檢舉錯誤表列 --%>
							<strong class="text-dark" style="font-size: 14px;">${articleVO.memVO.memName}</strong>
							<h1 class="blog-post-title">${articleVO.artTitle}</h1>
							<p class="blog-post-meta">${articleVO.artStime}</p>
							<p>${articleVO.artContent}</p>
							
							<!-- 輪播圖片 -->
<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <c:forEach var="articleImgeListVO" items="${articleVO.articleImgListVO}" varStatus="status">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}" class="<c:if test="${status.first}"> active</c:if>" aria-label="Slide ${status.index + 1}" style="background-color: gray;"></button>
    </c:forEach>
  </div>
  <div class="carousel-inner"  style="position: relative; top: 50%;">
    <c:forEach var="articleImgeListVO" items="${articleVO.articleImgListVO}" varStatus="status">  
      <div class="carousel-item<c:if test="${status.first}"> active</c:if>" style="width: 620px; height: 520px;">
        <img src="<%=request.getContextPath()%>/front-end/article/DBGifReader?imgID=${articleImgeListVO.imgID}" class="d-block center-img" width="100%" height="100%" alt="...">
      </div>
    </c:forEach>
  </div>
  <button class="carousel-control-prev btn btn-secondary" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next btn btn-secondary" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
   				 			<!-- 輪播圖片 -->
   				 	
<div class="mt-4" style="height: auto; width: 250px; display:flex; vertical-align: baseline; ">
    <div class="text-secondary">
      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill text-danger" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"></path>
      </svg>
        &nbsp;<span id="articleLike">${articleVO.artLikeVO.size()}</span>
    </div>
    <div class="text-secondary" style="margin-left: 20px;">
      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-dots-fill text-success" viewBox="0 0 16 16">
        <path d="M16 8c0 3.866-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.584.296-1.925.864-4.181 1.234-.2.032-.352-.176-.273-.362.354-.836.674-1.95.77-2.966C.744 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7zM5 8a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm4 0a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"></path>
      </svg>
        &nbsp;${articleVO.articleMessageVO.size()}
    </div>
    <div class="btn-group" style="position: absolute; right: 20px;">

<c:if test="${memVO != null}">
        <!-- 按讚按鈕 -->
	<c:if test="${artLikeVO.memID != memVO.memID}">
		<input type="hidden" name="artID" id="artID" value="${articleVO.artID}">
		<input type="hidden" name="memID" id="memID" value="${memVO.memID}">
        <input type="hidden" name="action" value="insert">
        <button id="artLikebutton" type="button" class="btn btn-link active insertartLike"  data-bs-toggle="button" autocomplete="off" aria-pressed="true" style="--bs-btn-color: red; --bs-btn-hover-color: red; --bs-btn-active-color: gray;">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart-fill" viewBox="0 0 16 16">
            <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"></path>
            </svg>
        </button>
	</c:if>
	<c:if test="${artLikeVO.memID == memVO.memID}">
		<input type="hidden" name="artID" id="artID" value="${articleVO.artID}">
		<input type="hidden" name="memID" id="memID" value="${memVO.memID}">
        <input type="hidden" name="action" value="delete">
        <button id="artLikebutton" type="button" class="btn btn-link deleteartLike"  data-bs-toggle="button" autocomplete="off" aria-pressed="true" style="--bs-btn-color: red; --bs-btn-hover-color: red; --bs-btn-active-color: gray;">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart-fill" viewBox="0 0 16 16">
            <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"></path>
            </svg>
        </button>
	</c:if>
         <!-- 按讚按鈕 -->

        <!-- 收藏按鈕 -->
	<c:if test="${articleFavoriteVO.memID != memVO.memID}">
		<input type="hidden" name="artID" id="artID" value="${articleVO.artID}">
		<input type="hidden" name="memID" id="memID" value="${memVO.memID}">
        <input type="hidden" name="action" value="insert">        
        <button id="articleFavoritebutton" type="button" class="btn btn-link active insertarticleFavorite" data-bs-toggle="button" autocomplete="off" aria-pressed="true" style="--bs-btn-color: #0d6efd; --bs-btn-hover-color: #0d6efd; --bs-btn-active-color: gray;">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmark-fill" viewBox="0 0 16 16">
                <path d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"></path>
            </svg>
        </button>
	</c:if>      
	<c:if test="${articleFavoriteVO.memID == memVO.memID}">
		<input type="hidden" name="artID" id="artID" value="${articleVO.artID}">
		<input type="hidden" name="memID" id="memID" value="${memVO.memID}">
        <input type="hidden" name="action" value="delete">        
        <button id="articleFavoritebutton" type="button" class="btn btn-link deletearticleFavorite" data-bs-toggle="button" autocomplete="off" aria-pressed="true" style="--bs-btn-color: #0d6efd; --bs-btn-hover-color: #0d6efd; --bs-btn-active-color: gray;">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmark-fill" viewBox="0 0 16 16">
                <path d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"></path>
            </svg>
        </button>
	</c:if>

		<!-- 收藏按鈕 -->

        <!-- 檢舉按鈕 -->        
        <button type="button" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#ArticleReport" style="--bs-btn-color: gray; --bs-btn-hover-color: gray; --bs-btn-active-color: gray;">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle-fill" viewBox="0 0 16 16">
                <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8 4a.905.905 0 0 0-.9.995l.35 3.507a.552.552 0 0 0 1.1 0l.35-3.507A.905.905 0 0 0 8 4zm.002 6a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"></path>
            </svg>
        </button>
</c:if>
  <!-- Modal -->
  <div class="modal fade" id="ArticleReport" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/article/articleReport.do" name="form1">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel" >檢舉原因</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <textarea required name="reportContent" value="" rows="4" class="col-12"></textarea>
        </div>
        <div class="modal-footer">
          <input type="hidden" name="memID" value="${memVO.memID}">
		  <input type="hidden" name="artID" value="${articleVO.artID}">
          <input type="hidden" name="action" value="insert">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="submit" class="btn btn-primary">確認</button>
        </div>
      </div>
      </FORM>
    </div>
  </div>
        <!-- 檢舉按鈕 -->
    </div>
</div>							
						</article>
					</div>
					
   					<!-- 編輯按鈕 -->
<c:if test="${articleVO.memID == memVO.memID}">
<div style="position: absolute; right: 20px; top: 20px;">
	<form class='col-12' METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/article/article.do" name="form1">
        <input type="hidden" name="artID" value="${articleVO.artID}">
    	<input type="hidden" name="memID" value="${memVO.memID}">
    	<input type="hidden" name="action" value="getOne_For_Display_For_Alter_Article">
    	<button type="submit"class="btn btn-link" style="--bs-btn-color: #ced4da; --bs-btn-hover-color: gray; --bs-btn-active-color: gray;">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
          <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
        </svg>
    </button>
      </form>
</div>
</c:if>
   					<!-- 編輯按鈕 -->	
				</div>
			</div>

			<!-- 留言 -->
			<div>
				<div class="card shadow-sm mb-4"
					style="max-width: 650px; position: relative;">
					<div class="card-header">留言區</div>
					<div class="card-body">
						<blockquote class="blockquote mb-0">
							<!-- 留言 -->
							<c:forEach var="articleMessageVO" items="${articleMessageVO}">
								<div style="max-width: 650px; position: relative;">
									<div class="d-flex align-items-center mb-3">
										<div class="d-flex flex-column">
											<strong class="text-dark" style="font-size: 14px;">${articleMessageVO.memVO.memName}</strong>
											<span class="text-muted small" style="font-size: 12px;">${articleMessageVO.msgStime}</span>
										</div>
									</div>
									<p style="font-size: 1rem;">${articleMessageVO.msgContent}</p>
								</div>

								<!-- 留言 -->
								<hr>
							</c:forEach>

						</blockquote>
					</div>
				</div>
			</div>
			<!-- 留言 -->
			<!-- 新增留言 -->
				<!-- 第一種 -->
<!-- 			<div id="expandable-div" -->
<!-- 				style="max-width: 650px; display: none;; position: sticky; bottom: 0px; background-color: white; border: rgba(0, 0, 0, 0.2) 1px solid;"> -->
<!-- 				<div class=" mb-3 mt-3" style="margin-left: 20px;"> -->
<!-- 					<input type="text" id="exampleFormControlTextarea1" class="col-9" -->
<!-- 						placeholder="留言"></input> -->
<!-- 				</div> -->
<!-- 			</div> -->
				<!-- 第一種 --> 
			<!-- 第二種 -->
			<div id="expandable-div"
				style=" z-index:99; height: 100px; max-width: 650px; position: sticky; bottom: 0px; background-color: white; border: rgba(0, 0, 0, 0.2) 1px solid;">
				<div style="margin-top: 5px;">
<%-- 留言錯誤表列 --%>
<div style="text-align: center;  display: inline-block; position:absolute; right: 50%; transform: translate(50%, 0);">
<c:if test="${not empty errorMsgs2}">
	<ul style=" list-style: none ;display: inline-block;">
		<c:forEach var="message" items="${errorMsgs2}">
			<li style="color:red ;display: inline-block;">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>
 <%-- 留言錯誤表列 --%>
<form id="form1">
    <div class="bd-example bd-example-cssgrid">
		<div class="grid" style="--bs-columns: 18; --bs-gap: .5rem; display: flex;">
		  <div style="grid-column: span 14;">
	  	    <div class="mb-3 mt-3" style="margin-left: 10px; margin-right: 10px; width: 551.2;">
		      <textarea  maxlength="200" name="msgContent" class="form-control" id="exampleFormControlTextarea1" rows="2" placeholder="敘述" aria-label=".form-control-lg example"></textarea>
		    </div>
		  </div>
		  <div class="g-col-4">
		  	<div style="position: absolute; right: 10px; top: 30px; display: flex;">
		        <input type="hidden" name="memID" value="${memVO.memID}">
		        <input type="hidden" name="artID" value="${articleVO.artID}">
		        <input type="hidden" name="action" value="insert">
		        <button type="submit" class="btn btn-dark">送出</button>
	    	</div>
		  </div>
		</div>
	</div>
    
</form>
				</div>
			</div>

			<!-- 第二種 -->

			<!-- 新增留言 -->
		</div>
	</main>
	<!-- 主要 -->



	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
		integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
		crossorigin="anonymous">
		
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
		integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
		crossorigin="anonymous">
	</script>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
 <!-- 	按讚   -->
<script>
    const artLikebutton = document.querySelector('#artLikebutton');
    artLikebutton.addEventListener('click', (e) => {
        if (artLikebutton.classList.contains('insertartLike')) {
            console.log('insertartLike');
            $.ajax({
                url: "articleLike.do",
                type: "post",
                data: {
                    artID: $('#artID').val(),
                    memID: $('#memID').val(),
                    action: 'insert'
                },

                success: function (data) {
                    document.querySelector("#articleLike").innerText = data;
                    artLikebutton.classList.toggle('insertartLike');
                    artLikebutton.classList.toggle('deleteartLike');
                    return;
                },
                error: function (err) { console.log(err) }
            });

        };
        if (artLikebutton.classList.contains('deleteartLike')) {
            console.log('deleteartLike');
            $.ajax({
                url: "articleLike.do",
                type: "post",
                data: {
                    memID: $('#memID').val(),
                    artID: $('#artID').val(),
                    action: 'delete'
                },

                success: function (data) {
                    document.querySelector("#articleLike").innerText = data;
                    artLikebutton.classList.toggle('insertartLike');
                    artLikebutton.classList.toggle('deleteartLike');

                    return;
                },
                error: function (err) { console.log(err) }
            });

        };
    });
</script>
<!-- 	按讚   -->
	
<!-- 	收藏   -->
<script>
    const articleFavoritebutton = document.querySelector('#articleFavoritebutton');
    articleFavoritebutton.addEventListener('click', (e) => {
        if (articleFavoritebutton.classList.contains('insertarticleFavorite')) {
            console.log('insertarticleFavorite');
            $.ajax({
                url: "articleFavorite.do",
                type: "post",
                data: {
                	artID: $('#artID').val(),
                    memID: $('#memID').val(),
                    action: 'insert'
                },

                success: function (data) {
                    //             window.location.reload();
                    console.log("test");
                    articleFavoritebutton.classList.toggle('insertarticleFavorite');
                    articleFavoritebutton.classList.toggle('deletearticleFavorite');
                    return;
                },
                error: function (err) { console.log(err) }
            });

        };
        if (articleFavoritebutton.classList.contains('deletearticleFavorite')) {
            console.log('deletearticleFavorite');
            $.ajax({
                url: "articleFavorite.do",
                type: "post",
                data: {
                	artID: $('#artID').val(),
                    memID: $('#memID').val(),
                    action: 'delete'
                },

                success: function (data) {
//                     window.location.reload();
                    articleFavoritebutton.classList.toggle('insertarticleFavorite');
                    articleFavoritebutton.classList.toggle('deletearticleFavorite');
                    return;
                },
                error: function (err) { console.log(err) }
            });

        };
    });
</script>
<!-- 	收藏   -->
	
<!--  留言   -->
<script>   
$(document).ready(function() {
    $('#form1').submit(function(event) {
        event.preventDefault();
        var formData = $(this).serialize();
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/front-end/article/articleMessage.do',
            data: formData,
            success: function(data) {
            	window.location.reload();
                // 在這裡處理服務器返回的數據
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // 在這裡處理錯誤
            }
        });
    });
});
</script>
<!--  留言   -->
	
	
	
	
</body>

</html>