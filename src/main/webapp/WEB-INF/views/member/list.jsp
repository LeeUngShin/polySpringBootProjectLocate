<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href = "/css/project01.css">
		<style>
			table, th, td{
				border : 1px solid black;
				border-collapse: collapse;
			}
		</style>
	</head>
	<body>
		
		<table border=1 >
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>주소</th>
				<th>나이</th>
				<th>이메일</th>
				<th>삭제</th>
				<th>조회</th>
			</tr>
			<c:if test="${not empty memberList}">
			<c:forEach items="${memberList}" var="mem">
			<tr>
				<td>${mem.id}</td>
				<td>${mem.name}</td>
				<td>${mem.addr}</td>
				<td>${mem.age}</td>
				<td>${mem.email}</td>
				<td><a href="/member/delete/${mem.id}">삭제</td>
				<td><a href="/member/detail/${mem.id}">조회</td>
			</tr>
			</c:forEach>
			</c:if>
		</table>		

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous">
		</script>
		</body>
</html>
