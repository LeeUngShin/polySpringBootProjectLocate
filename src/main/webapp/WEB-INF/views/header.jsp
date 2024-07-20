<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		  <header id="header">
		    <a href="/home">
		      <img src="../../../resources/img/logo.PNG">
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
			  <form action="/member/logout" metho="POST" id=logout-form>
			  <button type="submit" class="btn btn-main">
				<i class="bi bi-person-fill"></i>
				로그아웃
			</button>
			</form>
			<form action="/member/delete/${member.id}" metho="GET" id=delete-form>
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
		      </svg></i>로그인</a>
		      <a class="sign_button" href="/member/join"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
		        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
		      </svg>회원가입</a>
		    </div>
			<% } %>
		  </header>
