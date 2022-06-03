<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form.jsp</title>
</head>
<body>
	<form name="myform" action="formServlet" method="post" enctype = "multipart/form-data">
		<input type="text" name="user"><br>
		<input type="text" name="passwd"><br>
		<input type="file" name = "img"><br>
		<!-- 파일로 이미지 넘기기 -->
		<input type="hidden" name="cmd" value="search">
		<input type="hidden" name="name">
		<input type="submit" value = "전송">
		<!-- 페이지를 구분할만한 파라메터 -->
		<input type="submit" value="전송">
	</form>
	
	<script>
		let myform = document.forms.my;
		myform.onsubmit = function(){
			my.action = 'sample.html';
			my.cmd.value = "insert";
			my.user.value = "admin";
			my.passwd.value = "12345";
			my.name.value = "Hong";
			my.age.value =12;
			
		}
	</script>
	
</body>
</html>