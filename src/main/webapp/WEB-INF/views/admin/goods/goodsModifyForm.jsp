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
<body onload = "modifySubOption('${goodsDto.goodsSubCategory}')">
  <%@include file = "../adminHeader.jsp" %>

  <section id="adminSection">
    <%@include file = "../adminSidebar.jsp" %>
        <article id="article02">
          <div id="mainArticle">
          <%@include file="./goodsNav.jsp" %>
          <div style="text-align : center">
            <h1 style="padding-top : 50px">상품수정</h1>
          </div>
          <hr>
            <div id="goodsRegisterDiv">
              <form action="/admin/modifyGoods/${goodsDto.num}" method="POST" enctype="multipart/form-data">
                <div class="mb-3"">
                  <label for="exampleInputName" class="form-label">상품명</label>
                  <input type="text" class="form-control" id="exampleInputName" name="goodsName" value="${goodsDto.goodsName}" required>
                </div>
                <div class="mb-3">
                  <label for="exampleInputPrice" class="form-label">상품가격</label>
                  <input type="number" class="form-control" id="exampleInputStock" name="price" value="${goodsDto.price}" required>
                </div>
                <div class="mb-3">
                  <label for="exampleInputStock" class="form-label">상품수량</label>
                  <input type="number" class="form-control" id="exampleInputStock" name="stock" value="${goodsDto.stock}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">상위카테고리</label>
                    <select class="form-select" aria-label="Default select example" name="goodsCategory" id="topOption" value="${goodsDto.goodsCategory}" onchange = "modifySubOption()"required>
                      <!--<option value="빵" selected>빵</option>
                      <option value="케이크">케이크</option>
                      <option value="디저트">디저트</option>
                      <option value="음료">음료</option>-->
                      <option value="빵" ${goodsDto.goodsCategory=='빵' ? 'selected' : ''}>빵</option>
                      <option value="케이크" ${goodsDto.goodsCategory=='케이크' ? 'selected' : ''}>케이크</option>
                      <option value="디저트" ${goodsDto.goodsCategory=='디저트' ? 'selected' : ''}>디저트</option>
                      <option value="음료" ${goodsDto.goodsCategory=='음료' ? 'selected' : ''}>음료</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">하위카테고리</label>
                    <select class="form-select" aria-label="Default select example" name="goodsSubCategory" id="subOption" value="${goodsDto.goodsSubCategory}" required>
                    </select>
                </div>
                <div class="mb-3"">
                  <label for="exampleInputKcal" class="form-label">칼로리(Kcal)</label>
                  <input type="number" class="form-control" id="exampleInputKcal" name="kcal" value="${goodsDto.kcal}" required>
                </div>
                <div class="mb-3"">
                  <label for="exampleInputProtein" class="form-label">단백질(g)</label>
                  <input type="number" class="form-control" id="exampleInputProtein" name="protein" value="${goodsDto.protein}" required>
                </div>
                <div class="mb-3"">
                  <label for="exampleInputFat" class="form-label">지방(g)</label>
                  <input type="number" class="form-control" id="exampleInputFat" name="fat" value="${goodsDto.fat}" required>
                </div>
                <div class="mb-3"">
                  <label for="exampleInputNatrium" class="form-label">나트륨(mg)</label>
                  <input type="number" class="form-control" id="exampleInputNatrium" name="natrium" value="${goodsDto.natrium}" required>
                </div>
                <div class="mb-3"">
                  <label for="exampleInputSugar" class="form-label">당류(g)</label>
                  <input type="number" class="form-control" id="exampleInputSugar" name="sugar" value="${goodsDto.sugar}" required>
                </div>
                <div class="mb-3"">
                  <label for="exampleInputWeight" class="form-label">중량(g)</label>
                  <input type="number" class="form-control" id="exampleInputWeight" name="weight" value="${goodsDto.weight}"required>
                </div>
                <div class="mb-3"">
                        <label for="" class="form-label">알러지</label>
                    <div id="allergyCheckBox">
                        <input type="checkbox" id="wheat" name="allergy" value="밀"
                        <c:if test="${goodsDto.allergy.contains('밀')}">checked</c:if>>
                        <label for="wheat">밀&nbsp&nbsp</label>
                        <input type="checkbox" id="soybean" name="allergy" value="대두"
                        <c:if test="${goodsDto.allergy.contains('대두')}">checked</c:if>>
                        <label for="soybean">대두&nbsp&nbsp</label>
                        <input type="checkbox" id="milk" name="allergy" value="우유"
                        <c:if test="${goodsDto.allergy.contains('우유')}">checked</c:if>>
                        <label for="milk">우유&nbsp&nbsp</label>
                        <input type="checkbox" id="egg" name="allergy" value="계란"
                        <c:if test="${goodsDto.allergy.contains('계란')}">checked</c:if>>
                        <label for="egg">계란&nbsp&nbsp</label>
                        <input type="checkbox" id="caffeine" name="allergy" value="고카페인"
                        <c:if test="${goodsDto.allergy.contains('고카페인')}">checked</c:if>>
                        <label for="caffeine">고카페인</label>
                    </div>
                </div>
                <div>
                  <label class="form-label">상품설명</label>
                </div>
                <div class="input-group mb-3">
                  <span class="input-group-text">상품설명</span>
                  <textarea class="form-control" aria-label="With textarea" name="goodsExplanation" >${goodsDto.goodsExplanation}</textarea>
                </div>
                <div class="mb-3">
                  <label for="formFile" class="form-label">상품이미지</label>
                  <input class="form-control" type="file" id="formFile" name="goodsImageFile">
                </div>
                <button type="submit" class="btn btn btn-light">상품정보수정</button>
              </form>
            </div>
          </div>
        </article>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <script>
    document.getElementById("topOption").value = "${goodsDto.goodsCategory}";
  </script>
   <script>
      var role = '<%=(String)session.getAttribute("role")%>';
      //alert(role);
      if((role != "ROLE_ADMIN") || role=='null' || role==""){
          alert("관리자만 접근 가능합니다.")
          window.location.href = "/home";
      }
    </script>
</body>
</html>