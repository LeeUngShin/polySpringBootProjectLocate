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
    <section>
      <div id="orderDiv">
        <div id="orderComplete">
          <h3 class="fontBold">주문이 완료되었습니다.</h3>
          <div>
            <p>받는 사람 : ${savedOrderDetailDto.memberName}(${savedOrderDetailDto.phone1}-${savedOrderDetailDto.phone2}-${savedOrderDetailDto.phone3})</p>
            <p>받는 주소 : (${savedOrderDetailDto.submitPost}) ${savedOrderDetailDto.submitAddr} ${savedOrderDetailDto.submitAddrDetail} </p>
            <p>배송요청사항 : ${savedOrderDetailDto.submitOrderMessageChoice}</p>
            <hr>
            <p>총 상품가격 ${savedOrderDetailDto.submitGoodsTotalPrice}원</p>
            <p>적립금 사용 ${savedOrderDetailDto.submitUseAccumulatedMoney}원</p>
            <hr>
            <p>총 결제 금액 ${savedOrderDetailDto.submitFinalPrice}원</p>
          </div>
          <a href="/my/myOrderDetail/${savedOrderDetailDto.orderNum}" type="button" class="btn btn-primary">주문 상세보기</a>
          <a href="/goods/menu" class="btn btn-light">쇼핑 계속 하기</a>
        </div>
      </div>
    </section>
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