<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#show {
	width: 500px;
	height: 300px;
	border: 1px solid blue;
}
</style>
</head>
<body>
	<%
		String userName = request.getParameter("user"); //이 값을 고정시키겠다
	%>
	<div id="show">
		<div class="row">user> test</div>
	</div>
	<input type="text" name="content">
	<input type="button" value="전송"><br>
	Writer : <input type="text" name="writer" value="<%=userName%>님">

	<script>
		//3초마다 계속 실행하겠다
		//let lastMsg = -1;
		setInterval(e => { //가지고 온 데이터를 3초마다 계속 화면에 그려주는
			fetch('message')
			.then(result => result.json())
			.then(resolve => {
				let oldDivs = document.querySelectorAll('#show div');
				oldDivs.forEach(elem => elem.remove());
				
				//일정 갯수만 나오도록
				resolve = resolve.filter((val,idx,arr)=> (arr.length-idx) <15);
				
				/*let fitAry = resolve.filter(elem =>{
					return elem.msgId > lastMsg - 10;
				})*/
				
				resolve.forEach(val => {
					let row = createRow(val);
					document.getElementById('show').append(row);
				})
			})
			.catch(error => console.log(error));
		},3000)
		
		function createRow(resolve){ //메세지 하나를 가져오면 넣어주는
			let div = document.createElement('div');
			div.setAttribute('class','row');
			div.innerText = resolve.writer +'>'+resolve.content;
			//let txt =document.createTextNode(message.writer+'>'+message.content)
			//div.append(txt);
			return div;
		}
		
		document.querySelector('input[type="button"]').addEventListener('click',changeCall);
        document.querySelector('input[name="content"]').addEventListener('change',changeCall);
		function changeCall(e){
			let content = document.querySelector('input[name="content"]').value;
			let writer = document.querySelector('input[name="writer"]').value;
			let param = 'content='+content+'&writer='+writer;
            fetch('message',{
            	method:'post',
            	headers:{'Content-type':'application/x-www-form-urlencoded'},
            	body:param
            }).then(result => {
            	if(e.target.name=="content"){
	            	e.target.value='';
            	}else if(e.target.type=="button"){
            		e.stopPropagation();
            		document.querySelector('input[name="content"]').value='';
            		//undefined생기는데 왤까
            	}
            }).catch(err => console.log(err));
            
			
		}
		
		
	</script>
</body>
</html>