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
    <div class="section ">
      <article id="article1">
        <div class="col" id="detailMenuBar">
          <div class="row">
            <h3 style="font-weight: bold;">상품 안내</h3>
          </div>
          <div class="row">
            <nav aria-label="breadcrumb">
              <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/home" class="frontMenu">Home</a></li>
                <li class="breadcrumb-item"><a href="#" class="frontMenu">Library</a></li>
                <li class="breadcrumb-item active" aria-current="page">Data</li>
              </ol>
            </nav>
          </div>
          <div class="row detailMenuPageText">
            <p id="detailMenuPageText">상세페이지에서 제품별 영양 / 알레르기 정보를 확인하실 수 있습니다.</p>
          </div>
          <div class="row">
            <ul class="nav nav-underline">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">active</a>
              </li>
              <li class="nav-item" onclick="menuClick(this)">
                <a class="nav-link" aria-current="page"  href="#">전체</a>
              </li>
              <li class="nav-item" onclick="menuClick(this)">
                <a class="nav-link" href="#">빵</a>
              </li>
              <li class="nav-item" onclick="menuClick(this)">
                <a class="nav-link" href="#">케이크</a>
              </li>
              <li class="nav-item" onclick="menuClick(this)">
                <a class="nav-link" href="#">디저트</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" onclick="menuClick(this)">음료</a>
              </li>
            </ul>
          </div>
        </div>
        <div class="row" id="subMenu">
          <ul class="nav nav-underline">
            <li class="nav-item">
              <a class="nav-link active" href="#">active</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="menuClick(this)">전체</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="menuClick(this)">식빵</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="menuClick(this)">건강빵</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="menuClick(this)">빵2</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="menuClick(this)">빵3</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="menuClick(this)">빵4</a>
            </li>
          </ul>
        </div>
      </article>

      <article id="article2">
        <h5 id="goods">
          베스트 상품
        </h5>
        <hr style="border: none; border-top: 1.5px solid #023586; margin: 10px 0px 0px 0px;">

        <div class="row" id="goodsCard">
          <div class="col-lg-3 col-md-6">
            <a href="#">
              <div class="card" style="width: 18rem;" onmouseover="menuHover()" onmouseout="menuHoverOut()" id="menuCard">
                <div class="menuImgDiv">
                  <img src="detailMenuBackgroundImg.jpg" class="card-img-top menuImg" alt="...">
                  <div id="menuHover">
                  </div>
                </div>
                <div class="card-body">
                  <h7 class="card-title">베트스메뉴1</h7>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-3 col-md-6">
            <div class="card" style="width: 18rem;">
              <div class="menuImgDiv">
                <img src="detailMenuBackgroundImg.jpg" class="card-img-top menuImg" alt="...">
                <div id="menuHover">
                </div>
              </div>
              <div class="card-body">
                <h7 class="card-title">베트스메뉴2</h7>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6">
            <div class="card" style="width: 18rem;">
              <div class="menuImgDiv">
                <img src="detailMenuBackgroundImg.jpg" class="card-img-top menuImg" alt="...">
                <div id="menuHover">
                </div>
              </div>
              <div class="card-body">
                <h7 class="card-title">베트스메뉴3</h7>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6">
            <div class="card" style="width: 18rem;">
              <div class="menuImgDiv">
                <img src="detailMenuBackgroundImg.jpg" class="card-img-top menuImg" alt="...">
                <div id="menuHover">
                </div>
              </div>
              <div class="card-body">
                <h7 class="card-title">베트스메뉴4</h7>
              </div>
            </div>
          </div>
        </div>
      </article>

      <article id="article3">
        <h5 id="goods">
          전체 상품
        </h5>
        <hr style="border: none; border-top: 1.5px solid #023586; margin: 10px 0px 0px 0px;">

        <div class="row" id="goodsCard">
          <div class="col-lg-3 col-md-6">
            <a href="#">
              <div class="card" style="width: 18rem;" onmouseover="menuHover()" onmouseout="menuHoverOut()" id="menuCard">
                <div class="menuImgDiv">
                  <img src="detailMenuBackgroundImg.jpg" class="card-img-top menuImg" alt="...">
                  <div id="menuHover">
                  </div>
                </div>
                <div class="card-body">
                  <h7 class="card-title">베트스메뉴1</h7>
                </div>
              </div>
            </a>
          </div>
          <div class="col-lg-3 col-md-6">
            <div class="card" style="width: 18rem;">
              <div class="menuImgDiv">
                <img src="detailMenuBackgroundImg.jpg" class="card-img-top menuImg" alt="...">
                <div id="menuHover">
                </div>
              </div>
              <div class="card-body">
                <h7 class="card-title">베트스메뉴2</h7>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6">
            <div class="card" style="width: 18rem;">
              <div class="menuImgDiv">
                <img src="detailMenuBackgroundImg.jpg" class="card-img-top menuImg" alt="...">
                <div id="menuHover">
                </div>
              </div>
              <div class="card-body">
                <h7 class="card-title">베트스메뉴3</h7>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6">
            <div class="card" style="width: 18rem;">
              <div class="menuImgDiv">
                <img src="detailMenuBackgroundImg.jpg" class="card-img-top menuImg" alt="...">
                <div id="menuHover">
                </div>
              </div>
              <div class="card-body">
                <h7 class="card-title">베트스메뉴4</h7>
              </div>
            </div>
          </div>
        </div>
      </article>
    </div>

    <%@ include file = "../footer.jsp" %>
</div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>