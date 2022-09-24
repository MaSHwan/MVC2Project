<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   
<c:if test="${check == 1 }">
	<meta http-equiv="Refresh" content="0,url=/Mvc2Project/board/list.bdo"/>
</c:if>

<c:if test="${check == 0 }">
	비밀번호가 다릅니다.
	<br>
	<a href="javascript:history.go(-1)">[글 수정 폼으로 돌아가기]</a>
</c:if>

