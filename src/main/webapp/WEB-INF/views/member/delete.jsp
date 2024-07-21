<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href = "/resources/css/project.css">
	</head>
	<body>

		<form action="/member/delete" method="POST">
			
		  <div id="loginform">
		    <div class="mb-3">
				<input type="hidden" name="id" value="${id}">
				<input type="hidden" name="id" value="${id2}">
		        <label for="exampleInputPassword1" class="form-label">비밀번호</label>
		        <input type="password" class="form-control" id="exampleInputPassword1" name="pw">
		    </div>

		    <button type="submit" class="btn btn-primary">회원탈퇴 완료</button>
		  </div>
		</form>
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>