<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/project01.css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-gothic.css" rel="stylesheet">




    <title>BAKE SCENT</title>

</head>
<body>
    <div id="container">
        <%@ include file="./header.jsp" %>
        <%@ include file="./nav.jsp" %>
        <section id="main_section" class="section">
            <article id="article1">
                <div id="carouselExampleIndicators" class="carousel slide">
                  <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                  </div>
                  <div class="carousel-inner">
                    <div class="carousel-item active">
                      <img src="/img/home/homeBanner01.jpg" class="d-block w-100" alt="메인배너1">
                    </div>
                    <div class="carousel-item">
                      <img src="/img/home/homeBanner02.jpg" class="d-block w-100" alt="메인배너2">
                    </div>
                    <div class="carousel-item">
                      <img src="/img/home/homeBanner03.jpg" class="d-block w-100" alt="메인배너3">
                    </div>
                  </div>
                  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                  </button>
                  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                  </button>
                </div>
            </article>

            <article id="article2">
            <div class= "homeArticleTitleDiv">
                <h2 class= "homeArticleTitle">Menu Category</h2>
            </div>
                <div class="row">
                  <div class="col col-lg-3 col-md-6">
                    <div class="card">
                      <img src="/img/home/breadCategoryImg.jpg" class="card-img-top homeMenuCategory" alt="...">
                      <div class="card-body">
                        <h2 class="card-title">BREAD</h2>
                        <p class="card-text menuCategoryHashTag">#식빵&nbsp&nbsp#도넛&nbsp&nbsp#파이&nbsp&nbsp#페스트리&nbsp&nbsp<br>#건강빵&nbsp&nbsp#추천메뉴</p>
                        <a href="/goods/menu?topCategory=빵&subCategory=all&page=1" class="btn btn-outline-secondary">상품 보러가기</a>
                      </div>
                    </div>
                  </div>
                  <div class="col col-lg-3 col-md-6">
                    <div class="card">
                      <img src="/img/home/cakeCategoryImg.jpg" class="card-img-top homeMenuCategory" alt="...">
                      <div class="card-body">
                        <h2 class="card-title">CAKE</h2>
                        <p class="card-text menuCategoryHashTag">#생크림케이크&nbsp&nbsp#티라미수&nbsp&nbsp#치즈케이크&nbsp&nbsp<br>#조각케이크&nbsp&nbsp#캐릭터케이크</p>
                        <a href="/goods/menu?topCategory=케이크&subCategory=all&page=1" class="btn btn-outline-secondary">상품 보러가기</a>
                      </div>
                    </div>
                  </div>
                  <div class="col col-lg-3 col-md-6">
                    <div class="card">
                      <img src="/img/home/dessertCategoryImg.jpg" class="card-img-top homeMenuCategory" alt="...">
                      <div class="card-body">
                        <h2 class="card-title">DESSERT</h2>
                        <p class="card-text menuCategoryHashTag">#마카롱&nbsp&nbsp#아이스크림&nbsp&nbsp#초콜릿&nbsp&nbsp#사탕&nbsp&nbsp<br>#잼&nbsp&nbsp#쿠키</p>
                        <a href="/goods/menu?topCategory=디저트&subCategory=all&page=1" class="btn btn-outline-secondary">상품 보러가기</a>
                      </div>
                    </div>
                  </div>
                  <div class="col col-lg-3 col-md-6">
                    <div class="card">
                      <img src="/img/home/drinkCategoryImg.jpg" class="card-img-top homeMenuCategory" alt="...">
                      <div class="card-body">
                        <h2 class="card-title">Drink</h2>
                        <p class="card-text menuCategoryHashTag">#커피&nbsp&nbsp#밀크티&nbsp&nbsp#스무디&nbsp&nbsp#빙수&nbsp&nbsp#우유&nbsp&nbsp<br>#완제음료</p>
                        <a href="/goods/menu?topCategory=음료&subCategory=all&page=1" class="btn btn-outline-secondary">상품 보러가기</a>
                      </div>
                    </div>
                  </div>
                </div>
            </article>
            <article>
            </article>

            <article id="article3">
                <div class= "homeArticleTitleDiv">
                    <h2 class= "homeArticleTitle">BEST MENU</h2>
                </div>
                <div class="row">
                    <c:forEach items="${goodsDtoList}" var="goods">
                      <div class="col col-lg-3 col-md-6">
                        <div class="card">
                            <img src="/upload/goods/${goods.storedGoodsImageName}" class="card-img-top homeMenuCategory_article2" alt="...">
                            <div class="card-body">
                              <h2 class="card-title">${goods.goodsName}</h2>
                              <a href="/goods/detail/${goods.num}" class="btn btn-outline-secondary">상품 보러가기</a>
                            </div>
                        </div>
                      </div>
                    </c:forEach>
                </div>
            </article>

        </section>
        <%@ include file="./footer.jsp" %>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="/js/my.js"></script>

</body>
</html>
