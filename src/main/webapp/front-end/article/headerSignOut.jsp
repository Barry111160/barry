<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

  <!-- ⾴⾸ -->
  <header class="p-3 bg-dark fixed-top" style="height: 73.33px">
    <div>
      <div class="d-flex flex-wrap justify-content-lg-start">
        <span class="fs-4 col-lg-2 me-4" style="color: white;"><a href="listAllArticle.jsp" style="color: white;text-decoration: none;">Muscle Beach</a></span>
        <form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/article/article.do" name="form2" class="col-12 col-lg-3 mb-lg-0" style="display: flex; flex-wrap: wrap; width: 33%">
          <input required type="search" name="artTitle" class="form-control form-control-dark" style="width: 320px;" placeholder="Search..."
            aria-label="Search">
              <input type="hidden" name="action" value="getOne_For_Display_BY_Article_Title_OR_Article_cintent">
          <button type="submit" class="btn btn-dark text-white" style="margin-left: 10px;">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
              <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
            </svg>
          </button>
        </form>
        <ul class="nav col-12 col-lg-4 me-lg-auto mb-md-0" style="align-items: center;">
          <li>
          	<button type="submit" class="btn btn-dark text-white" data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop">
	            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-question-circle" viewBox="0 0 16 16">
				  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
				  <path d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"/>
				</svg>
	            &nbsp;進階查詢
          	</button>
<div class="offcanvas offcanvas-top" tabindex="-1" id="offcanvasTop" aria-labelledby="offcanvasTopLabel" style="--bs-offcanvas-color: #fff; --bs-offcanvas-bg: #212529;">
     <div class="offcanvas-header" style="padding: 5;">
       <h5 id="offcanvasTopLabel" style="margin-bottom: 0;">進階查詢</h5>
       <button type="button" class="btn" data-bs-dismiss="offcanvas" aria-label="Close" style="--bs-btn-color: none;">
                 <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
       <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
     </svg>
    </button>
     </div>
			  <div class="offcanvas-body" style="padding: 5;">
			    <form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/article/article.do" name="form1" class="row g-3 w-100 " METHOD="post">
		          <div class="col-2">
		            <label for="mem_name" class="form-label">輸入會員名稱:</label>
		            <input type="text" class="form-control" id="mem_name" name="mem_name" value="">
		          </div>
		          <div class="col-2">
		            <label for="artTitle" class="form-label">輸入文章標題:</label>
		            <input type="text" class="form-control" id="artTitle" name="art_title" value="">
		          </div>

		          <div class="col-3">
		            <label for="inputState" class="form-label">選擇文章類別:</label>
		            <select id="inputState" class="form-select" name = "type_id" value ="">
		            		<option value="">
		                <c:forEach var="articleTypeVO" items="${typeList}" >
          					<option value="${articleTypeVO.typeID}">${articleTypeVO.typeName}
         				</c:forEach> 
		            </select>
		          </div>
		          <div class="col-3">
		            <label for="artContent" class="form-label">輸入文章內容:</label>
		            <input type="text" class="form-control" id="artContent" name="art_content" value="">
		          </div>
		          <div class="col-1 mx-auto mt-5">
		            <input type="hidden" name="action" value="listArticles_ByCompositeQuery">
		            <button type="submit" class="btn btn-secondary">
               			查詢&nbsp;
      					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                		 <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
               			</svg>
     				</button>
		          </div>
		        </form>
			  </div>
			</div>
          </li>
          <li style="width: 175px;"></li>
          <li>
          <a href="addArticle.jsp" style="color: white;text-decoration: none;">
          <button class="nav-link px-3 btn btn-dark text-white">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
              <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
            </svg>
          </button>
          </a>  
          </li>         
      	  <li>
      		<a href="listAllArticleFavorite.jsp" style="color: white;text-decoration: none;">
      		<button class="nav-link px-3 btn btn-dark text-white" autocomplete="off" aria-pressed="true">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
              <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
            </svg>
          </button>
          </a>
          </li>
        </ul>
        <div class="text-end ">
          <button type="button" class="btn btn-light">Sign out</button>
        </div>
      </div>
    </div>
  </header>
  <!-- ⾴⾸ -->