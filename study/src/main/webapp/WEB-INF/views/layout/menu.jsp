<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/menu.css">
</head>
<body>
	<div align="center">
		<div>
			<br />
		</div>
		<div>
			<!-- 메뉴부분 -->
			<ul>
				<li><a class="active" href="home.do">Home</a></li>
				<c:choose>
					<c:when test="${empty studentId }">
						<li><a href="">Join</a></li>
						<li><a href="loginForm.do">Login</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="logout.do">LogOut</a></li>					
					</c:otherwise>
				</c:choose>
				<li><a href="noticeSelectList.do">Notice</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Product</a></li>
				<li><a href="#">Service</a></li>
				<c:if test="${author eq 'admin' }">
					<li><a href="#about">Members</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>