<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style type="text/css">
body {
	background: url("${pageContext.request.contextPath}/images/background.jpg");
}

</style>
<title>YS Exam - 시험 결과</title>
</head>
<body>

<div style="position:absolute;left:500px;top:70px">
<h3 align="center">시험 결과</h3>
<table border=1>
        <tr>
            <td class="head">
                과목(단원) :
            </td>
            <td>
                <span id="lblSubject">${sessionScope.exam}</span></td>
        </tr>
        <tr>
            <td class="head">
                시작시간 :
            </td>
            <td >
                <span id="lblStime">${sessionScope.started}</span></td>
        </tr>
        
              
                <tr>
            <td class="head">
               문제 수 :
            </td>
            <td>
                <span id="lblNquestions">${sessionScope.totalNumberOfQuizQuestions}</span></td>
        </tr>
        
                <tr>
            <td class="head">
                정답 수 :
            </td>
            <td>
                <span id="lblNcans">${requestScope.result}</span></td>
        </tr>
        
    </table>

<c:if test="${requestScope.result >= 5}">
   <h3 align="center"><font color="blue">합격</font></h3>
</c:if>
<c:if test="${requestScope.result < 5}">
   <h3 align="center"><font color="red">실패</font></h3>
</c:if>
<h3 align="center"><a href='${pageContext.request.contextPath}/exam/review'>정답 보기</a></h3><br>
<h3 align="center"><a href='${pageContext.request.contextPath}/index'>다른 시험 보기 ${sessionScope.currentQuestion}</a></h3><br>
<h3 align="center"><a href='${pageContext.request.contextPath}/index'>HOME으로 돌아가기</a></h3>
</div>

</body>
</html>