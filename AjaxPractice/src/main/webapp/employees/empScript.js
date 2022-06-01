document.addEventListener('DOMContentLoaded',function(){
    let myform = document.forms.frm;

    //처음 페이지 시작할 때 리스트 출력
    let xhtp = new XMLHttpRequest();
    xhtp.open('get','../ajaxPractice.do?job=json');
    xhtp.send();
    xhtp.onload = function(){
        console.log(xhtp.responseText);
        let data = JSON.parse(xhtp.responseText); //Json으로 들어온걸 배열로 변경
        let bodyList = document.getElementById('showList');
        data.forEach(emp => {
            bodyList.append(makeTr(emp)); //들어온 요소 tr로 변경 후 body에 추가
        })
    }


    //form의 submit 버튼이 눌렸을 때 (저장 버튼이 눌렸을 때)
    myform.onsubmit = function(e){ //on으로 시작하는건 이벤트
        e.preventDefault(); //기존에 새페이지로 이동하는걸 막고
        ajaxPost(addCallBack); //이걸 실행해라
    }

    //수정 버튼이 눌러졌을 때
    document.getElementById('modify').addEventListener('click',function(){
        myform.cmd.value = 'update';
        ajaxPost(modifyCallBack);
    })

    function ajaxPost(callBackFnc){
        let fname = myform.fname.value;
        let lname = myform.lname.value;
        let email = myform.email.value;
        let job = myform.job.value;
        let hdate = myform.hdate.value;
        let cmd = myform.cmd.value;
        let empId = myform.empId.value;
        //form으로부터 정보를 가져와서 파라미터로 저장
        let param = `cmd=${cmd}&fname=${fname}&lname=${lname}&email=${email}&job=${job}&hdate=${hdate}&empId=${empId}`;
        
        //파라미터를 Servlet으로 보내는 기능
        let xhtp = new XMLHttpRequest();
        xhtp.open("post",'../ajaxPractice.do'); //post타입의 ajaxPractice.do로
        xhtp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        xhtp.send(param); //한 줄로 정리한 param을 Servlet으로 전송

        //xhtp가 load되면 (이벤트)
        xhtp.onload = callBackFnc;
    }

    function addCallBack(e){
        //응답된 텍스트를 JSON형태로 바꿔서 전달 후 추가
        console.log(e.target.responseText);
        let data = JSON.parse(e.target.responseText);
        document.getElementById('showList').append(makeTr(data));
    }

    function modifyCallBack(e){
        let data = JSON.parse(e.target.responseText);
        let oldTr = document.getElementById('key_'+data.employeeId);
        let newTr = makeTr(data);
        oldTr.parentNode.replaceChild(newTr,oldTr);
    }



    let fields = ['employeeId','firstName','lastName','email','hireDate','jobId'];
    function makeTr(emp){
        let tr = document.createElement('tr');
        tr.setAttribute('id','key_'+emp.employeeId);
        
        tr.addEventListener('click',trClick); //tr클릭하면 클릭함수

        fields.forEach(field => {
            let td = document.createElement('td');
            td.innerText = emp[field];
            tr.append(td);
        })
        return tr;
    }
    function trClick(){
        myform.empId.value = this.children[0].innerText;
        myform.fname.value=this.children[1].innerText;
        myform.lname.value=this.children[2].innerText;
        myform.email.value= this.children[3].innerText;
        myform.hdate.value=this.children[4].innerText.substring(0,10);
        myform.job.value=this.children[5].innerText;
    }
});