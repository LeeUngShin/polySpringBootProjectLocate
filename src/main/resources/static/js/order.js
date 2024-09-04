document.getElementById("orderMessageChoice").addEventListener("change", function() {
    var orderMessageChoice = document.getElementById("orderMessageChoice").value;

    // 콘솔에 선택된 값 출력
    console.log(orderMessageChoice);

    // 선택된 값이 "4"일 때 입력 필드 표시
    if (orderMessageChoice === "4") {
        document.getElementById("orderMessageInput").style.display = "block";
    } else {
        document.getElementById("orderMessageInput").style.display = "none";
    }
});

function accumulatedMoneyUse(){
    console.log("적립금 모두 사용");
    document.getElementById("accumulatedMoney").value = 2000;
}