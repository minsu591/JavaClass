<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<!-- Page Heading -->
		<h1 class="h3 mb-2 text-gray-800">공지사항</h1>
		<p class="mb-4">
			DataTables is a third party plugin that is used to generate the demo
			table below. For more information about DataTables, please visit the
			<a target="_blank" href="https://datatables.net">official
				DataTables documentation</a>.
		</p>

		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">공지사항 목록</h6>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable" width="100%"
						cellspacing="0">
						<thead>
							<tr>
								<th>글 번호</th>
								<th>작성자</th>
								<th>제목</th>
								<th>작성 일자</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${notices}" var="n">
								<tr>
									<td>${n.id }</td>
									<td>${n.writer }</td>
									<td>${n.title}</td>
									<td>${n.wdate}</td>
									<td>${n.hit}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div></div>
		<br>
		<div>
			<form id="frm" action="noticeSelectOne.do" method="post">
				<input type="hidden" id="id" name="id">
			</form>
		</div>
		<div>
			<button type="button" onclick="location.href='home.do'">홈가기</button>
		</div>
	</div>

	<!-- Page level plugins -->
	<script src="vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/datatables-demo.js"></script>

	<script type="text/javascript">
		function noticeSelectOne(id) {
			//location.href="noticeSelectOne.do?id="+id;
			frm.id.value = id;
			frm.submit();
		}
	</script>
</body>
</html>