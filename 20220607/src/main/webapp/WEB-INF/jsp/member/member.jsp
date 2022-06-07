<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 목록</h1>
	<!-- 집합으로 넘어오니까 한 줄을 var, s로 읽겠다. -->
	<c:forEach items="${students}" var="s">
		${s.studentId} : ${s.name } : ${s.address}<br>
		
	</c:forEach>
</body>
</html>