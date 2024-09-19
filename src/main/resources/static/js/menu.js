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

    var newAmount = currentAmountNum-1;
    document.getElementById("cartAmount_"+cartItemNum).value = newAmount;
    // document.getElementById("cartItemPrice_" + cartItemNum).textContent = (currentAmountNum-1) * goodsPrice;

    fetch("/my/cartUpdate?cartItemNum=" + cartItemNum + "&amount=" + newAmount,{
        method : "PUT"
    })
    .then((response) => response.json())
    .then((data) =>{
        console.log("Item deleted:", data);
        if(data.result == "success"){
            getList(); // 카트 목록 갱신
        }
        else{
            alert("수량 수정에 실패했습니다.");
        }
    })
    .catch((error) => {
    console.error('Error deleting cart item:', error)
    });
}

function amountPlusCartOrder(stock, cartItemNum, goodsPrice){
    var currentAmount = document.getElementById("cartAmount_"+cartItemNum).value;
    var currentAmountNum = parseInt(currentAmount,10);
    if(currentAmountNum >= stock ){
        alert("현재 재고를 넘을 수 없습니다..");
        return;
    }

    var newAmount = currentAmountNum+1;
    document.getElementById("cartAmount_"+cartItemNum).value = newAmount;
    // document.getElementById("cartItemPrice_" + cartItemNum).textContent = (currentAmountNum-1) * goodsPrice;

        fetch("/my/cartUpdate?cartItemNum=" + cartItemNum + "&amount=" + newAmount,{
            method : "PUT"
        })
        .then((response) => response.json())
        .then((data) =>{
            console.log("Item deleted:", data);
            if(data.result == "success"){
                getList(); // 카트 목록 갱신
            }
            else{
                alert("수량 수정에 실패했습니다.");
            }
        })
        .catch((error) => {
        console.error('Error deleting cart item:', error)
        });
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

        fetch("/my/cartUpdate?cartItemNum=" + cartItemNum + "&amount=" + currentAmountNum,{
            method : "PUT"
        })
        .then((response) => response.json())
        .then((data) =>{
            console.log("Item deleted:", data);
            if(data.result == "success"){
                getList(); // 카트 목록 갱신
            }
            else{
                alert("수량 수정에 실패했습니다.");
            }
        })
        .catch((error) => {
        console.error('Error deleting cart item:', error)
        });
}

document.addEventListener('DOMContentLoaded', (event) => {
    getList();
});

function getList() {
    fetch("http://127.0.0.1:8080/my/cartList")
        .then((response) => response.json())
        .then((data) => {
            var htmlContent = ''; // HTML 문자열을 저장할 변수
            var totalPrice = 0;
            if(data.length == 0){
                htmlContent += "<div id='emptyCart'>장바구니가 비어 있습니다.</div>"
            }

            for (let index = 0; index < data.length; index++) {

                console.log(data[index].checked);
                if(data[index].checked === "Y"){
                    var checkboxHtml = "<input type='checkbox' id='cartChecked_" + data[index].cartItemNum + "' onchange='goCheck(" + data[index].cartItemNum + ")' checked>"
                    totalPrice += data[index].cartItemPrice;
                }
                else{
                    var checkboxHtml = "<input type='checkbox' id='cartChecked_" + data[index].cartItemNum + "' onchange='goCheck(" + data[index].cartItemNum + ")'>"
                }

                htmlContent +=
                    "<div class='orderDetail cartList' id='cartList_" + data[index].cartItemNum + "'>" +
                        checkboxHtml+
                        "<div class= 'cartImgDiv' id='cartImgDiv_" + data[index].cartItemNum + "'><img src='/upload/goods/" + data[index].storedGoodsImageName + "' alt='상품이미지' class='cartImg'></div>" +
                        "<div>" + data[index].goodsName + "</div>" +
                        "<div>" +
                            "<button type='button' class='amountButton' onclick='amountMinusCartOrder(" + data[index].stock + ", " + data[index].cartItemNum + ", " + data[index].goodsPrice + ")'> - </button>" +
                            "<input id='cartAmount_" + data[index].cartItemNum + "' class='cartAmount' name='goodsCartAmount' value=" + data[index].cartItemAmount + " onchange='amountPlusMinusCartOrder(" + data[index].stock + ", " + data[index].cartItemNum + ", " + data[index].goodsPrice + ")'>" +
                            "<button type='button' class='amountButton' onclick='amountPlusCartOrder(" + data[index].stock + ", " + data[index].cartItemNum + ", " + data[index].goodsPrice + ")'> + </button>" +
                        "</div>" +
                        "<div><span id='cartItemPrice_" + data[index].cartItemNum + "'>" + data[index].cartItemPrice + "</span><span>원</span></span></div>" +
                        "<div><a href='javascript:goDelete(" + data[index].cartItemNum + ")'><i class='bi bi-x-lg'></i></a></div>" +
                    "</div>"; // 각 루프마다 'orderDetail' div를 정확히 닫기
            }
            document.getElementById("cartListRest").innerHTML = htmlContent; // 한 번에 업데이트
            //alert(typeof(totalPrice));
            document.getElementById("finalPrice").textContent = totalPrice;
        })
        .catch((error) => console.error('Error fetching cart list:', error));
}

function goDelete(cartItemNum) { // 함수 이름 수정

    const data = {
        cartItemNum : cartItemNum
    };
    fetch("http://127.0.0.1:8080/my/cartDel", {
            method : "DELETE",
            headers : {
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(data)
        })
        .then((response) => response.json())
        .then((data) => {
            console.log("Item deleted:", data);
            if(data.result == "success"){
                getList(); // 카트 목록 갱신
            }
            else{
                alert("삭제에 실패했습니다.");
            }
        })
        .catch((error) => console.error('Error deleting cart item:', error));
}

function goCheck(cartItemNum){
    var cartCheck = document.getElementById("cartChecked_" + cartItemNum);
    alert(cartCheck.checked);
    const data = {
        cartItemNum : cartItemNum,
        cartCheck : cartCheck.checked
    };
    fetch("http://127.0.0.1:8080/my/cartCheck", {
        method : 'PUT',
        headers :{
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(data)
    })
    .then((response) => response.json())
    .then((data) => {
        if(data.result == "success"){
            getList();
        }
        else{
            alert("체크/체크해제를 하던 중 에러가 발생했습니다.");
        }
    })
    .catch((error) => console.log(error))
}