<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ include file = "base.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../views/content.css">
</head>

<body>
<div id="note_page_panel" style="min-height:600px;">
<br><br> 
	<div class="modal-dialog comment-pop">
		<div class="modal-content spot-comment-pop">
			<div class="modal-header">
				<div class="tit-bar">
					
					<div class="author-infor">
						<div class="name-time">
						<span class="user-name">${article.writer }</span>

					</div>
					<span class="data">
						<span class="time">${article.regdate}</span>
					</span>
					
					</div><%-- end div author-infor --%>
					
					
				</div><%-- end div tit-bar --%>
			</div> <%--end div modal-header --%>
			
			<div class="modal-body">
				<div id="note_detail_panel">
					<div class="spot-contents">
						<div id="note_panel">
							
							<div class="conts-tit-bar">
							<span class="boardname">공지</span>
								<div class="p">
								<div class="left">
									<h1>${article.subject }</h1>
									</div>
									<div class="right">
								<button onclick="document.location.href='/Mvc2Project/board/list.bdo?pageNum=${pageNum }'"><img src="../images/list.png" width="24px" height="24px"></button>
								<button onclick="document.location.href='/Mvc2Project/board/updateForm.bdo?num=${article.num }&pageNum=${pageNum }'"><img src="../images/edit.png" width="24px" height="24px"></button>
								<button onclick="document.location.href='/Mvc2Project/board/deleteForm.bdo?num=${article.num }&pageNum=${pageNum }'"><img src="../images/delete.png" width="24px" height="24px"></button>
								</div>
								
								
								</div>
								
							</div><%-- end div conts-tit-bar --%>
							
							<div class="conts-txt-area">
								<p>${article.content }</p>
							</div><%--end div conts-txt-area --%>
							
							<div class="author-info">
								<div class="tit-bar"></div>
							
							<div class="conts-label">
								<div class="spot-like-count">
									<a>좋아요
									<span>0</span>
									</a>
								</div><%-- end div spot-like-count --%>
								
								<div class="spot-commont-count">
									<a class="show-comments-of-note">댓글
										<span>0</span>
									</a>
								</div>
							</div><%-- end div conts-label --%>
							</div><%-- end div author-info --%>
						</div><%-- end div note_panel --%>
						
						<div id="note-comment-area">
							<div class="plan-comment">
								<div class="spot-comment-content">
									<div class="comment-write">
										<textarea class="form-control new-comment-textarea"
										placeholder="댓글을 남겨주세요"></textarea>
										
										<div class="comment-edit-btns">
											<button class="btn btn-primary save-new-comment">
											저장</button>
										</div>
									</div><%-- end div comment-write --%>
								</div><%-- end div spot-comment-content --%>
							
							
							</div><%-- end div plan-comment --%>
						</div><%-- end div note-comment-area --%>
					</div><%-- end div spot-contents --%>
				
				</div><%-- end div note_detail_panel --%>
			</div><%-- end div modal-body --%>
			
		</div><%-- end div modal-content spot-comment-pop --%>
	</div> <%-- end div modal dialog comment top --%>
</div> <%-- end div note page panel --%>





</body>
</html>