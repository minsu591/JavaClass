<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div align='center'>
			<div>
				<form id="frm" action="noticeForm.do" method="post">
					<select id ="key" name='key'>
						<option value="1" selected>전체</option>
						<option value="2">작성자</option>
						<option value="3">제목</option>
						<option value="4">내용</option>
					</select>&nbsp;&nbsp;
					<input type="text" id="val" name="val" placeholder="검색 내용을 입력해주세요">
					&nbsp;&nbsp;
					<button type="submit">검색</button>
				</form>
				
			</div>
		</div>
	</section>
</body>
</html>