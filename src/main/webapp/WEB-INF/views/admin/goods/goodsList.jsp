<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <link rel="stylesheet" href="/css/admin.css">
  <script src="/js/admin.js"></script>
  <title>Document</title>
</head>
<body>
  <%@include file = "../adminHeader.jsp" %>

  <section id="adminSection">
    <%@include file = "../adminSidebar.jsp" %>
    <article id="article02">


      <div id="mainArticle">
        <%@include file="./goodsNav.jsp" %>
        <div id="adminListDiv">
        <c:if test="${not empty goodsDtoPage}">
          <table class="table table-striped table-hover">
            <thead>
              <tr>
                <th scope="col" style="width:10%">상품번호</th>
                <th scope="col" style="width:30%">상품썸네일</th>
                <th scope="col" style="width:15%">상품명</th>
                <th scope="col" style="width:10%">상품가격</th>
                <th scope="col" style="width:10%">상품수량</th>
                <th scope="col" style="width:10%">상품카테고리</th>
                <th scope="col" style="width:15%">상품보기</th>
              </tr>
            </thead>
          <c:forEach items="${goodsDtoPage.content}" var="goods">
            <tbody>
              <tr>
                <th scope="row">${goods.num}</th>
                <td>
                    <img src="/upload/goods/${goods.storedGoodsImageName}" alt="상품섬네일" style="max-width:250px; max-height:250px">
                </td>
                <td>${goods.goodsName}</td>
                <td>${goods.price}</td>
                <td>${goods.stock}</td>
                <td>${goods.goodsCategory}</td>
                <td>
                    <form action="/admin/goodsDetail/${goods.num}">
                        <button type="submit" class="btn btn-secondary">
                           상품보기
                       </button>
                    </form>
                </td>
              </tr>
            </tbody>
          </c:forEach>
        </c:if>
        </div>
          </table>
          <div id="adminPagingNum">
            <c:choose>
                 <c:when test="${goodsDtoPage.isFirst()}">
                    처음
                 </c:when>
                 <c:otherwise>
                   <a href="/admin/memberList?page=1">처음</a>
                 </c:otherwise>
            </c:choose>
            <c:choose>
                 <c:when test="${currentPage<=blockLimit}">
                    이전
                 </c:when>
                 <c:otherwise>
                   <a href="/admin/goodsList?page=${startPage-1}">이전</a>
                 </c:otherwise>
            </c:choose>
            <c:forEach begin="${startPage}" end="${endPage}" var="count">
              <c:choose>
                <c:when test="${currentPage==count}">
                  ${currentPage}
                </c:when>
                <c:otherwise>
                  <a href="/admin/goodsList?page=${count}">${count}</a>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:choose>
               <c:when test="${lastPageSet==true}">
                  다음
               </c:when>
               <c:otherwise>
                 <a href="/admin/goodsList?page=${endPage+1}">다음</a>
               </c:otherwise>
            </c:choose>
            <c:choose>
               <c:when test="${goodsDtoPage.isLast()}">
                  마지막
               </c:when>
               <c:otherwise>
                 <a href="/admin/goodsList?page=${totalLastPage}">마지막</a>
               </c:otherwise>
            </c:choose>
          </div>
      </div>
    </article>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>