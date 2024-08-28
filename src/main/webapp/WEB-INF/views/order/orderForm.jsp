<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<link rel="stylesheet" href = "/css/project01.css">
<link rel="stylesheet" href = "/css/order.css">
<style>
</style>
</head>
<body>
<div id="container">
    <%@include file = "../header.jsp" %>
    <%@include file = "../nav.jsp" %>
    <hr>
    ${memberDto}
    <section class="section">
      <article>
        <div class="container">
            <div class = "row">
                <div class="col-12">
                    <h2>주문결제</h2>
                <div>
            </div>
        </div>
      </article>
      <article>
        <div class="container text-center orderFormBack">
          <div class="row">
            <div class="col">
                <div class="row">
                    <div class="col address">
                        <i class="bi bi-pin-map-fill"></i>
                        배송지
                    </div>
                </div>
                <div class="row payMethod">
                    <div class="col payMethod">왼쪽2</div>
                </div>
            </div>
            <div class="col payInfo">오른쪽1</div>
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
