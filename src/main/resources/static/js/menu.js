function likeYesNo(loginId, goodsNum, goodsLikeTF){

    if(loginId==null || loginId==""){
        alert("회원만 상품을 찜할 수 있습니다.");
        return;
    }

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
        if(data=="true"){
            like.classList.add("likeYesNo");
            likeNot.classList.remove("likeYesNo");
        }
        else if(data=="false"){
            likeNot.classList.add("likeYesNo");
            like.classList.remove("likeYesNo");
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

function amountPlusOrder(stock){
    //alert(stock);
    var currentAmount = document.getElementById("amount").value;
    var currentAmountNum = parseInt(currentAmount, 10);
    if(currentAmountNum >= stock){
        alert("상품 재고를 넘을 수 없습니다.");
        return;
    }
    document.getElementById("amount").value = currentAmountNum+1;

}

function amountMinusOrder(stock){

    alert(stock);
    var currentAmount = document.getElementById("amount").value;
    var currentAmountNum = parseInt(currentAmount, 10);
    if(currentAmountNum<=1){
        alert("최소 1개를 선택해야 합니다.");
        return;
    }
    document.getElementById("amount").value = currentAmountNum-1;
}

function amountPlusMinusOrder(stock){

    //alert(stock);
    var currentAmount = document.getElementById("amount").value;
    var currentAmountNum = parseInt(currentAmount, 10);
    if(currentAmountNum >= stock){
        alert("상품 재고를 넘을 수 없습니다.");
        document.getElementById("amount").value = 1;
        return;
    }
    if(currentAmountNum<=1){
        alert("최소 1개를 선택해야 합니다.");
        document.getElementById("amount").value = 1;
        return;
    }
}

function amountMinusCart(stock){
    var currentAmount = document.getElementById("cartAmount").value;
    var currentAmountNum = parseInt(currentAmount,10);
    if(currentAmountNum <=1 ){
        alert("최소 1개를 선택해야 합니다.");
        return;
    }
    document.getElementById("cartAmount").value = currentAmountNum-1;
}

function amountPlusCart(stock){
    var currentAmount = document.getElementById("cartAmount").value;
    var currentAmountNum = parseInt(currentAmount,10);
    if(currentAmountNum >= stock ){
        alert("현재 재고를 넘을 수 없습니다..");
        return;
    }
    document.getElementById("cartAmount").value = currentAmountNum+1;
}

function amountPlusMinusCart(stock){

    //alert(stock);
    var currentAmount = document.getElementById("cartAmount").value;
    var currentAmountNum = parseInt(currentAmount, 10);
    if(currentAmountNum >= stock){
        alert("상품 재고를 넘을 수 없습니다.");
        document.getElementById("cartAmount").value = 1;
        return;
    }
    if(currentAmountNum<=1){
        alert("최소 1개를 선택해야 합니다.");
        document.getElementById("cartAmount").value = 1;
        return;
    }
}

function cartClick(loginId, price){
    alert(loginId);
    alert(price);
    if(loginId == null || loginId == ""){
        alert("회원만 장바구니에 담을 수 있습니다.");
    }
    var amount = document.getElementById("cartAmount").value;
    amount = parseInt(amount, 10);
    document.getElementById("cartPrice").value= amount * price;
    alert(amount);
    alert(amount * price);
}