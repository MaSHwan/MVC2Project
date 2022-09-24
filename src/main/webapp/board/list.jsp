<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <%@ include file="base.jsp" %>
    

    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../views/list.css">
</head>
<body bgcolor = "${bodyback_c }">

<div id="contents" class="city">
	<div class="conts-container">
		<div class="present">

 

<div class="tabs-list">
<ul class="tabs-list">
	
	<li>
		<a href="/Mvc2Project/board/list.bdo">공지 게시판</a><br>
	</li>
	
	<li>
		<a href="../freeboard/freelist.jsp">자유 게시판</a>
	</li>
	
	<li>
		<a href="#">루트추천 게시판</a>
	</li>
	
	<li>
		<a href="#">루트질문 게시판</a>
	</li>
	
	<li>
		<a href="#">동행 게시판</a>
	</li>
	
	<li>
		<a href="#">나눔 게시판</a>
	</li>
</ul>
</div>  
   
   <div class="conts-box-list-search">
   	<div class="inner-box">
   		<div class="city-community">
   			<div class="write-tour-story">
   			<p>[공지] 여행 이야기를 남겨주세요.</p>
   			<a><button class="openBtn">포스트 작성</button></a>
   			</div> <!-- end write-tour-story -->
<div class="modal hidden">
  <div class="bg"></div>
  	<div class="mBox">
	     <div class='modal-dialog'>
       		 <div class='modal-contents'>
         	   
         	   <form action="/Mvc2Project/board/writePro.bdo" method="post" name="writeForm" onsubmit="return writeSave()">
         	   <input type="hidden" name="num" value="${num }">
         	 	 <div class='modal-header'>
            	 	 <p align="center">포스트 작성</p>
           	 	 </div><hr>
          
          		  <div class="modal-writer">
           		
           			<input type="text" size="12" maxlength="12" name="writer" placeholder="작성자">
           		  </div>
           
           		  <div class="modal-title">
           			
           			<input type="text" size="50" maxlength="50" name="subject" placeholder="제목">
           		  </div>
         
         	 	 <div class='modal-body' align='center'>
					<div class="modal-content">
						<textarea class="modal-content" name="content" placeholder="내용"></textarea>
					</div>
         		  </div>
         	
         		  <div class='modal-footer' id='modalBtn'>
         			<input type="submit" value="저장">
         	 	 </div>
         	 </form>	 
     	 </div><!-- end div modal-contents -->
  	 </div><!-- end div modal-dialog -->
  </div>
</div>

   			
   
   			<script>
  const open = () => {
    document.querySelector(".modal").classList.remove("hidden");
  }

  const close = () => {
    document.querySelector(".modal").classList.add("hidden");
  }

  document.querySelector(".openBtn").addEventListener("click", open);
  document.querySelector(".closeBtn").addEventListener("click", close);
  document.querySelector(".bg").addEventListener("click", close);

</script>



<c:if test="${count>0 }">

   
 <c:forEach var="article" items="${articleList }">
   
   
   
   
   <div class="community-contents">
	<div class="city-inner-box">
		<div class="community-info-box">
   
   
   
   <h4 class="community-tit"> 
   <a href = "/Mvc2Project/board/content.bdo?num=${article.num }&pageNum=${currentPage}">
  ${article.subject}
  </a>
  </h4>

  
   
  <div class="long-desc">
  	<a href="/Mvc2Project/board/content.bdo?num=${article.num }&pageNum=${currentPage}">
  	${article.content }
  	</a>
  </div>
  
  
  
  <div class="day-time">
  	<span>${article.regdate }</span>
  </div>
  
  
  
  <div class="txt-info">
  
  <p>${article.writer } 님이 남긴 포스트입니다.</p>
  
  </div> <%-- end div txt-info --%>
  
</div> <!-- end div community-info-box -->
</div> <!--end div city-inner-box  -->
</div> <!-- end div community-contents -->
   

</c:forEach>   

 

</c:if>

</div> <!-- end div city-community -->






	 <!-- end div 글목록 -->
	
	</div> <!-- end div innerbox -->
	
	<c:if test="${count > 0 }">
	<c:set var="pageBlock" value="${10 }"/>
	<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1 }"/>
 	<c:set var="pageCount" value="${count/pageSize + imsi }"/>

	<fmt:parseNumber var="result" value="${(currentPage-1) / pageBlock }" integerOnly="true"/> 
   
 	<c:set var="startPage" value="${result * pageBlock + 1}"/>
 	<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>

 

 <c:if test="${endPage > pageCount }">
 <c:set var="endPage" value="${pageCount }"/>
 </c:if>
 
<div class="paging">
 
 <c:if test="${startPage > pageBlock}">
   <a class="btn-prev" href="/Mvc2Project/board/list.bdo?pageNum=${startPage - pageBlock }">이전</a>
 </c:if>
 
 <ul>
 
 
 <c:forEach var="i" begin="${startPage }" end="${endPage }"><li>
   <a class="active" href="/Mvc2Project/board/list.bdo?pageNum=${i }">${i }</a></li>
 </c:forEach>

</ul>



	<c:if test="${endPage < pageCount }">
    <a class="btn-next" href="/Mvc2Project/board/list.bdo?pageNum=${startPage + pageBlock }">다음</a>
	</c:if>

</div> <%-- end div paging --%>
</c:if>
	
	</div> <!-- end div conts-box-list search -->
	
	</div> <!-- end div present -->
	
	
	
	</div> <!-- end div conts-container -->
	
	
	
 
</div><!-- end div content city -->

</body>
</html>