<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href = "${path}/resources/css/project.css">
	</head>
	<body>
		<div id="container">
		  <header id="header">
		    <a href="/home">
		      <img src=".${path}/resources/img/logo.PNG">
		    </a>
			
			<% 
			    String loginId = (String) session.getAttribute("loginId");
			    if(loginId != null && loginId != "") { %>  <!--로그인 상태이면-->
			<div id="loginRegister">
			  ${loginId}님 환영합니다.!<br>
			  ${sessionScope.loginId}님!!<br>
			 <!-- <a class="sign_button" href="/member/logout"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
			    <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
			  </svg>로그아웃</a> -->
			  <form action="/member/logout" method="POST" id=logout-form>
			 	 <button type="submit" class="btn btn-main">
					<i class="bi bi-person-fill"></i>
					로그아웃
				</button>
			</form>
			<form action="/member/delete" metho="GET" id=delete-form>
				<input type="hidden" value="${loginId}" name="id">
			 	<button type="submit" class="btn btn-main">
				<i class="bi bi-person-fill"></i>
				회원탈퇴
			</button>
			</form>
		
			</div>
			
			<% } else { %>
		    <div id="loginRegister">
		      <a class=sign_button href="/member/login"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
		        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
		      </svg>로그인</a>
		      <a class="sign_button" href="/member/join"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
		        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
		      </svg>회원가입</a>
		    </div>
			<% } %>
		  </header>

		  <nav class="navbar navbar-expand-lg bg-body-tertiary">
		    <div class="container-fluid">
		      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		        <span class="navbar-toggler-icon"></span>
		      </button>
		      <div class="collapse navbar-collapse" id="navbarSupportedContent">
		        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		          <li class="nav-item">
		            <a class="nav-link active" aria-current="page" href="#">Home</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link" href="/board/list?page=1">게시판</a>
		          </li>
		          <li class="nav-item dropdown">
		            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		              Dropdown
		            </a>
		            <ul class="dropdown-menu">
		              <li><a class="dropdown-item" href="#">Action</a></li>
		              <li><a class="dropdown-item" href="#">Another action</a></li>
		              <li><hr class="dropdown-divider"></li>
		              <li><a class="dropdown-item" href="#">Something else here</a></li>
		            </ul>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
		          </li>
		        </ul>
		      <!--  <form class="d-flex" role="search">
		          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
		          <button class="btn btn-outline-success" type="submit">Search</button>
		        </form>-->
		      </div>
		    </div>
		  </nav>


		  <section id="main_section" class="section">

		    <article id="article1">

		    </article>
		    
		    <article id="article2">

		    </article>


		  </section>

		  <footer id="footer">
		    푸터
		  </footer>
		</div>
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>