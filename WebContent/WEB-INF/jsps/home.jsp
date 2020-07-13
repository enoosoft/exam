<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<style type="text/css">
body {
	background:
		url("${pageContext.request.contextPath}/images/background.jpg");
}

.button {
	padding: 10px 15px;
	font-size: 14px;
	line-height: 100%;
	text-shadow: 0 1px rgba(0, 0, 0, 0.4);
	color: #fff;
	vertical-align: middle;
	text-align: center;
	cursor: pointer;
	font-weight: bold;
	transition: background 0.1s ease-in-out;
	-webkit-transition: background 0.1s ease-in-out;
	-moz-transition: background 0.1s ease-in-out;
	-ms-transition: background 0.1s ease-in-out;
	-o-transition: background 0.1s ease-in-out;
	text-shadow: 0 1px rgba(0, 0, 0, 0.3);
	color: #fff;
	-webkit-border-radius: 40px;
	-moz-border-radius: 40px;
	border-radius: 40px;
	font-family: 'Helvetica Neue', Helvetica, sans-serif;
}

.button, .button:hover, .button:active {
	outline: 0 none;
	text-decoration: none;
	color: #fff;
}

.username {
	background-color: #2ecc71;
	box-shadow: 0px 3px 0px 0px #239a55;
}
</style>
<script language ="javascript" >
</script>
<title>YS Exam - HOME</title>
</head>
<body>

	<div id='cssmenu'>
		<ul>
			<li class=''><a href='${pageContext.request.contextPath}/index'><span>Home</span></a></li>
			
			<c:if test='${not empty sessionScope.user}'>
				<li id="menu_login"><a href='${pageContext.request.contextPath}/logout'><span>로그아웃</span></a></li>
			</c:if>
			<c:if test='${empty sessionScope.user}'>
				<li id="menu_login"><a href='${pageContext.request.contextPath}/login'><span>로그인</span></a></li>
				<li id="menu_register"><a href='${pageContext.request.contextPath}/register'><span>회원가입</span></a></li>
			</c:if>
		</ul>
	</div>

	<c:if test='${not empty sessionScope.user}'>

		<div style="position: absolute; top: 70px; left: 1100px">
			<a href="#" class="button username">${sessionScope.user}</a>님 반갑습니다
		</div>

		<!--div style="position: absolute; top: 70px; left: 1300px">
			<a href='${pageContext.request.contextPath}/logout'>로그아웃</a>
		</div-->

	</c:if>

	<div style="position: absolute; left: 120px; top: 60px">
		<table cellpadding="0" cellspacing="100">

			<tr>
				<td><a href="takeExam?test=1단원"><img height="150"
						width="150"
						src="${pageContext.request.contextPath}/images/chapter10.png" /></a></td>
				<td><a href="takeExam?test=2단원"><img height="150"
						width="150"
						src="${pageContext.request.contextPath}/images/chapter20.png" /></a></td>
			</tr>
		</table>
	</div>


</body>
</html>
