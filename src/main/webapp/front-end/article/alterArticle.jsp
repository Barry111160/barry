<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.musclebeach.article.model.*"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeService" %>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeVO" %>
<%@ page import="com.musclebeach.mem.model.MemVO" %>

<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
    ApplicationContext ctx = ApplicationContextUtil.getContext();
    assert ctx != null;
    ArticleTypeService articleTypeService = ctx.getBean(ArticleTypeService.class);
    List<ArticleTypeVO> typeList = articleTypeService.getAll();
    pageContext.setAttribute("typeList", typeList);
    MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
%>
<html>
<head>
  <title>Title</title>
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

<body class="bg-dark overflow-hidden" style="--bs-bg-opacity: .95;">

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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/article/article.do" name="form1" enctype="multipart/form-data" >
 
  <!-- 主要 -->
  <main
    style="left: 100px;top: 80px;right: 100px; ;height: calc(100vh - 73.33px); width: calc(100vw-208px); position: absolute; overflow:auto;">
    <div class="p-5 mb-4 bg-light rounded-3">
      <div style="float: left;">
        <div class="btn-group">
          <select name="typeID" class="btn btn-secondary text-white disabled" style="text-align:left;">
            <option>點此選擇發文類別</option>
            <c:forEach var="articleTypeVO" items="${typeList}">
         	 <option value="${articleTypeVO.typeID}"${(articleVO.typeID==articleTypeVO.typeID)? 'selected':'' }>${articleTypeVO.typeName}</option>
         	</c:forEach> 
          </select >
        </div>
        <div>
          <p class="text-dark fs-4 mt-3" style="float: left;">${memVO.memName}</p>
        </div>
      </div>
<%-- 錯誤表列 --%>
      <div style="float: right;">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>
 <%-- 錯誤表列 --%>
      <div class="mb-2">
        <input name="artTitle" value="<%= (articleVO==null)? "" : articleVO.getArtTitle()%>" class="form-control" id="exampleFormControlInput1" placeholder="標題" style="font-size: larger;" >
      </div>
      <div class="mb-3">
        <textarea  name="artContent"  class="form-control" id="exampleFormControlTextarea1" rows="6" placeholder="敘述" aria-label=".form-control-lg example"><%= (articleVO==null)? "" : articleVO.getArtContent()%></textarea>
      </div>
      <div class="container-fluid py-4">
		<!--  修改圖片按鈕 -->
		<button type="button" class="btn btn-outline-light"  style="float: left; --bs-btn-color: none;" data-bs-toggle="modal" data-bs-target="#exampleModal">
            <svg viewBox="0 0 24 24" class="jsx-952c5c783df17c88 atm_mj_glywfm atm_tl_glywfm sgb9dab" focusable="false" width="20" height="20" role="img" aria-hidden="true" style="fill: var(--color-text-default);">
              <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2zM16.5 5.5a2 2 0 0 0-2 2 2 2 0 0 0 2 2 2 2 0 0 0 2-2 2 2 0 0 0-2-2zm2.5 13v-3l-2.37-2.77a1 1 0 0 0-1.38-.13L13.5 14 9.7 9.78a1 1 0 0 0-1.45-.04L5 13v5.5a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5z"></path>
            </svg>
            修改圖片       
         </button>
		<!--  修改圖片按鈕 -->        
         <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable">
              <div class="modal-content" style="width: 500px;">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">修改圖片</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body"zz style="display: flex;">
                <!--  修改圖片 -->  
                <c:forEach var="articleImgeListVO" items="${articleVO.articleImgListVO}">
					<div id="img${articleImgeListVO.imgID}" class="text-center">
				    	<img src="<%=request.getContextPath()%>/front-end/article/DBGifReader?imgID=${articleImgeListVO.imgID}" class="rounded d-block mx-3 mb-2" width="200" height="200" alt="...">
	                 	<div class="box mb-2" style="display:flex; justify-content: center; align-items: center;">

<button type="button" class="btn btn-outline-danger float-end" onclick="deleteArticleImg('${articleImgeListVO.imgID}')">
  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
    <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"></path>
  </svg>
  刪除
