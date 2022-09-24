<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>


<meta http-equiv="Refresh" content="3;url=member.mdo?cmd=login">
<body>

<c:set var="result" value="${result }"></c:set>

<div align="center">

	<!-- 비밀번호가 틀릴경우 -->
	<c:if test="${result eq 0 }">
		<script type="text/javascript">
	alert("비밀번호가 맞지 않습니다")
	history.go(-1);
	</script>
	</c:if>

	<font size="5" face="궁서체">
	회원탈퇴완료<br><br>
	3초후에 login page로 이동합니다.
	</font>
</div>



</body>
</html>