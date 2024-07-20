<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
		<link rel="stylesheet" href = "../../resources/css/project.css">
		
	</head>
	
	<body>
		<div id="container">
		<%@include file = "../header.jsp" %>
		<%@include file = "../nav.jsp" %>
		<div class="section" id="boardWriteForm">
			<h2>게시글 수정</h2>
			<hr>
			<form action="/board/modify/${boardDto.num}" method="POST">
				<div class="mb-3">
				  <label for="formGroupExampleInput" class="form-label">제목</label>
				  <input type="text" class="form-control" id="formGroupExampleInput"style="width:920px" name="title" value=${boardDto.title}>
				</div>
				<div class="mb-3">
				  <label for="formGroupExampleInput2" class="form-label">내용</label>
				  <textarea id="formGroupExampleInput2" name="content" class="form-control" rows="10" cols="118" style="resize:none" name="content">
					${boardDto.content}
				  </textarea>
				</div>
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
				<button type=submit" class="btn btn-outline-secondary" id="writeButton">
					<i class="bi bi-pencil"></i>
					글수정하기
				</button>
				</div>
			</form>
		</div>
		
		<%@include file = "../footer.jsp" %>
		</div>
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>