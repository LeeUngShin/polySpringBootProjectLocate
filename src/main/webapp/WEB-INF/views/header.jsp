<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <header id="header">
        <a href="/home">
        <img src="/img/home/logo.PNG" id="homeLogo">
        </a>
        <div id="itemSearch">
            <form class="d-flex" role="search" action="/goods/search?page=1">
                <!--<select class="form-select" aria-label="Default select example" name="itemSearchCategory" style="width : 150px;">
                  <option selected>카테고리1</option>
                  <option value="#">카테고리2</option>
                  <option value="#">카테고리3</option>
                  <option value="#">카테고리6</option>
                </select>-->
                <input class="form-control me-2" type="search" placeholder="상품검색" aria-label="Search" name="keyword">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>

    <%
        String loginId = (String) session.getAttribute("loginId");
        if(loginId != null && loginId != "") { %>  <!--로그인 상태이면-->
    <div id="loginRegister">
        ${sessionScope.loginId}님!!<br>
        ${sessionScope.role}<br>
    <form action="/member/logout" method="POST" id=logout-form>
        <button type="submit" class="btn btn-main">
            <i class="bi bi-person-fill"></i>
            로그아웃
        </button>
    </form>
    <form action="/admin/home" method="GET" id=delete-form>
        <button type="submit" class="btn btn-main">
            관리자페이지로 이동
        </button>
    </form>

    </div>

    <% } else { %>
    <div id="loginRegister">
        <a class="sign_button" href="/member/login"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
            </svg></i>로그인</a>
        <a class="sign_button" href="/member/join"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
            </svg>회원가입</a>
        </div>
    <% } %>
  </header>
