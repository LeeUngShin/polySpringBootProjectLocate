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
        <%@include file="./goodsNav.jsp" %>
        <div id="goodsDetailDiv">

            <table class="table table-bordered table-hover" id="boardDetail">
                <tr>
                  <th>상품이미지</th>
                  <th>상품번호</th>
                  <td>${goodsDto.num}</td>
                </tr>
                <tr>
                  <td rowspan="7"><img src="/upload/goods/${goodsDto.storedGoodsImageName}"</td>
                  <th>상품명</th>
                  <td>${goodsDto.goodsName}</td>
                </tr>
                <tr>
                  <th>상품가격</th>
                  <td>${goodsDto.price}</td>
                </tr>
                <tr>
                  <th>상품수량</th>
                  <td>${goodsDto.stock}</td>
                </tr>
                <tr>
                  <th>찜개수</th>
                  <td>${goodsDto.likeCnt}</td>
                </tr>
                <tr>
                  <th>판매횟수</th>
                  <td>${goodsDto.sellCnt}</td>
                </tr>
                <tr>
                  <th>상품등록일</th>
                  <td>${goodsDto.regTime}</td>
                </tr>
                <tr>
                  <th>상품설명</th>
                  <td>${goodsDto.goodsExplanation}</td>
                </tr>

            </table>
           <div id="detailGoodsButton">
             <form action="/admin/deleteGoods/${goodsDto.num}" method="post" style="margin-right : 20px;" id="goodsDelete">
               <button type="button" class="btn btn-outline-secondary" onclick = "deleteGoods(${goodsDto.num})">상품삭제</button>
             </form>
             <form action="/admin/modifyGoodsForm/${goodsDto.num}">
               <button type="submit" class="btn btn-outline-secondary">상품수정</button>
             </form>
           </div>
       </div>
    </article>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>