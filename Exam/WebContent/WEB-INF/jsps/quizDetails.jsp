<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<title>YS Exam - 시험안내</title>
<style type="text/css">
body {
	background: url("${pageContext.request.contextPath}/images/background.jpg");
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
<br><br><br>
<h2 align="center">○ ${sessionScope.exam} 문제 안내</h2>

<div style="position:absolute;left:500px;top:170px">
<ul style="list-style-type:disc">
 <li>총 ${sessionScope.totalNumberOfQuizQuestions} 문제가 출제됩니다</li>
 <li>문제당 ${sessionScope.quizDuration} 분의 시간이 주어집니다</li>
 <li>시험 도중 중단할 수 있습니다</li>
 <li>최종 제출전 뒤로가기를 통해 답안을 수정할 수 있습니다</li>
 <li>시험 잘 보세요</li>
</ul>  
<br><br><br>
</div>

<div  style="position:absolute;left:600px;top:350px">
<button onclick="location.href='${pageContext.request.contextPath}/exam'">시작</button>
</div>


</body>
</html>