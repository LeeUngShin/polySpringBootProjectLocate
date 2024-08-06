<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href = "/css/project01.css">
	</head>
	<body>
	    <div class="middleLogo">
            <a href="/home">
                <img src="../img/logo.PNG">
            </a>
        </div>

	    <form action="/member/login" method="POST">
		   <div id="loginform">
		     <div class="mb-3">
		       <label for="exampleInputId1" class="form-label">아이디</label>
		       <input type="text" class="form-control" id="exampleInputId1" name="id" value="${cookie.REMEMBER.value}">
		     </div>
		     <div class="mb-3">
		       <label for="exampleInputPassword1" class="form-label">비밀번호</label>
		       <input type="password" class="form-control" id="exampleInputPassword1" name="pw">
		     </div>
		     <div class="mb-3 form-check">
		       <input type="checkbox" class="form-check-input" id="exampleCheck1" name="idMemory">
		       <label class="form-check-label" for="exampleCheck1">아이디 기억하기</label>
		     </div>
		     <button type="submit" class="btn btn-primary">로그인</button>
		     <a href="/member/searchId" class="btn btn-primary">아이디찾기</a>
		     <a href="/member/searchPw" class="btn btn-primary">비밀번호찾기</a>
		   </div>
	    </form>
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

	</body>
</html>