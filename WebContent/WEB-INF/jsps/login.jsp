<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   
    <title>
        YS Exam - 로그인
    </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style type="text/css">
body {
	background: url("${pageContext.request.contextPath}/images/background.jpg");
}
.user-icon {
	top:153px; /* Positioning fix for slide-in, got lazy to think up of simpler method. */
	background: rgba(65,72,72,0.75) url('${pageContext.request.contextPath}/images/user-icon.png') no-repeat center;	
}

.pass-icon {
	top:201px;
	background: rgba(65,72,72,0.75) url('${pageContext.request.contextPath}/images/pass-icon.png') no-repeat center;
}


</style>
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

<div style="position:absolute;left:500px;top:75px">
처음 오신 분은 <a href='${pageContext.request.contextPath}/register'>회원가입</a> 버튼을 누르세요
<br/>
<h3 align="center">${errorMessage}</h3>
</div>
<div id="wrapper">

	<form name="login-form" class="login-form" action="checkLogin" method="post">
	
		<div class="header">
		<h1>로그인 </h1>
		<span></span>
		</div>
	
		<div class="content">
		<input name="username" type="text" class="input username" placeholder="Username" />
		<input name="password" type="password" class="input password" placeholder="Password" />
		</div>

		<div class="footer">
		<input type="submit" name="submit" value="로그인" class="button" />
		
		</div>
	
	</form>

</div>
<div class="gradient"></div>


</body>
</html>
