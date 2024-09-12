<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<link rel="stylesheet" href = "/css/project01.css">
<link rel="stylesheet" href = "/css/my.css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
</head>
<body onload = "getList()">
<div id="container">
    <%@include file = "../header.jsp" %>
    <%@include file = "../nav.jsp" %>
    <hr style="margin-bottom : 45px;">
    <section id="myMenuSection">
      <article>
          <div class="orderDiv">
            <div id="orderDetailDiv">
              <h3 class="fontBold">장바구니</h3>
              <hr>
              <div id="cartListRest">

              </div>
              <div>
                  <h2>결제정보</h2>
                  <hr>
                  <div>상품금액 <span id="goodsPrice"></span></div>
                  <div>배송비 <span id="deliveryPrice"></span></div>
                  <hr>
                  <div>총 결제금액 <span id="finalPrice"></span></div>
                  <button>구매하기</button>
              <div>
            </div>
          </div>
      </article>
    </section>

    <%@ include file = "../footer.jsp" %>
</div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <script src="/js/my.js"></script>
  <script src="/js/menu.js"></script>
 <script>
    var role = '<%=(String)session.getAttribute("role")%>';
    //alert(role);
    if((role != "ROLE_ADMIN" && role != "ROLE_USER") || role=='null' || role==""){
        alert("회원만 접근 가능합니다.")
        window.location.href = "/home";
    }
  </script>
  <script>
    function getList() {
        fetch("http://127.0.0.1:8080/my/cartList")
            .then((response) => response.json())
            .then((data) => {
                let htmlContent = ''; // HTML 문자열을 저장할 변수

for (let index = 0; index < data.length; index++) {
    htmlContent +=
        "<div class='orderDetail cartList' id='cartList_" + data[index].cartItemNum + "'>" +
            "<input type='checkbox' checked>" +
            "<div class= 'cartImgDiv' id='cartImgDiv_" + data[index].cartItemNum + "'><img src='/upload/goods/" + data[index].storedGoodsImageName + "' alt='상품이미지' class='cartImg'></div>" +
            "<div>" + data[index].goodsName + "</div>" +
            "<div>" +
                "<button type='button' class='amountButton' onclick='amountMinusCartOrder(" + data[index].stock + ", " + data[index].cartItemNum + ", " + data[index].goodsPrice + ")'> - </button>" +
                "<input id='cartAmount_" + data[index].cartItemNum + "' class='cartAmount' name='goodsCartAmount' value='1' onchange='amountPlusMinusCartOrder(" + data[index].stock + ", " + data[index].cartItemNum + ", " + data[index].goodsPrice + ")'>" +
                "<button type='button' class='amountButton' onclick='amountPlusCartOrder(" + data[index].stock + ", " + data[index].cartItemNum + ", " + data[index].goodsPrice + ")'> + </button>" +
            "</div>" +
            "<div><span id='cartItemPrice_" + data[index].cartItemNum + "'>" + data[index].goodsPrice + "</span><span>원</span></span></div>" +
            "<div><a href='javascript:goDelete(" + data[index].cartItemNum + ")'><i class='bi bi-x-lg'></i></a></div>" +
        "</div>"; // 각 루프마다 'orderDetail' div를 정확히 닫기
}

                document.getElementById("cartListRest").innerHTML = htmlContent; // 한 번에 업데이트
            })
            .catch((error) => console.error('Error fetching cart list:', error));
    }

    function goDelete(cartItemNum) { // 함수 이름 수정
        fetch("http://127.0.0.1:8080/my/cartDel?cartItemNum=" + cartItemNum)
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

  </script>

</body>
</html>