<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	session.invalidate(); // 세션무효화
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<meta http-equiv="Refresh" content="3;url=login.jsp">
<body>
<div align="center">
	<font size="5" face="궁서체">
	로그아웃되었습니다<br><br>
	3초후에 login page로 이동합니다.
	</font>
</div>

</body>
</html>