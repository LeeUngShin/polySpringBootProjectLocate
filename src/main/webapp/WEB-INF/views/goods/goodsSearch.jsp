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
    <div>
      <article id="article3">
        <h5 id="goods">
          검색 상품
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
                        <a href="/goods/search?page=1&keyword=${keyword}">처음</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${currentPage<=blockLimit}">
                        이전
                    </c:when>
                    <c:otherwise>
                        <a href = "/goods/search?page=${startPage-1}&{keyword}">이전</a>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="${startPage}" end ="${endPage}" var = "count">
                    <c:choose>
                        <c:when test = "${count != currentPage}">
                            <a href="/goods/search?page=${count}&{keyword}">${count}</a>
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
                        <a href= "/goods/search?page=${endPage+1}&keyword=${keyword}">다음</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${currentPage==totalPage || totalPage==0}">
                        마지막
                    </c:when>
                    <c:otherwise>
                        <a href="/goods/search?page=${totalPage}&keyword=${keyword}">마지막</a>
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
</body>
</html>