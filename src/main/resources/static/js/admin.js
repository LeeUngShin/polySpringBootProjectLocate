var currentBoardNum=null;
function memberMenuToggle(){
    memberMenu.classList.toggle("memberLowMenu");
}

function boardMenuToggle(){
    boardMenu.classList.toggle("boardLowMenu");
}

function goodsMenuToggle(){
    goodsMenu.classList.toggle("goodsLowMenu");
}

function boardDetail(boardNum){
    currentBoardNum = boardNum;
    fetch("http://127.0.01:8080/admin/boardDetail/" + boardNum)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            document.getElementById("boardDetail").innerHTML =
                "<tr>" +
                "    <th scope='col'> 글번호</th>" +
                "    <td scope='col'>" + data.num + "</td>" +
                "    <th scope='col'>글작성자</th>" +
                "    <td scope='col'>" + data.writer + "</td>" +
                "    <th scope='col'>등록일</th>" +
                "    <td scope='col'>" + data.regTime + "</td>" +
                "</tr>" +
                "<tr>" +
                "    <th>제목</th>" +
                "    <td colspan='6'>" + data.title + "</td>" +
                "</tr>" +
                "<tr>" +
                "    <th>내용</th>" +
                "    <td colspan='6'>" +
                "        <div><img src='/upload/board/" + data.storedFileName +"' alt=''></div>" +
                "        <div id='boardContent'>" + data.content + "</div>" +
                "    </td>" +
                "</tr>"
        })
        .catch((error)=>{
            console.error(error);
            alert("삭제 중 오류 발생");
        });
}

function deleteBoard(boardNum){
    alert(boardNum);
    if(confirm("정말로 삭제하시겠습니까?")){
    var deleteButtonForm = document.getElementById("deleteButtonForm");
    deleteButtonForm.innerHTML +=
        "<input type='hidden' name='boardNum' value='" + currentBoardNum +
        "'>"
    deleteButtonForm.submit();
    }
}