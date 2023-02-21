<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.musclebeach.article.model.*"%>
<%@ page import="com.musclebeach.articleFavorite.model.*"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeService" %>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeVO" %>
<%@ page import="com.musclebeach.mem.model.MemVO" %>

<%
    MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
    ApplicationContext ctx = ApplicationContextUtil.getContext();
    assert ctx != null;
    ArticleTypeService articleTypeService = ctx.getBean(ArticleTypeService.class);
    List<ArticleTypeVO> typeList = articleTypeService.getAll();
    pageContext.setAttribute("typeList", typeList);
    ArticleService articleService = ctx.getBean(ArticleService.class);
    List<ArticleVO> list = articleService.getAllByMemID(memVO.getMemID());
    pageContext.setAttribute("list", list);
	Iterator<ArticleVO> iterator = list.iterator();
	while(iterator.hasNext()){
	    ArticleVO articleVO = iterator.next();
	    if(articleVO.getArtStatus() != 1)
	        iterator.remove();   //注意这个地方
	}

%>


<!doctype html>

<head>
  <title>MyArticle</title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS v5.2.1 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

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

<body class="bg-dark" style="--bs-bg-opacity: .95; overflow-x: hidden;">

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
  

  <!-- 主要 -->
  <main style="top: 73.333px; position: absolute;">
    <div class="row gx-1">

      <!-- 側邊欄 -->
      <div class="col" style="position: fixed; margin-left: 100px;">
        <div class="box" style="width:250px; display:flex; justify-content: center; align-items: center;">
          <div class="col gy-3">
            <div class="row">
              <div class="box" style="display:flex; justify-content: center; align-items: center;">
                <div class="col gy-1">
                  <div class="row">
                    <div class="box" style="display:flex; justify-content: center; align-items: center;">
                      <div class="mb-3 mt-5">
                        <svg xmlns="http://www.w3.org/2000/svg" width="120" height="120" fill="currentColor" class="bi bi-person-circle text-white" viewBox="0 0 16 16">
                          <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
                          <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"></path>
                        </svg>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="box text-white fs-3" style="display:flex; justify-content: center; align-items: center;">
                        ${memVO.memName}
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="box" style="display:flex; justify-content: center; align-items: center;">
                <div class="col gy-2">
                  <div class="row">
                    <div class="box" style="height: 50px; display:flex; justify-content: center; align-items: center;">
                      
                    </div>
                  </div>
                  <div class="row">
                    <div class="box" style="display:flex; justify-content: center; align-items: center;">
                      <a href="listAllArticleFavorite.jsp" style="color: white;text-decoration: none;">
                      <button type="button" class="btn btn-outline-dark text-white">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmark-fill text-primary" viewBox="0 0 16 16">
                          <path d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"></path>
                        </svg>
                          &nbsp;&nbsp;我的收藏
                      </button>
                      </a>
                    </div>
                  </div>
                  <div class="row mt-2">
                    <div class="box" style="display:flex; justify-content: center; align-items: center;">
                      <a href="listAllMyArticle.jsp" style="color: white;text-decoration: none;">
                      <button type="button" class="btn btn-outline-dark text-white">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-book-half text-info" viewBox="0 0 16 16">
                          <path d="M8.5 2.687c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492V2.687zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z"></path>
                        </svg>
                          &nbsp;&nbsp;我的文章
                      </button>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 側邊欄 -->

      <!-- 文章 -->
      <div class="col" style="margin-left: 350px; width: 834px;">
        <div class="box" style="display:flex; justify-content: center; align-items: center; overflow-x: hidden;">
          <div class="card mt-2">
            <div class="card-header fs-4">
              &nbsp;&nbsp;我的文章
              <div class="mt-2">
              </div>
            </div>
            <div class="card-body" style="overflow: auto; height: calc(100vh - 145.33px);width: 834px;">
 
  	<c:if test="${list.size() == 0}"> <%-- 判斷是否有文章  --%>
	<h1 style="display: flex; justify-content:center ;">暫無文章</h1>
	</c:if>
 
 <c:forEach var="articleVO" items="${list}">
        <c:if test="${articleVO.artStatus == 1}"> <%-- 判斷是否隱藏文章 --%>
                <!-- 按紐一 -->
                <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#article${articleVO.artID}" 
                    style="text-align: start;min-width: 800px">
                    <div class="col-md-12">
                        <div
                            class="row g-0 border rounded overflow-hidden flex-md-row mb-2 shadow-sm h-md-250 position-relative">
                            
                            <div class="col p-4 d-flex flex-column position-static">
                                <strong
                                    class="d-inline-block mb-2 text-primary">${articleVO.articleTypeVO.typeName}</strong>
                                <h3 class="mb-0">${articleVO.artTitle}</h3>
                                <div class="mb-1 text-muted">${articleVO.artStime}</div>
              <p class="card-text mb-auto" style=" font-size: 14px; line-height: 1.5; display: -webkit-box; -webkit-line-clamp: 2;  -webkit-box-orient: vertical;  overflow: hidden;  text-overflow: ellipsis;  word-break: break-all;">
              ${articleVO.artContent}
				</p>
                                <!-- 			 按讚數 留言量 -->
                                <div class="mt-2"
                                    style="height: auto; width: 250px; display:flex; vertical-align: baseline; ">
                                    <div class="text-secondary">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-heart-fill text-danger"
                                            viewBox="0 0 16 16">
                                            <path fill-rule="evenodd"
                                                d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z" />
                                            
                                        </svg>
                                        &nbsp;${articleVO.artLikeVO.size()}
                                    </div>
                                    <div class="text-secondary" style="margin-left: 20px;">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-chat-dots-fill text-success"
                                            viewBox="0 0 16 16">
                                            <path
                                                d="M16 8c0 3.866-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.584.296-1.925.864-4.181 1.234-.2.032-.352-.176-.273-.362.354-.836.674-1.95.77-2.966C.744 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7zM5 8a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm4 0a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                                            
                                        </svg>
                                        &nbsp;${articleVO.articleMessageVO.size()}
                                    </div>
                                    <div class="text-secondary" style="margin-left: 20px;">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-bookmark-fill text-primary"
                                            viewBox="0 0 16 16">
                                            <path
                                                d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z">
                                            </path>
                                        </svg>
                                        &nbsp;收藏
                                    </div>
                                </div>
                                <!-- 			 按讚數 留言量 --> 
                            </div>
                            <div class="col-auto d-none d-lg-block">
                                <svg class="bd-placeholder-img" width="200" height="216"
                                    xmlns="http://www.w3.org/2000/svg" role="img"  aria-label="Placeholder: Thumbnail"
                                    preserveAspectRatio="xMidYMid slice" focusable="false">
                                    <title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#55595c"></rect>
                 <!-- 圖片-->        <image style="width: 100%; height: 100%;"
                                        xlink:href="<%=request.getContextPath()%>/front-end/article/DBGifReader?imgID=${articleVO.articleImgVO.imgID}">
                                    </image>
                                </svg>
                            </div>
                        </div>
                    </div>
                </button>
                
                <!-- 按鈕一 -->
                <!-- Modal 文章 -->
                <div class="modal fade" id="article${articleVO.artID}" tabindex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">${articleVO.artTitle}</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
            				<div class="modal-body"style="  white-space: wrap;word-wrap: break-word;">
             					 ${articleVO.artContent}
            				</div>
                            <div class="modal-footer">
                                <form class='col-12' METHOD="post"
                                    ACTION="<%=request.getContextPath()%>/front-end/article/article.do" name="form1"">
             						<input type="hidden" name="artID" value="${articleVO.artID}">
                                    <input type="hidden" name="memID" value="${articleVO.memID}">
                                    <input type="hidden" name="action" value="getOne_For_Display">
                                    <button type="submit" class="col-12 btn btn-secondary"
                                        data-bs-dismiss="modal">查看留言</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
        </c:if> 
</c:forEach>
      <!-- Modal 文章 -->  
              
              </div>
          </div>
        </div>
      </div>
      <!-- 文章 -->

      <div class="col">
        <div class="box" style="display:flex; justify-content: center; align-items: center;">
        </div>
      </div>
    </div>
  </main>
  <!-- 主要 -->



  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
    integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
    </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
    integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
    </script>
</body>

</html>