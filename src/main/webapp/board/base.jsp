<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../views/base.css">
<link rel ="shortcut icon" href="#">
<link href="https://fonts.googleapis.com/css2?family=DynaPuff:wght@700&family=Gowun+Dodum&display=swap" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script> <!--jquery사용 -->
<script type="text/javascript" src="js/CityInfo.js"></script>
<script src="https://kit.fontawesome.com/e14a2b80fa.js" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container" >
    <div class="header">
   	<script>
    	const header = document.querySelectorAll('.header');
    	function scrollFunc() {
        	if(pageYOffset >= 90) {
            	header[0].classList.add('on');
        	}else {
            	header[0].classList.remove('on');
        	}
    	}
    	window.addEventListener('scroll',scrollFunc);
    </script>
        <div class="logo">
        	<h1><a href="cityPlan.do?cmd=cityInfoView">LOGO</a></h1>
        </div>
        <div class="menu_wrap">
            <ul class="dep1">
                <li>
                    <span>추천플래너</span>
                </li>
                <li>
                    <span>게시판</span>
                    <ul class="dep2">
                        <li><a href="">공지사항</a></li>
                        <li><a href="">자유게시판</a></li>
                        <li><a href="">동행게시판</a></li>
                        <li><a href="">루트소개</a></li>
                        <li><a href="">루트질문</a></li>
                        <li><a href="">나눔게시판</a></li>
                        <li>&nbsp;</li>
                    </ul>
                </li>
                <li>
                    <span>내일정</span>
                    <ul class="dep2">
                    	<li><a href="cityPlan.do?cmd=myPlanClick" target="_blank">일정만들기</a></li>
                    	<li><a href="cityPlan.do?cmd=necessity">준비물챙기기</a></li>
                    	<li>&nbsp;</li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="nav">
    <%-- <% if(session어쩌고) { %> --%>
           <!-- <a href="#"><i class="fa-solid fa-user"></i></a> -->
    <%-- <% } else { %> --%>
            <div id="login_Btn"><a href="#">로그인</a></div>
    <%-- <% } %> --%>
        </div>
    </div>
</div>
</body>
</html>