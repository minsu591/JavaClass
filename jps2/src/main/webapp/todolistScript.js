document.addEventListener('DOMContentLoaded',function(){
    //전체 목록 조회
    fetch('todo')
    .then(result => result.json())
    .then(res => {
        res.forEach(elem => {
            let myul = document.getElementById('myUL');
            let no = document.createElement('input');
            no.setAttribute('type','hidden');
            no.setAttribute('name','no');

            let li = makeLi(elem);
            no.value = elem.no;
            li.appendChild(no);

            let span = createBtn();

            li.appendChild(span);
            myul.append(li);
        })
    })
    

    function createBtn(){
        //create a 'close' button and append it to each list item
        var span = document.createElement("SPAN");
        var txt = document.createTextNode("\u00D7");
        span.className = "close";
        //click on a close button to hide the current list item (delete)
        span.onclick = function(e) {
            var div = this.parentElement;
            div.style.display = "none";
            let param = 'cmd=delete&no='+div.children[0].value;

            //db연결
            fetch('todo',{
                method:'post',
                headers:{'Content-type':'application/x-www-form-urlencoded'},
                body : param
            })
        }
        span.appendChild(txt);
        return span;
    }

    //li 생성
    function makeLi(elem){
        let li = document.createElement('li');
        li.innerText=elem.title;
        if(elem.checked==1){
            li.setAttribute('class','checked');
        }
        return li;
    }
 

    // Add a "checked" symbol when clicking on a list item
    //check하는거
    var list = document.querySelector('ul');
    list.addEventListener('click', function(ev) {
      if (ev.target.tagName === 'LI') {
        ev.target.classList.toggle('checked');
      }
      let param = 'cmd=check&no='+ev.target.children[0].value;
      fetch('todo',{
        method:'post',
        headers:{'Content-type':'application/x-www-form-urlencoded'},
        body : param
    })

    }, false);

    let insertBtn = document.getElementsByClassName('addBtn')[0];
    insertBtn.addEventListener('click',newElement);


    // Create a new list item when clicking on the "Add" button
    function newElement() {
      var li = document.createElement("li");
      var inputValue = document.getElementById("myInput").value;
      var t = document.createTextNode(inputValue);
      li.appendChild(t);
      if (inputValue === '') {
        alert("You must write something!");
      } else {
        let no = document.createElement('input');
        no.setAttribute('type','hidden');
        no.setAttribute('name','no');

        //삽입 fetch
        fetch('todo',{
            method:'post',
            headers:{'Content-type':'application/x-www-form-urlencoded'},
            body : 'cmd=insert&title='+inputValue
        }).then(result => result.json())
        .then(re => {
            no.setAttribute('value',re.no);
            li.appendChild(no);
            let span = createBtn();
            li.appendChild(span);
            document.getElementById("myUL").appendChild(li);
        })
      }

      document.getElementById("myInput").value = "";
      
    
      
      for (i = 0; i < close.length; i++) {
        close[i].onclick = function() {
          var div = this.parentElement;
          div.style.display = "none";
        }
      }
    }
})

