<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>

<body>
<form name="regForm" method="post" action="member.mdo?cmd=modifyProc">
<table border="1">
<tr>
	<td colspan="2" align="center">회원수정 정보 입력</td>
</tr>

<tr>
	<td align="right">ID : </td>
	<td>
		<input type="hidden" name="id" value="${id}">
		<c:out value="${id}"></c:out>
	</td>
</tr>

<tr>
	<td align="right">PASSWD : </td>
	<td>
		<input type="password" name="pass"value="${pass}">
	</td>
</tr>

<tr>
	<td align="right">비밀번호 확인 : </td>
	<td>
		<input type="password" name="repass" value="${pass}">
	</td>
</tr>

<tr>
	<td align="right">NAME : </td>
	<td>
		<input type="hidden" name="name" value="${name}">
		<c:out value="${name}"></c:out>
	</td>
</tr>

<tr>
	<td align="right">PHONE : </td>
	<td>
	
		<select name="phone1">
			<option value="02" ${phone1 eq '02'?"selected='selected'":'null' }>02</option>
			<option value="010" ${phone1 eq '010'?"selected='selected'":'null' }>010</option>
		</select>&nbsp;-&nbsp;
			
		
		<input type="text" name="phone2" size="4" value="${phone2 }">&nbsp;-&nbsp;
		<input type="text" name="phone3" size="4" value="${phone3 }">
		
	</td>
</tr>

<tr>
	<td align="right">E-MAIL : </td>
	<td>
		<input type="text" name="email" value="${email }">
	</td>
</tr>

<tr>
	<td align="right">POST NO : </td>
	<td>
		<input type="text" name="zipcode" value="${zipcode }">&nbsp;
		<input type="button" value="우편번호 검색" onclick="zipCheck()">
	</td>
</tr>

<tr>
	<td align="right">ADDRESS : </td>
	<td>
		<input type="text" name="address1" size="60" value="${address1 }">
	</td>
</tr>

<tr>
	<td align="right">DETAIL ADDRESS : </td>
	<td>
		<input type="text" name="address2" size="30" value="${address2 }">
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
		<input type="button" value="정보수정" onclick="updateCheck()">&nbsp;&nbsp; 
		<!--  <input type ="submit" value="회원가입">-->
		<input type="reset" value="취소" onclick="javascript:window.location='member.mdo?cmd=login'">
	</td>

</tr>


</table>

</form>
</body>
</html>