document.addEventListener('DOMContentLoaded',function(){
    makeSpan();
    document.

})


function makeSpan(){
    let str = 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ad qui laborum amet excepturi facilis. Aut excepturi itaque omnis, consequuntur voluptatibus perspiciatis odit? Sed dolores incidunt odio! Quisquam reprehenderit molestiae magni.';
    let strList = str.split(' ');
    let div = document.getElementById('show');
    console.log(div);
    strList.forEach((val,idx) => {
        let span = document.createElement('span');

        console.log(val, idx);
        span.innerHTML=val;

        div.append(span);
        if(idx%5==0 && idx != 0){
            div.append(document.createElement('br'));
        }

    })
}
