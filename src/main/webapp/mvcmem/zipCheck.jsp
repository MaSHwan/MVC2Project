<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mvcmem.model.ZipCodeVO" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript">
function sendAddress(zipcode,sido,gugun,dong,ri,bunji){
	var address= sido + " " + gugun + " " + dong + " " + ri + " " +  bunji;
	opener.document.regForm.zipcode.value=zipcode;
	opener.document.regForm.address1.value=address;
	self.close();
}

</script>

</head>
<body bgcolor="#FFFFCC">
<div align="center">
<b>우편번호 찾기</b>
<form action="member.mdo?cmd=zipCheck" name="zipForm" method="post">
<input type="hidden" name="check" value="n">
<table>
	<tr>
		<td>
			동이름 입력: <input type="text" name="dong">
			<input type="button" value="검색" onclick="dongCheck()">
		</td>
	</tr>
</table>
</form>

<table border="1">
<c:if test="${check eq 'n' }">
	<c:if test="${zipcodeList.isEmpty() }">
		<tr><td align="center"><br>검색된 결과가 없습니다.</td></tr>
	</c:if>
	
	<c:if test="${!zipcodeList.isEmpty()}">
		<tr><td align="center"><br>검색후, 아래 우편번호를 클릭하면 자동으로 주소가 입력</td></tr>
		<c:forEach var="vo" items="${zipcodeList}">
			<tr><td>
			<a href="javascript:sendAddress('${vo.getZipcode() }','${vo.getSido() }','${vo.getGugun() }','${vo.getDong() }','${vo.getRi() }','${vo.getBunji() }')">
					${vo.getZipcode()}&nbsp;${vo.getSido() }%>&nbsp;${vo.getGugun() }&nbsp;${vo.getDong() }&nbsp;${vo.getRi() }&nbsp;${vo.getBunji() }
		</a><br>
		
			</td></tr>
		</c:forEach>
		
	</c:if>
</c:if>


	<tr>
		<td align="center">
			<a href="javascript:this.close()">닫기</a>
		</td>
	
	</tr>

</table>

</div>

</body>
</html>