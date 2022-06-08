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
		<div><h1>로 그 인</h1></div>
		<div>
			<form id="frm" action="login.do" method="post">
				<div>
					<table border="1">
						<tr>
							<th width="100">아이디</th>
							<td>
								<input type="email" id="studentId" name = "studentId"
								required="required" placeholder="Enter Your id...">
							</td>
						</tr>
						<tr>
							<th width="100">패스워드</th>
							<td>
								<input type="password" id="password" name = "password"
								required="required" placeholder="Enter Your password...">
							</td>
						</tr>
					</table>
				</div>
				<div>
					<input type="submit" value="로그인">&nbsp;&nbsp;
					<input type="reset" value="취소">
				</div>
			</form>
		</div>
	</div>
</body>
</html>