<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<link rel="stylesheet" href = "/css/project01.css">
<link rel="stylesheet" href = "/css/order.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
<div id="container">
    <%@include file = "../header.jsp" %>
    <%@include file = "../nav.jsp" %>
    <hr>
    ${memberDto}
    <section class="section">
      <article>
        <div>
          <div class = "row">
            <div class="col-12">
              <h2>주문결제</h2>
            <div>
          </div>
        </div>
      </article>
      <article>
      <div class="orderFormBack">
        <div class="orderPaymentAll">
            <div class="orderPaymentLeft">
                <div class="orderPayment">
                    <div class="orderPayment">
                        <div id="addressModifyButton">
                            <p style="margin-bottom : 0px"><i class="bi bi-pin-map-fill"></i>
                            배송지</p>
                            <button type="button" class="btn btn-outline-dark" style="">배송지변경</button>
                        </div>
                        <hr>
                        <p>이웅신 010-0000-0000</p>
                        <p>인천광역시 미추홀구 소성로 211 1차 2동 909호</p>
                        <div class="mb-3">
                          <label for="exampleFormControlInput1" class="form-label">배송메세지</label>
                            <p><select name="color" class="form-control" id="orderMessageChoice">sd
                                <option value="0">배송시 요청사항을 선택해 주세요.</option>
                                <option value="1">부재 시 경비실에 맡겨주세요.</option>
                                <option value="2">배송 전 연락바랍니다</option>
                                <option value="3">부재 시 문 앞에 놓아주세요.</option>
                                <option value="4">직접 입력</option>
                            </select></p>
                          <input type="text" class="form-control" id="orderMessageInput" placeholder="직접 입력" style="display : none">
                        </div>
                    </div>
                </div>
                <div class="orderPayment">
                    <div class="orderPayment">
                        <p>적립금 사용</p>
                        <hr>
                        <p>나의 적립금 2000원</p>
                        <p>
                            <input type="text" class="form-control" id="accumulatedMoney" style="width : 50%; display : inline-block">
                            <button type="button" class="btn btn-outline-dark" onclick="accumulatedMoneyUse()">모두 사용</button>
                        </p>
                    </div>
                </div>
                <div class="orderPayment">
                    <div class="orderPayment">
                        <p>결제 수단</p>
                        <hr>
                    <input type="radio" id="option1" name="option" value="1">
                    <label for="option1">신용카드</label></br>
                    <input type="radio" id="option2" name="option" value="2">
                    <label for="option2">계좌자동이체</label></br>
                    <input type="radio" id="option3" name="option" value="3">
                    <label for="option3">PAYPAL</label></br>
                    <input type="radio" id="option3" name="option" value="4">
                    <label for="option3">네이버페이</label></br>
                    </div>
                </div>
            </div>
          <div class="orderPayment orderPaymentRight">
            <p>주문상품</p>
            <hr>
            <div id="orderGoods">
                <img src="/img/home/cakeCategoryImg.jpg" alt="주문상품이미지" id="orderPaymentImg">
                <div style="margin-left : 10px;">
                <p>상품명</p>
                <p>5000원 / 1개</p>
                </div>
            </div>
            <div id="delivery">
                <div>배송비</div>
                <div>무료배송</div>
            </div>
            <hr style="margin-top : 30px; margin-bottom : 30px;">
            <div>
                <div class="goodsPrice">
                    <div>상품금액</div> <div>5000원</div>
                </div>
                <div class="goodsPrice">
                    <div>할인금액</div> <div>0원</div>
                </div>
            </div>
            <hr style="margin-top : 30px; margin-bottom : 30px;">
            <div class="goodsPrice">
                <div>총 결제금액</div> <div>5000원</div>
            </div>
            <p style="text-align : right; padding-right : 10px;">적립금 50원 적립예정</p>
            <div id="paymentButtonDiv">
                <button type="button" class="btn btn-primary" id="paymentButton" onclick="window.location.href='/order/orderResult';">결제하기</button>
            </div>
          </div>
        </div>
      </div>
      </article>
    </section>

    <%@ include file = "../footer.jsp" %>
</div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <script src="/js/order.js"></script>
  <script>
    var loginId = '<%=(String)session.getAttribute("loginId")%>';
    //alert(loginId);
    if(loginId=="null" || loginId==""){
        //alert("회원만 접근 가능합니다.");
        location.href="/home";
    }
  </script>
</body>
</html>
