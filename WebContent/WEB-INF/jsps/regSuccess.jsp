<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style type="text/css">
body {
	background: url("${pageContext.request.contextPath}/images/background.jpg");
}


</style>
   <script src="script.js"></script>
   <title>YS Exam - 회원가입성공</title>
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

<div style="position:absolute;left:350px;top:200px">
<h3>축하합니다. ${requestScope.newUser} 사용자 계정 생성 성공했습니다. 시험을 보시려면 <a href="${pageContext.request.contextPath}/login">로그인 </a> 하세요</h3>
</div>


</body>
</html>
