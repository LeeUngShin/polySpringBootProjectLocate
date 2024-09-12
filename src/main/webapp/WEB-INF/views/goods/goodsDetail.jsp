<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<link rel="stylesheet" href = "/css/project01.css">
<link rel="stylesheet" href = "/css/menu.css">
</head>
<body>
<div id="container">
    <%@include file = "../header.jsp" %>
    <%@include file = "../nav.jsp" %>
            <hr>
    <section class="section">
      <article class="article2">
          <div class="container">
            <div class="row">
              <div class="col-sm-8">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb menuDetailBreadcrumb">
                      <li class="breadcrumb-item"><a href="/home">Home</a></li>
                      <li class="breadcrumb-item"><a href="/goods/menu?topCategory=all&subCategory=all&page=1">상품안내</a></li>
                      <li class="breadcrumb-item"><a href="/goods/menu?topCategory=${topCategory}&subCategory=all&page=1">${topCategory}</a></li>
                      <li class="breadcrumb-item"><a href="/goods/menu?topCategory=${topCategory}&subCategory=${subCategory}&page=1">${subCategory}</a></li>
                      <li class="breadcrumb-item active" aria-current="page">Data</li>
                    </ol>
                </nav>
              </div>
              <div class="col-sm-4 menuLocationIcon">
                  <a href= "/goods/detail/${goodsDto.num-1}">
                    <i class="bi bi-arrow-left-circle" style="font-size : 35px;"></i>
                  </a>
                  <a href= "/goods/detail/${goodsDto.num+1}">
                    <i class="bi bi-arrow-right-circle" style="font-size : 35px;"></i>
                  </a>
              </div>
            </div>
          </div>
      </article>
      <article class="article3">
        <div class="container">
            <div class="row goodsDetailMain">
                <div class="col menuDetailImgDiv">
                    <img src="/upload/goods/${goodsDto.storedGoodsImageName}" class="goodsDetailImg">
                </div>
                <div class="col goodsDetailExplain">
                    <div>
                        <h1 id="menuName">${goodsDto.goodsName}
                            <button type="button" class="btn btn-outline-secondary" onclick="likeYesNo('${sessionScope.loginId}', '${goodsDto.num}', '${goodsLike}')">
                              <i class="bi bi-heart-fill" id="like" style="color : red;"></i>

                              <i class="bi bi-heart" id="likeNot"></i>
                               찜하기
                            </button>
                            ${goodsLike}
                        </h1>
                        <p id="menuExplain">${goodsDto.goodsExplanation}</p>
                        <h5>가격정보</h5>
                        <p class="menuInfo">${goodsDto.price}원</p>
                        <hr>
                        <h5>영양정보</h5>
                        <p class="menuInfo">총내용량 : ${goodsDto.weight}gㆍ100g 당 칼로리 : ${goodsDto.kcal}kcal<br>
                        100g 당 - 나트륨(mg) : ${goodsDto.natrium}ㆍ당류(g) : ${goodsDto.sugar}ㆍ포화지방(g) : ${goodsDto.fat}ㆍ단백질(g) : ${goodsDto.protein}</p>
                        <hr>
                        <h5>알레르기 정보정보</h5>
                        <p class="menuInfo">
                            <c:forEach items="${goodsDto.allergy}" var= "allergy">
                                ${allergy}
                            </c:forEach>
                            함유
                        </p>
                        <hr>
                        <form action="/order/orderForm" method="get">
                            <!--
                            <button type="button" class="btn btn-outline-secondary"
                            onclick="javascript:location.replace('/order/orderForm/${goodsDto.num}')">
                                구매
                            </button>-->
                            <input type="hidden" value="${goodsDto.num}" name="goodsNum">
                            <button type="submit" class="btn btn-outline-secondary" style="margin-bottom : 10px;">
                              <i class="bi bi-cart-plus"></i> 구매
                            </button>
                            수량
                            <span>
                                <button type="button" class="amountButton" onclick="amountMinusOrder(${goodsDto.stock})" >-</button>
                                <input id="amount" name="amount" value="1" onchange="amountPlusMinusOrder(${goodsDto.stock})"></input>
                                <button type="button" class="amountButton" onclick="amountPlusOrder(${goodsDto.stock})">+</button>
                            </span>
                        </form>
                        <div>
                            <form id="cartInput">
                                <button type="button" class="btn btn-outline-secondary"
                                onclick="cartClick('${sessionScope.loginId}', ${goodsDto.price}, ${goodsDto.num}, '${cartExists}')">
                                  <i class="bi bi-cart-plus"></i> 장바구니
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <hr id="menuDetailEndHr">
        </div>
      </article>
    </section>
    <form>
    <div name="aa">1000</div>

    </form>


    <%@ include file = "../footer.jsp" %>
</div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <script src="/js/menu.js"></script>
  <script>
    var goodsLike = '${goodsLike}';
    if(goodsLike == 'Y'){
        like.classList.add("likeYesNo");
        likeNot.classList.remove("likeYesNo");
    }
    if(goodsLike == 'N'){
        like.classList.remove("likeYesNo");
        likeNot.classList.add("likeYesNo");
    }
  </script>

  <script>
  /*   const cookieString = document.cookie;

     const parts = cookieString.split('=');

     var cookieValue;

     if (parts.length === 2) {
         cookieValue = decodeURIComponent(parts[1]); // URL 디코딩하여 반환
     }

     if(cookieValue === 'Y'){
        like.classList.add('likeYesNo');
        likeNot.classList.remove('likeYesNo');
     }
     else{
        likeNot.classList.add('likeYesNo');
        like.classList.remove('likeYesNo');
     }*/
  </script>
</body>
</html>