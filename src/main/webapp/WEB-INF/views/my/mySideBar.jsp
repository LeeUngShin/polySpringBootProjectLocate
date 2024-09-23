<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
      <article>
          <div class="container">
            <div class="myMenu">
              <div>
                <h2 style="margin-bottom : 20px;">My Menu</h2>
                <nav class="nav flex-column myNavDiv">
                  <a class="nav-link active myNav" aria-current="page" href="#" style="font-weight : bold">홈</a>
                  <a class="nav-link myNav" href="/member/modify">회원정보 수정</a>
                  <a class="nav-link myNav" href="/member/delete">회원탈퇴</a>
                  <hr>
                  <a class="nav-link myNav" href="/member/myBoard?page=1">게시글 모아보기</a>
                  <hr>
                  <a class="nav-link myNav" href="/my/orderList">주문내역</a>
                  <a class="nav-link myNav" href="/my/myCartList">장바구니</a>
                </nav>
              </div>
            </div>
          </div>
      </article>
</body>