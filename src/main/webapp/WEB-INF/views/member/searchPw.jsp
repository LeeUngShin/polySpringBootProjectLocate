<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href = "/css/project01.css">
	</head>
	<body>
		<div id="loginform">
			<form action="/member/searchPw" method="POST">

			<div class="mb-3">
			  <label for="exampleInputId1" class="form-label">아이디</label>
			  <input type="text" class="form-control" id="exampleInputId" name="id">
			</div>
				<div>
			    <button type="submit" class="btn btn-primary">비밀번호 찾기</button>
			</form>
			<form action="/member/login" method="GET" style="display : inline-block">
				<button type="submit" class="btn btn-primary">
					로그인하러 가기</button>
			</form>
			<div>
		</div>
		<c:if test="${not empty searchPw}">
			<script>
				alert("랜덤 비밀번호: ${searchPw}");
			</script>

		</c:if>
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>