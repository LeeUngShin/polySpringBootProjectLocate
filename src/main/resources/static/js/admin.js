var currentBoardNum=null;
function memberMenuToggle(){
    memberMenu.classList.toggle("memberLowMenu");
    memberMenuDown.classList.toggle("memberMenuUpDownShow");
    memberMenuUp.classList.toggle("memberMenuUpDownShow");
}

function boardMenuToggle(){
    boardMenu.classList.toggle("boardLowMenu");
    boardMenuDown.classList.toggle("boardMenuUpDownShow");
    boardMenuUp.classList.toggle("boardMenuUpDownShow");
}

function goodsMenuToggle(){
    goodsMenu.classList.toggle("goodsLowMenu");
    goodsMenuDown.classList.toggle("goodsMenuUpDownShow");
    goodsMenuUp.classList.toggle("goodsMenuUpDownShow");
}

function addSubOption(){
    var topOption = document.getElementById("topOption").value;
    var subOption = document.getElementById("subOption");
    subOption.innerHTML = "";  // 서브옵션 선택부분 제거
    if(topOption == "빵"){
        var subOption1 = document.createElement("option");  // option 태그 생성
        subOption1.value = "식빵";
        subOption1.text = "식빵";
        subOption.add(subOption1);
        var subOption2 = document.createElement("option");
        subOption2.value = "건강빵";
        subOption2.text = "건강빵";
        subOption.add(subOption2);
        var subOption3 = document.createElement("option");
        subOption3.value = "도넛";
        subOption3.text = "도넛";
        subOption.add(subOption3);
        var subOption4 = document.createElement("option");
        subOption4.value = "파이/패스트리";
        subOption4.text = "파이/패스트리";
        subOption.add(subOption4);
    }
    else if(topOption == "케이크"){
        var subOption1 = document.createElement("option");
        subOption1.value = "생크림케이크";
        subOption1.text = "생크림케이크";
        subOption.add(subOption1);
        var subOption2 = document.createElement("option");
        subOption2.value = "티라미수";
        subOption2.text = "티라미수";
        subOption.add(subOption2);
        var subOption3 = document.createElement("option");
        subOption3.value = "치즈케이크";
        subOption3.text = "치즈케이크";
        subOption.add(subOption3);
        var subOption4 = document.createElement("option");
        subOption4.value = "조각케이크";
        subOption4.text = "조각케이크";
        subOption.add(subOption4);
        var subOption5 = document.createElement("option");
        subOption5.value = "조각케이크";
        subOption5.text = "조각케이크";
        subOption.add(subOption5);
        var subOption6 = document.createElement("option");
        subOption6.value = "선물용케이크";
        subOption6.text = "선물용케이크";
        subOption.add(subOption6);
    }
    else if(topOption == "디저트"){
        var subOption1 = document.createElement("option");
        subOption1.value = "마카롱";
        subOption1.text = "마카롱";
        subOption.add(subOption1);
        var subOption2 = document.createElement("option");
        subOption2.value = "아이스크림";
        subOption2.text = "아이스크림";
        subOption.add(subOption2);
        var subOption3 = document.createElement("option");
        subOption3.value = "초콜릿/캔디";
        subOption3.text = "초콜릿/캔디";
        subOption.add(subOption3);
        var subOption4 = document.createElement("option");
        subOption4.value = "잼";
        subOption4.text = "잼";
        subOption.add(subOption4);
        var subOption5 = document.createElement("option");
        subOption5.value = "쿠키";
        subOption5.text = "쿠키";
        subOption.add(subOption5);
    }
    else if(topOption == "음료"){
        var subOption1 = document.createElement("option");
        subOption1.value = "커피";
        subOption1.text = "커피";
        subOption.add(subOption1);
        var subOption2 = document.createElement("option");
        subOption2.value = "밀크티";
        subOption2.text = "밀크티";
        subOption.add(subOption2);
        var subOption3 = document.createElement("option");
        subOption3.value = "스무디";
        subOption3.text = "스무디";
        subOption.add(subOption3);
        var subOption4 = document.createElement("option");
        subOption4.value = "빙수";
        subOption4.text = "빙수";
        subOption.add(subOption4);
        var subOption5 = document.createElement("option");
        subOption5.value = "우유";
        subOption5.text = "우유";
        subOption.add(subOption5);
        var subOption6 = document.createElement("option");
        subOption6.value = "완제음료";
        subOption6.text = "완제음료";
        subOption.add(subOption6);
    }
}

