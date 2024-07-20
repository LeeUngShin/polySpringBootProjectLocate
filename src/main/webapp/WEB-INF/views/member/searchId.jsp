<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href = "../../../resources/css/project.css">
	</head>
	<body>
		<div id="loginform">
		<form action="/member/searchId" method="POST">
		  <div id="loginform">
		    <div class="mb-3">
		      <label for="exampleInputName1" class="form-label">이름</label>
		      <input type="text" class="form-control" id="exampleInputName" name="name">
		    </div>
		    <div class="mb-3">
		      <label for="exampleInputEmail1" class="form-label">이메일</label>
		      <input type="email" class="form-control" id="exampleInputEmail1" name="email">
		    </div>
		    <button type="submit" class="btn btn-primary" >아이디 찾기</button>
		</form>
		<form action="/member/login" method="GET" style="display : inline-block">
			<button type="submit" class="btn btn-primary">
				로그인하러 가기</button>
		</form>
		</div>

		<c:if test="${not empty searchId}">
		    <script>
		        alert("찾은 아이디: ${searchId}");
		    </script>
		</c:if>
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>


	</body>
</html>