</button>
	                 	</div>
	               </div>
	              </c:forEach>
	            <!--  修改圖片 -->    
                </div>
                <div class="modal-footer">
                  <button type="button" id="upload-button" class="btn btn-outline-light btn-sm" style="float: left; --bs-btn-color: none; --bs-btn-border-color: none; --bs-btn-hover-border-color: none;">
					  <label class="atm_9j_tlke0l atm_9s_1txwivl atm_f_1h6ojuz l8leymb" for="editor-image">
					    <svg viewBox="0 0 24 24" class="jsx-952c5c783df17c88 atm_mj_glywfm atm_tl_glywfm sgb9dab" focusable="false" width="20" height="20" role="img" aria-hidden="true" style="fill: var(--color-text-default);">
					      <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2zM16.5 5.5a2 2 0 0 0-2 2 2 2 0 0 0 2 2 2 2 0 0 0 2-2 2 2 0 0 0-2-2zm2.5 13v-3l-2.37-2.77a1 1 0 0 0-1.38-.13L13.5 14 9.7 9.78a1 1 0 0 0-1.45-.04L5 13v5.5a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5z">
					      </path>
					    </svg>
					    <input name="artImg" type="file" accept="image/jpeg,image/png,image/gif,image/webp,image/tiff" id="editor-image" class="atm_9s_glywfm i1k8vwk9" style="width: 200px" value="" multiple>
					  </label>
					</button>
					<button type="button" id="submit-button" class="btn btn-light">
					    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cloud-upload-fill" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M8 0a5.53 5.53 0 0 0-3.594 1.342c-.766.66-1.321 1.52-1.464 2.383C1.266 4.095 0 5.555 0 7.318 0 9.366 1.708 11 3.781 11H7.5V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11h4.188C14.502 11 16 9.57 16 7.773c0-1.636-1.242-2.969-2.834-3.194C12.923 1.999 10.69 0 8 0zm-.5 14.5V11h1v3.5a.5.5 0 0 1-1 0z"/>
						</svg>
						&nbsp;上傳
             		</button>
                </div>
              </div>
            </div>
          </div>
        <button class="btn btn-primary btn-md" type="button" style="float: right;" data-bs-toggle="modal" data-bs-target="#example">下一步</button>
        <div class="modal fade" id="example" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-body text-body fs-4">
                確認送出
              </div>
              <div class="modal-footer">
                <input type="hidden" name="artID" value="${articleVO.artID}">
                <input type="hidden" name="memID" value="${memVO.memID}">
              	<input type="hidden" name="action" value="getOne_For_Update">
                <button type="button" class="btn btn-secondary text-white" data-bs-dismiss="modal">否</button>
                <button type="submit" class="btn btn-primary">是</button>
              </div>
            </div>
          </div>
        </div>
        <button class="btn btn-light btn-md" type="button"  onclick="history.back()" style="float: right;">取消</button>
      </div>
    </div>  
  </main>
 </FORM>
  <!-- 主要 -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 
<script>
  function deleteArticleImg(imgID) {
    $.ajax({
      type: "post",
      url: "<%=request.getContextPath()%>/front-end/article/articleImg.do",
      data: {
        imgID: imgID,
        action: "delete"
      },
      success: function(response) {
        document.querySelector("#img" + imgID).remove();
      },
      error: function(jqXHR, textStatus, errorThrown) {
        // Handle the error
        console.error("Error: " + textStatus, errorThrown);
      }
    });
  }
</script>
<script>
$("#submit-button").click(function() {
    var formData = new FormData();
    formData.append("artID", "${articleVO.artID}");
    const input = document.querySelector('#editor-image');
    for (let i = 0; i < input.files.length; i++) {
    	formData.append("artImg", input.files[i]);
    }
	formData.append("action","insert");
    $.ajax({
      url: "<%=request.getContextPath()%>/front-end/article/articleImg.do",
      type: "post",
      data: formData,
      processData: false,
      contentType: false,
      success: function(response) {
    	  window.location.reload();
      },
      error: function(jqXHR, textStatus, errorThrown) {
        // 处理错误
      }
    });
  });
</script>


  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
    integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
    </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
    integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
    </script>
</body>

</html>