function modifySubOption(){
    var topOption = document.getElementById("topOption").value;
    var subOption = document.getElementById("subOption");
    subOption.innerHTML = "";  // 서브옵션 선택부분 제거
    if(topOption == "빵"){

        var subOption1 = document.createElement("option");  // option 태그 생성
        subOption1.value = "식빵";
        subOption1.text = "식빵";
        subOption.add(subOption1);
        var subOption2 = document.createElement("option");
        subOption2.value = "건강빵";
        subOption2.text = "건강빵";
        subOption.add(subOption2);
        var subOption3 = document.createElement("option");
        subOption3.value = "도넛";
        subOption3.text = "도넛";
        subOption.add(subOption3);
        var subOption4 = document.createElement("option");
        subOption4.value = "파이";
        subOption4.text = "파이";
        subOption.add(subOption4);
        var subOption4 = document.createElement("option");
        subOption4.value = "카스테라";
        subOption4.text = "카스테라";
        subOption.add(subOption4);
    }
    else if(topOption == "케이크"){
        var subOption1 = document.createElement("option");
        subOption1.value = "생크림케이크";
        subOption1.text = "생크림케이크";
        subOption.add(subOption1);
        var subOption2 = document.createElement("option");
        subOption2.value = "티라미수";
        subOption2.text = "티라미수";
        subOption.add(subOption2);
        var subOption3 = document.createElement("option");
        subOption3.value = "치즈케이크";
        subOption3.text = "치즈케이크";
        subOption.add(subOption3);
        var subOption4 = document.createElement("option");
        subOption4.value = "조각케이크";
        subOption4.text = "조각케이크";
        subOption.add(subOption4);
        var subOption5 = document.createElement("option");
        subOption5.value = "캐릭터케이크";
        subOption5.text = "캐릭터케이크";
        subOption.add(subOption5);
        var subOption6 = document.createElement("option");
        subOption6.value = "선물용케이크";
        subOption6.text = "선물용케이크";
        subOption.add(subOption6);
    }
    else if(topOption == "디저트"){
        var subOption1 = document.createElement("option");
        subOption1.value = "마카롱";
        subOption1.text = "마카롱";
        subOption.add(subOption1);
        var subOption2 = document.createElement("option");
        subOption2.value = "아이스크림";
        subOption2.text = "아이스크림";
        subOption.add(subOption2);
        var subOption3 = document.createElement("option");
        subOption3.value = "초콜릿";
        subOption3.text = "초콜릿";
        subOption.add(subOption3);
        var subOption4 = document.createElement("option");
        subOption4.value = "잼";
        subOption4.text = "잼";
        subOption.add(subOption4);
        var subOption5 = document.createElement("option");
        subOption5.value = "쿠키";
        subOption5.text = "쿠키";
        subOption.add(subOption5);
    }
    else if(topOption == "음료"){
        var subOption1 = document.createElement("option");
        subOption1.value = "커피";
        subOption1.text = "커피";
        subOption.add(subOption1);
        var subOption2 = document.createElement("option");
        subOption2.value = "밀크티";
        subOption2.text = "밀크티";
        subOption.add(subOption2);
        var subOption3 = document.createElement("option");
        subOption3.value = "스무디";
        subOption3.text = "스무디";
        subOption.add(subOption3);
        var subOption4 = document.createElement("option");
        subOption4.value = "빙수";
        subOption4.text = "빙수";
        subOption.add(subOption4);
        var subOption5 = document.createElement("option");
        subOption5.value = "우유";
        subOption5.text = "우유";
        subOption.add(subOption5);
        var subOption6 = document.createElement("option");
        subOption6.value = "완제음료";
        subOption6.text = "완제음료";
        subOption.add(subOption6);
    }
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

function deleteGoods(goodsNum){
    if(confirm("정말로 삭제하시겠습니까?")){
        var goodsDeleteButton = document.getElementById("goodsDelete");
        goodsDeleteButton.submit();
    }

}