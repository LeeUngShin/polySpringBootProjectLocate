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
<body onload="addSubOption()">
  <%@include file = "../adminHeader.jsp" %>

  <section id="adminSection">
    <%@include file = "../adminSidebar.jsp" %>
        <article id="article02">
          <div id="mainArticle">
          <%@include file="./goodsNav.jsp" %>
          <div style="text-align : center">
            <h1 style="padding-top : 50px">상품등록</h1>
          </div>
          <hr>
            <div id="goodsRegisterDiv">
              <form action="/admin/goodsRegister" method="POST" enctype="multipart/form-data">
                <div class="mb-3"">
                  <label for="exampleInputName" class="form-label">상품명</label>
                  <input type="text" class="form-control" id="exampleInputName" name="goodsName" required>
                </div>
                <div class="mb-3">
                  <label for="exampleInputPrice" class="form-label">상품가격</label>
                  <input type="number" class="form-control" id="exampleInputStock" name="price" required>
                </div>
                <div class="mb-3">
                  <label for="exampleInputStock" class="form-label">상품수량</label>
                  <input type="number" class="form-control" id="exampleInputStock" name="stock" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">상위카테고리</label>
                    <select class="form-select" aria-label="Default select example" name="goodsCategory" onchange = "addSubOption()" id="topOption" required>

                      <option value="빵" selected>빵</option>
                      <option value="케이크">케이크</option>
                      <option value="디저트">디저트</option>
                      <option value="음료">음료</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">하위카테고리</label>
                    <select class="form-select" aria-label="Default select example" name="goodsSubCategory" id="subOption" required>
                    </select>
                </div>
                <div>
                  <label class="form-label">상품설명</label>
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text">상품설명</span>
                  <textarea class="form-control" aria-label="With textarea" name="goodsExplanation" ></textarea>
                </div>
                <div class="mb-3">
                  <label for="formFile" class="form-label">상품이미지</label>
                  <input class="form-control" type="file" id="formFile" name="goodsImageFile" required>
                </div>
                <button type="submit" class="btn btn-primary">상품등록</button>
              </form>
            </div>
          </div>
        </article>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>