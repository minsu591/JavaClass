<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div align="center">
		<div>
			<h1>공지사항 목록</h1>
		</div>
		<div>
			<form id="frm" action="" method="post">
				<select id="key" name="key">
					<option value="1" selected>전체</option>
					<option value="2">작성자</option>
					<option value="3">제목</option>
					<option value="4">내용</option>
				</select>&nbsp;
				<input id="val" name="val" type="text">&nbsp;
				<input type="button" onclick="search2()" value="검색"></input>
			</form>
		</div><br>
		<div>
			<table border="1" id="ta">
				<thead>
					<tr>
						<th width ="100">글번호</th>
						<th width ="150">작성자</th>
						<th width ="250">제목</th>
						<th width ="150">작성일자</th>
						<th width ="100">조회수</th>
						<th width ="200">첨부파일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="notice">
						<tr>
							<td align="center">${notice.id }</td>
							<td align="center">${notice.writer }</td>
							<td >${notice.title }</td>
							<td align="center">${notice.wdate }</td>
							<td align="center">${notice.hit }</td>
							<td width ="200">${notice.fileName }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div><br>
		<div>
			<button type="button" onclick ="location.href='noticeInputForm.do'">글쓰기</button>
		</div>
	</div>
<script type="text/javascript">
	function search(){
		let fields = ['id','writer','title','wdate','hit','fileName'];
		
		//기존 tr 다 없애기
		removeTr();
		let key = frm.key.value;
		let val = frm.val.value;
		let params = "key="+key+"&val="+val;
		let tbody = document.querySelector('tbody')
		fetch('ajaxSearchList.do',{
			method:'POST',
			headers:{'Content-Type':'application/x-www-form-urlencoded'},
			body : params
		}).then(response => response.json())
		.then(data => {
			console.log(data);
			data.forEach(d=>{
				let tr = document.createElement('tr');
				fields.forEach(f=>{
					let td = document.createElement('td');
					td.setAttribute('align','center');
					td.innerHTML=d[f];
					tr.appendChild(td);
				})
				tbody.appendChild(tr);
			})
		})
	}
	function removeTr(){
		let oldTr = document.querySelectorAll('tbody>tr');
		oldTr.forEach(tr => tr.remove());
	}
	
	function search2(){ //jquery library 사용
		$.ajax({
			url : "ajaxSearchList.do",
			type : "post",
			data : {"key":$("#key").val(),"val":$("#val").val()},
			dataType : "json",
			success : function(res){
					htmlConvert(res);
				},
			error : function(){
				console.log("실패")
			}
		});
	}
	function htmlConvert(res){
		$("tbody").remove();
		let tb = $("<tbody />");
		$.each(res, function(idx,n){
			let tr = $("<tr/>").append(
					$("<td/>").text(n.id),
					$("<td/>").text(n.writer),
					$("<td/>").text(n.title),
					$("<td/>").text(n.wdate),
					$("<td/>").text(n.hit),
					$("<td/>").text(n.fileName)
			);
			console.log(tr);
			tb.append(tr);
		});
		$("#ta").append(tb);
		
	}
</script>
</body>
</html>