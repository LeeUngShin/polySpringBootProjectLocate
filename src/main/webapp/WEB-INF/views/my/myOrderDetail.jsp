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
      <%@include file = "./mySideBar.jsp" %>
      <article style="min-width: 800px";>
          <div class="orderDiv">
            <div id="orderDetailDiv">
              <h3 class="fontBold" style="margin-bottom : 20px;">주문상세</h3>
              <p class="fontBold">${orderDetailDto.orderDate}</p>
              <div class="orderDetail">
                <div id="orderDetail1">
                  <div><img src="/upload/goods/${orderDetailDto.goodsStoredImgName}" class="goodsImg"></div>
                  <div id="orderDetail1_info">
                    <span class="fontBold">${orderDetailDto.deliveryTypeStr}</span> <i class="bi bi-chevron-right"></i>
                    <hr>
                    <a href="/goods/detail/${orderDetailDto.goodsNum}" class="a_black"><p>${orderDetailDto.goodsName}</a><a href="#" class="a_black"><span class="cartButton"><i class="bi bi-cart"></i></span></a></p>
                    주문번호 ${orderDetailDto.orderUniqueNumber}
                    <hr>
                    <ul>
                      <li>결제금액 ${orderDetailDto.submitFinalPrice} / ${orderDetailDto.submitAmount}개 </li>
                    </ul>
                  </div>
                </div>
              </div>

              <div class="orderDetail">
                <h4 class="fontBold">배송정보</h4>
                <hr>
                <div>
                  <div id="orderDetail2">
                    <div>
                      <p>받는 사람</p>
                      <p>연락처</p>
                      <p>배송주소</p>
                      <p>배송 요청사항</p>
                    </div>
                    <div id="orderDetail2Right">
                      <p>${orderDetailDto.memberName}</p>
                      <p>${orderDetailDto.phone1}-${orderDetailDto.phone2}-${orderDetailDto.phone3}</p>
                      <p>(${orderDetailDto.submitPost}) ${orderDetailDto.submitAddr} ${orderDetailDto.submitAddrDetail}</p>
                      <p>${orderDetailDto.submitOrderMessageChoice}</p>
                    </div>
                  </div>
                </div>
              </div>

              <div class="orderDetail">
                <h4 class="fontBold">결제 정보</h4>
                <hr>
                <div id="orderDetail3">
                  <div id="orderDetail3Left">
                    <p>결제수단</p>
                    <p>${orderDetailDto.submitPaymentMethodStr}</p>
                    <hr>
                    <p>사용적립금 ${orderDetailDto.submitUseAccumulatedMoney}원</p>
                    <p>예상 적립금 : ${orderDetailDto.submitAccumulatedMoney}원</p>
                  </div>
                  <div id="orderDetail3Right">
                    <div class="orderDetail3Right">
                      <div class="fontBold" >총 결제금액</div> <div class="fontBold">${orderDetailDto.submitFinalPrice}원</div>
                    </div>
                    <div class="orderDetail3Right">
                      <div>총 상품금액</div> <div>${orderDetailDto.submitGoodsTotalPrice}원</div>
                    </div>
                    <div class="orderDetail3Right">
                      <div>총 할인금액</div> <div>${orderDetailDto.submitUseAccumulatedMoney}원</div>
                    </div>
                  </div>
                </div>
              </div>
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

</body>
</html>