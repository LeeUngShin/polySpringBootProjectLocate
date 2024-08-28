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
    <hr>
    <section class="section ">
      <article>
          <div class="container">
            <div class="row myMainRow">
              <div class="col-2">
                <h2 style="margin-bottom : 20px;">My Menu</h2>
                <nav class="nav flex-column myNavDiv">
                  <a class="nav-link active myNav" aria-current="page" href="#" style="font-weight : bold">홈</a>
                  <a class="nav-link myNav" href="/member/modify">회원정보 수정</a>
                  <a class="nav-link myNav" href="/member/delete">회원탈퇴</a>
                  <hr>
                  <a class="nav-link myNav" href="/member/myBoard?page=1">게시글 모아보기</a>
                  <hr>
                  <a class="nav-link myNav" href="#">주문내역</a>
                  <a class="nav-link myNav" href="#">장바구니</a>
                </nav>
                          <button onclick="test()">버튼</button>

              </div>
              <div class="col-10">
                col-8
              </div>
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