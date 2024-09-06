document.getElementById("orderMessageChoice").addEventListener("change", function() {
    var orderMessageChoice = document.getElementById("orderMessageChoice").value;

    // 콘솔에 선택된 값 출력
    console.log(orderMessageChoice);

    // 선택된 값이 "직접입력"일 때 입력 필드 표시
    if (orderMessageChoice === "직접 입력") {
        console.log("여기1");
        document.getElementById("orderMessageInput").style.display = "block";
    } else {
        console.log("여기2");
        document.getElementById("orderMessageInput").style.display = "none";
    }
});

function accumulatedMoneyUse(){
    console.log("적립금 모두 사용");
    document.getElementById("accumulatedMoney").value = 2000;
}

    function openAddressChange() {
        // 새 창에서 주소 변경 페이지 열기
        window.open('address-change.html', '_blank', 'width=600,height=400');
    }
function addrCh(){
      var post = document.getElementById("sample6_postcode").value;
      var addr = document.getElementById("sample6_address").value;
      var addrDetail = document.getElementById("sample6_detailAddress").value;
      document.getElementById("post").textContent = post;
      document.getElementById("addr").textContent = addr;
      document.getElementById("addrDetail").textContent = addrDetail;

    }

    function accumulatedMoneyUse(accumulatedMoney ,finalPrice){
      var goodsOriginalPrice = document.getElementById("goodsOriginalPrice").textContent;
      var goodsOriginalPriceNum = parseInt(goodsOriginalPrice, 10);
      var disCountPrice = document.getElementById("useAccumulateMoney").value;
      var disCountPriceNum = parseInt(disCountPrice, 10);
      var accumulatedMoneyNum = parseInt(accumulatedMoney, 10);
      console.log(goodsOriginalPrice+10);
      console.log(goodsOriginalPriceNum+10);
      console.log(disCountPrice+10);
      console.log(disCountPriceNum+10);
      console.log((goodsOriginalPriceNum - disCountPriceNum)+10);
      if(disCountPrice > accumulatedMoney || disCountPriceNum > accumulatedMoney){
        alert("현재 적립금보다 높은 금액은 불가능합니다.");
        document.getElementById("useAccumulateMoney").value = "0";
        return;
      }
      if(disCountPrice > finalPrice){
        alert("결제 금액보다 높은 금액은 불가능합니다.");
        document.getElementById("useAccumulateMoney").value = "0";
        return;
      }
      document.getElementById("disCountPrice").textContent = disCountPrice;
      document.getElementById("finalPrice").textContent = goodsOriginalPriceNum-disCountPriceNum;
      var finalAccumulateMoney = Math.round((goodsOriginalPriceNum-disCountPriceNum)/100);
      document.getElementById("accumulateMoney").textContent = finalAccumulateMoney;
    }

function submitOrderInfo(goodsNum, loginId){
    var post = document.getElementById("post").innerText;
    var addr = document.getElementById("addr").innerText;
    var addrDetail = document.getElementById("addrDetail").innerText;
    var orderMessageChoice = document.getElementById("orderMessageChoice").value;
    if(orderMessageChoice=="직접입력"){
        var orderMessageChoice = document.getElementById("orderMessageInput").value;
    }
    var useAccumulateMoney = document.getElementById("useAccumulateMoney").value;
    var orderPaymentMethodRadio = document.querySelector('input[name="option"]:checked');
        if (orderPaymentMethodRadio) {
            var orderPaymentMethod = orderPaymentMethodRadio.value;
            console.log(orderPaymentMethodRadio);
            console.log(orderMessageChoice);
            //alert("선택된 값: " + orderPaymentMethod);
        } else {
            console.log(orderPaymentMethodRadio);
            //alert("아직 선택된 항목이 없습니다.");
        }
    var amount = document.getElementById("amount").innerText;
    var deliveryPrice = document.getElementById("deliveryPrice").innerText;
    var finalPrice = document.getElementById("finalPrice").innerText;
    var accumulateMoney = document.getElementById("accumulateMoney").innerText;
    alert("우편주소 : " + post);
    alert("주소 : " + addr);
    alert("상세주소 : " + addrDetail);
    alert("배송메세지 : " + orderMessageChoice);
    alert("사용적립금 : " + useAccumulateMoney);
    alert("결제방법 : " + orderPaymentMethod);
    alert("구매수량 : " + amount);
    alert("배송비 : " + deliveryPrice);
    alert("결제가격 : " + finalPrice);
    alert("적립금 : " + accumulateMoney);
}

