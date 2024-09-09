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
      <article>
          <div class="orderDiv">
            <div id="orderDetailDiv">
              <h3 class="fontBold">주문내역</h3>
              <hr>
              <c:forEach items="${orderDetailDtoPage.content}" var="order">
                  <p><span class="fontBold">${order.orderDate}</span><a href="/my/myOrderDetail/${order.orderNum}"><span style="float : right">주문상세보기<i class="bi bi-chevron-right"></i></span></a></p>
                  <div class="orderDetail">
                    <div id="orderDetail1">
                      <div><img src="/upload/goods/${order.goodsStoredImgName}" class="goodsImg"></div>
                      <div id="orderDetail1_info">
                        <span class="fontBold">${order.deliveryType}</span> <i class="bi bi-chevron-right"></i>
                        <hr> ${order.goodsNum}
                        <a href="/goods/detail/${order.goodsNum}"><p>${order.goodsName}</a> <a href="#"><span class="cartButton"><i class="bi bi-cart"></i></span></a></p>
                        <p><span class="fontBold">${order.submitFinalPrice}원</span> / 1개</p>
                        <p>주문번호 ${order.orderUniqueNumber}</p>
                      </div>
                    </div>
                  </div>
              </c:forEach>
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