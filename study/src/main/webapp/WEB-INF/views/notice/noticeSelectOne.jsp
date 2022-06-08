<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div><h1>공지사항 상세보기</h1></div>
		<p>${notice.id} : ${notice.writer} : ${notice.wdate} : ${notice.hit }</p>
		<p>${notice.title }</p>
		<p>${notice.subject }</p>
	</div>
</body>
</html>