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
          <div class="row">
            <div class="col-12">
              <h2>주문결제</h2>
            </div>
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
                            <p style="margin-bottom: 0px"><i class="bi bi-pin-map-fill"></i> 배송지</p>
                            <button type="button" class="btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#staticBackdrop">배송지변경</button>
                        </div>
                        <hr>
                        <p>${memberDto.name} ${memberDto.phone1}-${memberDto.phone2}-${memberDto.phone3}</p>
                        <p><div id="post">(${memberDto.post})</div> <div id="addr">${memberDto.addr}</div> <div id="addrDetail">${memberDto.addrDetail}</div></p>
                        <div class="mb-3">
                          <label for="exampleFormControlInput1" class="form-label">배송메세지</label>
                          <p>
                            <select name="color" class="form-control" id="orderMessageChoice">
                                <option value="배송시 요청사항을 선택해 주세요.">배송시 요청사항을 선택해 주세요.</option>
                                <option value="부재 시 경비실에 맡겨주세요.">부재 시 경비실에 맡겨주세요.</option>
                                <option value="배송 전 연락바랍니다">배송 전 연락바랍니다</option>
                                <option value="부재 시 문 앞에 놓아주세요.">부재 시 문 앞에 놓아주세요.</option>
                                <option value="직접 입력">직접 입력</option>
                            </select>
                          </p>
                          <input type="text" class="form-control" id="orderMessageInput" placeholder="직접 입력" style="display: none">
                        </div>
                    </div>
                </div>
                <div class="orderPayment">
                    <div class="orderPayment">
                        <p>적립금 사용</p>
                        <hr>
                        <p>나의 적립금 ${memberDto.accumulatedMoney}원</p>
                        <p>
                            <input type="text" class="form-control" id="useAccumulateMoney" value="0" style="width: 50%; display: inline-block">
                            <button type="button" class="btn btn-outline-dark" onclick="accumulatedMoneyUse(${memberDto.accumulatedMoney}, ${finalPrice})">적립금 적용</button>
                        </p>
                    </div>
                </div>
                <div class="orderPayment">
                    <div class="orderPayment">
                        <p>결제 수단</p>
                        <hr>
                        <input type="radio" id="option1" name="option" value="CREDIT_CARD" checked>
                        <label for="option1">신용카드</label><br>
                        <input type="radio" id="option2" name="option" value="BANK_TRANSFER">
                        <label for="option2">계좌자동이체</label><br>
                        <input type="radio" id="option3" name="option" value="PAPAL">
                        <label for="option3">PAYPAL</label><br>
                        <input type="radio" id="option4" name="option" value="NAVER_PAY">
                        <label for="option4">네이버페이</label><br>
                    </div>
                </div>
            </div>
            <div class="orderPayment orderPaymentRight">
                <p>주문상품</p>
                <hr>
                <div id="orderGoods">
                    <img src="/img/home/cakeCategoryImg.jpg" alt="주문상품이미지" id="orderPaymentImg">
                    <div style="margin-left: 10px;">
                        <p>상품명</p>
                        <p><span>${goodsDto.price}</span><span>원 / <span id="amount" name="amount"> ${amount}</span><span>개</span></p>
                    </div>
                </div>
                <div id="delivery">
                    <div>배송비</div>
                    <c:if test="${deliveryPrice eq 'Y'}">
                        <div><span id="deliveryPrice" name="deliveryPrice">3000</span>원</div>
                    </c:if>
                    <c:if test="${deliveryPrice eq 'N'}">
                        <div><span id="deliveryPrice" name="deliveryPrice">0</span>원(무료배송)</div>
                    </c:if>
                </div>
                <hr style="margin-top: 30px; margin-bottom: 30px;">
                <div>
                    <div class="goodsPrice">
                        <div>상품금액</div> <div><span id="goodsOriginalPrice">${finalPrice}</span><span>원</span></div>
                    </div>
                    <div class="goodsPrice">
                        <div>할인금액</div> <div><span id="disCountPrice">0</span><span>원</span></div>
                    </div>
                </div>
                <hr style="margin-top: 30px; margin-bottom: 30px;">
                <div class="goodsPrice">
                    <div style="font-size: 20px;">총 결제금액</div> <div><span id="finalPrice">${finalPrice}</span><span>원</span></div>
                </div>
                <div style="text-align: right; padding-right: 10px;"><span>적립금</span> <span id="accumulateMoney"></span><span>원 적립예정</span></div>
                <div id="paymentButtonDiv">
                <form method="post">
                    <input type="hidden" name="submitMemberId" id="submitMemberId">
                    <input type="hidden" name="submitGoodsNum" id="submitGoodsNum">
                    <input type="hidden" name="submitPost" id="submitPost">
                    <input type="hidden" name="submitAddr" id="submitAddr">
                    <input type="hidden" name="submitAddrDetail" id="submitAddrDetail">
                    <input type="hidden" name="submitOrderMessageChoice" id="submitOrderMessageChoice">
                    <input type="hidden" name="submitUseAccumulatedMoney" id="submitUseAccumulatedMoney">
                    <input type="hidden" name="submitPaymentMethod" id="submitPaymentMethod">
                    <input type="hidden" name="submitAmount" id="submitAmount">
                    <input type="hidden" name="submitDeliveryPrice" id="submitDeliveryPrice">
                    <input type="hidden" name="submitFinalPrice" id="submitFinalPrice">
                    <input type="hidden" name="submitAccumulatedMoney" id="submitAccumulatedMoney">
                    <button type="button" class="btn btn-primary" id="paymentButton" onclick="submitOrderInfo(${goodsDto.num}, '${sessionScope.loginId}')">결제하기</button>
                </form>
                </div>
            </div>
          </div>
        </div>
      </article>
      </article>
    </section>

    <%@ include file = "../footer.jsp" %>
</div>


<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">주소변경하기</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <input type="text" id="sample6_postcode" placeholder="우편번호">
        <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
        <input type="text" id="sample6_address" placeholder="주소"><br>
        <input type="text" id="sample6_detailAddress" placeholder="상세주소">
        <input type="text" id="sample6_extraAddress" placeholder="참고항목">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="addrCh()">주소변경</button>
      </div>
    </div>
  </div>
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
  // 데이터 보내기
  <script>
    var disCountPrice = document.getElementById("useAccumulateMoney").value;
    document.getElementById("disCountPrice").textContent = disCountPrice;
    var goodsPrice = '${finalPrice}';
    accumulatedMoney = Math.round(goodsPrice/100)
    document.getElementById("accumulateMoney").textContent =  accumulatedMoney;
  </script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;

                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
</body>
</html>
