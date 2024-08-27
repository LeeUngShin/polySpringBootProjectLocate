function likeYesNo(loginId, goodsNum){

    if(loginId==null || loginId==""){
        alert("회원만 상품을 찜할 수 있습니다.");
        return;
    }

    var like = document.getElementById("like");
    var likeNot = document.getElementById("likeNot");
    like.classList.toggle("likeYesNo");
    likeNot.classList.toggle("likeYesNo");

    var goodsLikeTF;
    if(like.classList.contains("likeYesNo")){
        goodsLikeTF = true;
        alert("상품을 찜했습니다.")
    }
    else if(likeNot.classList.contains("likeYesNo")){
        goodsLikeTF = false;
        alert("상품 찜을 취소했습니다.");
    }

    const payload = new FormData();
    payload.append("loginId", loginId);
    payload.append("goodsNum", goodsNum);
    payload.append("goodsLikeTF", goodsLikeTF);
    fetch("http://127.0.0.1:8080/goods/goodsLike",{
        method : "POST",
        body : payload,
    })
    .then((response) => response.json())
    .then((data) =>{
        console.log(data);
        if(data.result == "fail"){
            alert("처리 중 에러 발생");
        }
     })
    .catch(error => {
     console.error('Error:', error);
    });
}


function menuHover(goodsNum){
    var menuHover = document.getElementById("menuHover_"+goodsNum);
    menuHover.style.visibility = "visible";
}

function menuHoverOut(goodsNum){
    var menuHover = document.getElementById("menuHover_"+goodsNum);
    menuHover.style.visibility = "hidden";
}