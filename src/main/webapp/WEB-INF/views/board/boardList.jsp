<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
		<link rel="stylesheet" href = "/css/project01.css">
	</head>
	<body>
		<div id="container">
			<%@include file = "../header.jsp" %>
			<%@include file = "../nav.jsp" %>
			<div class="section "> 
				<table class="table table-striped table-hover" >
				  <thead class="table-dark">
				    <tr>
				      <th scope="col" width=200px>글번a호</th>
				      <th scope="col" width=500px>제목</th>
					  <th scope="col" width=200px>작성자</th>
				      <th scope="col" idth=200px>작성일자</th>
				    </tr>
				  </thead>
				  <tbody class="table-group-divider">
					<c:forEach var="board" items="${paging.content}">
				    <tr>
				      <th scope="row">${board.num}</th>
				      <td><a href="/board/detail/${board.num}/${currentPage}" id="board_detail_view" class="boardTitle">${board.title}</a></td>
				      <td>${board.member.id}</td>
					  <td>${board.regDate}</td>
				    </tr>
					</c:forEach>
				  </tbody>
				</table>
				
				<div id="pageNum">
					<div style="flex-grow: 1;padding-left: 150px;">
					<c:choose>
						<c:when test="${paging.isFirst()}">
						이전
						</c:when>
						<c:otherwise>
							<a href="/board/list?page=${currentPage-1}">이전</a>
						</c:otherwise>
					</c:choose>

					<c:forEach begin="1" end="${totalPage}" var="count">
						<c:choose>
							<c:when test = "${count != currentPage}">
								<c:if test = "${(currentPage-2 <= count && count <= currentPage+2)}">
									<a href="/board/list?page=${count}">${count}</a>
								</c:if>
							</c:when>
							<c:otherwise>
								${count}
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<c:choose>
						<c:when test="${paging.isLast()}">
							다음
						</c:when>
						<c:otherwise>
							<a href="/board/list?page=${currentPage+1}">다음</a>
						</c:otherwise>
					</c:choose>
					</div>
	
					<form action="/board/write">
						<div class="d-grid gap-2 d-md-flex justify-content-md-end boardButton">
							<button type=submit" class="btn btn-outline-secondary" id="writeFormButton">
							<i class="bi bi-pencil"></i>
							글쓰기
							</button>
						</div>
					</form>
				</div>
				<div class = "serachInput">
					<form action="/board/search?page=1">
						<input type="hidden" name="page" value="1">
						<input type="text" name="keyword">
						<input type="submit" value="검색">
					</form>
				</div>
			</div>
			
			<%@ include file = "../footer.jsp" %>
		</div>
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>