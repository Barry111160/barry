 <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


  <aside class="d-flex flex-nowrap" style="top:73.333px; position: absolute;">
    <div class="d-flex flex-column flex-shrink-0 p-3 bg-dark"
      style="width: 208px; height: calc(100vh - 73.33px); overflow: auto;" id="article_aside">
      <ul class="nav nav-pills flex-column mb-auto" id="v-pills-tab" role="tablist" aria-orientation="vertical"
        style="text-align: center">
        <li class="nav-item">
          <a class="nav-link text-white"type="button" style="background-color:Dimgray;font-size: 1.5rem" href="<%=request.getContextPath()%>/front-end/indexlogin.jsp">
            <i class="bi bi-house-door" style="margin-right: 8px"></i>
            首頁
          </a>
        </li>
        <hr/>
        <li>
          <div  style="float: left;">
            <h7 style="color: lightgray; margin: 10px">文章類別</h7>
          </div>
        </li>



         <c:forEach var="articleTypeVO" items="${typeList}" >
         <form class="mb-2" METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/article/article.do" name="form1">
        <li class="nav-item" >
            <input type="hidden" name="action" value="getOne_For_Display_BY_TypeID">
            <input type="hidden" name="typeID" value="${articleTypeVO.typeID}" >
          <button  style="margin-left: 32.5px" type="submit" class="nav-link text-white">
            <i class="bi bi-person" style="color: white; margin: 5px"></i>
            ${articleTypeVO.typeName}
          </button>
        </li>
         </form>
        </c:forEach> 
      </ul>
    <div class="tab-content" id="v-pills-tabContent">
  </aside>