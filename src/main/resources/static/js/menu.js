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

function cartClick(loginId, price, goodsNum, cartExist){

    if(loginId == null || loginId == ""){
        alert("회원만 장바구니에 담을 수 있습니다.!!");
        //window.location.href="http://127.0.0.1:8080/goods/detail/"+goodsNum;
        return;
    }

    if(cartExist=="Y"){
        console.log(cartExist);
        alert("이미 장바구니에 있습니다.");
        //window.location.href="http://127.0.0.1:8080/goods/detail/"+goodsNum;
        return;
    }

    var form = document.getElementById("cartInput");
    form.method="POST";
    form.action = "/my/cartInput/"+goodsNum;
    form.submit();

    //alert(amount);
    //alert(amount * price);
}

function amountMinusCartOrder(stock, cartItemNum, goodsPrice){
    var currentAmount = document.getElementById("cartAmount_"+cartItemNum).value;
    var currentAmountNum = parseInt(currentAmount,10);
    if(currentAmountNum <=1 ){
        alert("최소 1개를 선택해야 합니다.");
        return;
    }
    document.getElementById("cartAmount_"+cartItemNum).value = currentAmountNum-1;
    document.getElementById("cartItemPrice_" + cartItemNum).textContent = (currentAmountNum-1) * goodsPrice;
}

function amountPlusCartOrder(stock, cartItemNum, goodsPrice){
    var currentAmount = document.getElementById("cartAmount_"+cartItemNum).value;
    var currentAmountNum = parseInt(currentAmount,10);
    if(currentAmountNum >= stock ){
        alert("현재 재고를 넘을 수 없습니다..");
        return;
    }
    document.getElementById("cartAmount_"+cartItemNum).value = currentAmountNum+1;
    document.getElementById("cartItemPrice_" + cartItemNum).textContent = (currentAmountNum+1) * goodsPrice;
}

function amountPlusMinusCartOrder(stock, cartItemNum, goodsPrice){

    //alert(stock);
    var currentAmount = document.getElementById("cartAmount_"+cartItemNum).value;
    var currentAmountNum = parseInt(currentAmount, 10);
    if(currentAmountNum > stock){
        alert("상품 재고를 넘을 수 없습니다.");
        document.getElementById("cartAmount_"+cartItemNum).value = 1;
        return;
    }
    if(currentAmountNum<1){
        alert("최소 1개를 선택해야 합니다.");
        document.getElementById("cartAmount_"+cartItemNum).value = 1;
        return;
    }
    document.getElementById("cartItemPrice_" + cartItemNum).textContent = currentAmountNum * goodsPrice;
}