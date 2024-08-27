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
      <article class="article1">
        <div class="col" id="detailMenuBar">
          <div class="row">
            <h3 style="font-weight: bold;">상품 안내</h3>
          </div>
          <div class="row">
            <nav aria-label="breadcrumb">
              <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/home" class="frontMenu">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">상품안내</li>
              </ol>
            </nav>
          </div>
          <div class="row detailMenuPageText">
            <p id="detailMenuPageText">상세페이지에서 제품별 영양 / 알레르기 정보를 확인하실 수 있습니다.</p>
          </div>
          <div class="row">
            <ul class="nav nav-underline">
              <li class="nav-item">
                <a class="nav-link tab-menu__item" href="/goods/menu?topCategory=all&subCategory=all&page=1">전체</a>
              </li>
              <li class="nav-item">
                <a class="nav-link tab-menu__item" href="/goods/menu?topCategory=빵&subCategory=all&page=1">빵</a>
              </li>
              <li class="nav-item">
                <a class="nav-link tab-menu__item" href="/goods/menu?topCategory=케이크&subCategory=all&page=1">케이크</a>
              </li>
              <li class="nav-item">
                <a class="nav-link tab-menu__item" href="/goods/menu?topCategory=디저트&subCategory=all&page=1">디저트</a>
              </li>
              <li class="nav-item">
                <a class="nav-link tab-menu__item" href="/goods/menu?topCategory=음료&subCategory=all&page=1">음료</a>
              </li>
            </ul>
          </div>
        </div>
        <div class="row" id="subMenu">
          <ul class="nav nav-underline">
            <li class="nav-item">
              <a class="nav-link" href="/goods/menu?topCategory=${topCategory}&subCategory=all&page=1">전체</a>
            </li>
            <c:forEach items="${goodsSubCategoryEntityList}" var = "item">
              <li class="nav-item">
                <a class="nav-link" href="/goods/menu?topCategory=${topCategory}&subCategory=${item.categoryName}&page=1">${item.categoryName}</a>
              </li>
            </c:forEach>
          </ul>
        </div>
      </article>

      <article id="article2">
        <h5 id="goods">
          베스트 상품
        </h5>
        <hr style="border: none; border-top: 1.5px solid #023586; margin: 10px 0px 0px 0px;">

        <div class="row" id="goodsCard">
            <c:forEach items="${bestGoodsDtoList}" var = "item">
                <div class="col-lg-3 col-md-6">
                      <div class="card" style="width: 18rem;" onmouseover="menuHover(${item.num})" onmouseout="menuHoverOut(${item.num})" id="menuCard">
                       <a href="/goods/detail/${item.num}?page=${currentPage}" class="goodsCard">
                        <div class="menuImgDiv">
                          <img src="/upload/goods/${item.storedGoodsImageName}" class="card-img-top menuImg" alt="...">
                          <div id="menuHover_${item.num}" class = "menuHover">
                          </div>
                        </div>
                        <div class="card-body">
                          <h6 class="card-title">베트스메뉴 ${item.goodsName}</h6>
                        </div>
                      </div>
                    </a>
                </div>
            </c:forEach>
        </div>
      </article>

      <article id="article3">
        <h5 id="goods">
          전체 상품
        </h5>
        <hr style="border: none; border-top: 1.5px solid #023586; margin: 10px 0px 0px 0px;">

        <div class="row" id="goodsCard">
           <c:forEach items="${goodsDtoPage.content}" var="goods">
              <div class="col-lg-3 col-md-6">
                  <div class="card" style="width: 18rem;" onmouseover="menuHover('${goods.num}')" onmouseout="menuHoverOut('${goods.num}')" class="menuCard">
                   <a href="/goods/detail/${goods.num}?page=${currentPage}" class="goodsCard">
                    <div class="menuImgDiv">
                      <img src="/upload/goods/${goods.storedGoodsImageName}" class="card-img-top menuImg" alt="...">
                      <div id="menuHover_${goods.num}" class="menuHover">
                      </div>
                    </div>
                    <div id="card-body">
                      <h6 class="card-title">${goods.goodsName}</h6>
                    </div>
                  </div>
                </a>
              </div>
           </c:forEach>
        </div>
        <div id="pageNum">
            <div style="flex-grow: 1;">
                <c:choose>
                    <c:when test="${currentPage == 1}">
                        처음
                    </c:when>
                    <c:otherwise>
                        <a href="/goods/menu?topCategory=${topCategory}&subCategory=${subCategory}&page=1">처음</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${currentPage<=blockLimit}">
                        이전
                    </c:when>
                    <c:otherwise>
                        <a href = "/goods/menu?topCategory=${topCategory}&subCategory=${subCategory}&page=${startPage-1}">이전</a>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="${startPage}" end ="${endPage}" var = "count">
                    <c:choose>
                        <c:when test = "${count != currentPage}">
                            <a href="/goods/menu?topCategory=${topCategory}&subCategory=${subCategory}&page=${count}">${count}</a>
                        </c:when>
                        <c:otherwise>
                            ${count}
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${isLast}">
                        다음
                    </c:when>
                    <c:otherwise>
                        <a href= "/goods/menu?topCategory=${topCategory}&subCategory=${subCategory}&page=${endPage+1}">다음</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${currentPage==totalPage}">
                        마지막
                    </c:when>
                    <c:otherwise>
                        <a href="/goods/menu?topCategory=${topCategory}&subCategory=${subCategory}&page=${totalPage}">마지막</a>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
      </article>
    </div>

    <%@ include file = "../footer.jsp" %>
</div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <script src="/js/menu.js">
  <script>
    const tabMenuItems = document.querySelectorAll('.tab-menu__item');
    console.log("tabMenuItems: ", tabMenuItems);
    tabMenuItems.forEach((item) => {
        console.log("item: ", item);
        item.addEventListener('click', () => {
            const activeItem = document.querySelector('.active');
            if (activeItem) {
                activeItem.classList.remove('active');
            }
            item.classList.add('active');
        });
    });
  </script>
</body>
</html>