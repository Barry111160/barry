<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.musclebeach.article.model.ArticleVO"%>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeVO"%>
<%@ page import="com.musclebeach.articleType.model.ArticleTypeService"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.musclebeach.common.util.ApplicationContextUtil" %>
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
  <title>AddArticle</title>
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
    
    #addArticle_main::-webkit-scrollbar {
      width: 5px;
    }

    #addArticle_main::-webkit-scrollbar-thumb {
      border-radius: 100px;
      -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
      background: gray;

    }
</style>

<!-- 預覽圖 -->
 <style>
        #previewContainer {
  display: flex;
  flex-wrap: wrap;
}
.preview {
  width: 200px;
  height: 200px;
  margin: 10px;
  position: relative;
  overflow: hidden;
  border-radius: 5px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  transition: all 0.3s ease-in-out;
}

.preview:hover {
  transform: scale(1.05);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.19), 0 9px 9px rgba(0, 0, 0, 0.23);
}

.preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.preview .remove {
  position: absolute;
  top: 5px;
  right: 5px;
  width: 30px;
  height: 30px;
  text-align: center;
  line-height: 30px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  font-size: 16px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
}

.preview .remove:hover {
  background-color: #f44336;
}
</style>
<!-- 預覽圖 -->
  
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
  <main   id ="addArticle_main"
    style="left: 100px;top: 80px;right: 100px; ;height: calc(100vh - 73.33px); width: calc(100vw-208px); position: absolute; overflow:auto;">
    <div class="p-5 mb-4 bg-light rounded-3">
      <div style="float: left;">
        <div class="btn-group">
          <select name="typeID" class="btn btn-secondary text-white" style="text-align:left;">
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

	<input name="artImg" type="file" accept="image/jpeg,image/png,image/gif,image/webp,image/tiff" id="fileInput" class="atm_9s_glywfm i1k8vwk9" value="" multiple />
    <div id="previewContainer"></div>
	
        <button class="btn btn-primary btn-md" type="button" style="float: right;" data-bs-toggle="modal" data-bs-target="#exampleModal">下一步</button>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-body text-body fs-4">
                確認送出
              </div>
              <div class="modal-footer">
                <input type="hidden" name="memID" value="${ memVO.memID}">
              	<input type="hidden" name="action" value="insertwithImg">
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



  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
    integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
    </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
    integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
    </script>
    
      <script>
      const input = document.querySelector('#fileInput');
      const previewContainer = document.querySelector('#previewContainer');
      input.onchange = event => {
    	  previewContainer.innerHTML = '';
    	  const files = input.files;
    	  for (let i = 0; i < files.length; i++){
    		  const image = document.createElement('img');
    		  image.src = URL.createObjectURL(files[i]);
    		  const preview = document.createElement('div');
    		  preview.classList.add('preview');
    		  const remove = document.createElement('div');
    		  remove.id = 'remove' + i;
    		  remove.classList.add('remove');
    		  remove.innerText = 'X';
    		  remove.onclick = e => {
    			  const dataTransfer = new DataTransfer();
    			  const input2 = document.querySelector('#fileInput');
    			  const str = e.target.id;
    			  const id = str.substring(str.length - 1);
    			  preview.remove();
    			  for (let j = 0; j < input2.files.length; j++) {
    				  if (parseInt(id) != j) {
    					  dataTransfer.items.add(input2.files[j]);
    				  }
    			  }
    			  input2.files = dataTransfer.files;
    			  let removeList = document.querySelectorAll('.remove');
    			  for (let k = 0; k < removeList.length; k++) {
    				  removeList[k].id = 'remove' + k;
    			  }
    		  }
    		  preview.appendChild(image);
    		  preview.appendChild(remove);
    		  previewContainer.appendChild(preview);
    	  }
      }
  </script>
    
    
</body>

</html>