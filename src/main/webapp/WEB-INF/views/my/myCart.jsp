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
<body>
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
              <div>
                  <c:forEach items="${cartItemDtoList}" var="cartItem">
                    <div class="orderDetail" id="cartList">
                      <input type="checkbox">
                      <div> <img src="/upload/goods/${cartItem.storedGoodsImageName}" alt="상품이미지"></div>
                      <div>${cartItem.goodsName}</div>
                      <div>
                          <button type="button" class="amountButton" onclick="amountMinusCartOrder(${cartItem.stock}, ${cartItem.cartItemNum}, ${cartItem.goodsPrice})" >-</button>
                          <input id="cartAmount_${cartItem.cartItemNum}" class="cartAmount" name="goodsCartAmount" value="${cartItem.goodsCartAmount}" onchange="amountPlusMinusCartOrder(${cartItem.stock}, ${cartItem.cartItemNum}, ${cartItem.goodsPrice})"></input>
                          <button type="button" class="amountButton" onclick="amountPlusCartOrder(${cartItem.stock}, ${cartItem.cartItemNum}, ${cartItem.goodsPrice})">+</button>
                          <input type="hidden" name="price" id="cartPrice">
                      </div>
                      <div id="cartItemPrice_${cartItem.cartItemNum}"></div><span>원</span>
                      <div><a href="#"><i class="bi bi-x-lg"></i></a></div>
                      ${cartItem.stock}
                    </div>
                      <script>
                        var cartItemNum = "${cartItem.cartItemNum}";
                        var amount = document.getElementById("cartAmount_" + cartItemNum).value;
                        amount = parseInt(amount, 10);
                        var stock = parseInt("${cartItem.stock}", 10);
                        if(amount > stock){
                           alert("${cartItem.goodsName} 상품의 선택 수량이 재고보다 많습니다. 다시 수량을 입력하세요");
                           document.getElementById("cartAmount_" + cartItemNum).value = 1;
                           amount = document.getElementById("cartAmount_" + cartItemNum).value;
                        }
                        var goodsPrice = parseInt(${cartItem.goodsPrice}, 10);

                        document.getElementById("cartItemPrice_" + cartItemNum).textContent = amount * goodsPrice;
                      </script>
                  </c:forEach>
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
      var cartItemNum = "${cartItem.cartItemNum}";
    var amount = document.getElementById("cartAmount_" + cartItemNum).value;
    amount = parseInt(amount, 10);
    var goodsPrice = "${cartItem.goodsPrice}";
    goodsPrice = parseInt(goodsPrice, 10);

    document.getElementById("cartItemPrice_"+cartItemNum).textContent = amount * goodsPrice;


  // 총 상품 금액 계산 함수
  function calculateTotalPrice() {
    var total = 0;
    <c:forEach items="${cartItemDtoList}" var="cartItem">
      var cartItemNum = "${cartItem.cartItemNum}";
      var amount = parseInt(document.getElementById("cartAmount_" + cartItemNum).value, 10);
      var goodsPrice = parseInt(${cartItem.goodsPrice}, 10);
      total += amount * goodsPrice;
    </c:forEach>
    document.getElementById("goodsPrice").textContent = total;
    //document.getElementById("finalPrice").textContent = total + parseInt(document.getElementById("deliveryPrice").textContent);
  }

  // 페이지 로드 시 총 금액 계산
  window.onload = function() {
    calculateTotalPrice();
  }
  </script>
</body>
</html>