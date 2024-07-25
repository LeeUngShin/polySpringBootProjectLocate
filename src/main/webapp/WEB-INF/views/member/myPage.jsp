<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href = "/css/project01.css">
	</head>
	<body>

		<form action="/member/modify" method="POST" id="registerForm">
			<div id="registerform">
			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">아이디</label>
			    <input type="text" class="form-control" id="exampleInputEmail1" name="id" value=${member.id} readonly required>
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputPassword1" class="form-label">비밀번호</label>
			    <input type="password" class="form-control" id="exampleInputPassword1" name ="pw">
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputName1" class="form-label">이름</label>
			    <input type="text" class="form-control" id ="exampleInputName1" name="name" value=${member.name} required>
			  </div>
			<div class="mb-3">
				      <label for="exampleInputAddr1" class="form-label">주소</label>
				      <input type="text" class="form-control" id ="exampleInputAddr1" name="addr" value=${member.addr} required>
			</div>

			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">이메일</label>
			    <input type="email" class="form-control" id ="exampleInputEmail1" name=email value=${member.email} required>
			  </div>		    
		    <button type="submit" class="btn btn-primary">정보수정</button>
		</div>
		</form>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous">
		</script>
		<script>
		  document.addEventListener('DOMContentLoaded', () => {
		    const form = document.getElementById('registerForm');
		    const passwordInput = document.getElementById('exampleInputPassword1');

		    form.addEventListener('submit', (event) => {
		      const password = passwordInput.value;
		      const regex = /[A-Z]/;

		      if (!regex.test(password)) {
		        event.preventDefault();
		        alert('비밀번호에는 최소한 한 개의 대문자가 포함되어야 합니다.');
		      }
		    });
		  });
		</script>
	</body>
</html>