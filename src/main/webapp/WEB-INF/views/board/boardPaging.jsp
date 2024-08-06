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
				      <th scope="col" width=200px>글번호
				      <!--
				        <c:if test="${sortStd==desc}">
				            <a href="/board/page?page=1&sort=numDesc"><i class="bi bi-arrow-down-circle"></i></a>
				        </c:if>
				        <c:if test="${sortStd==asc}">
				            <a href="/board/page?page=1&sort=numAsc"><i class="bi bi-arrow-up-circle"></i></a>
				        </c:if>
				        -->
				      </th>
				      <th scope="col" width=500px>제목</th>
					  <th scope="col" width=200px>작성자</th>
				      <th scope="col" width=200px>작성일자</th>
				    </tr>
				  </thead>
				  <tbody class="table-group-divider">
                    <c:forEach var="board" items="${noticeBoardList}">
                        <tr>
                            <th scope="row">${board.num}</th>
                            <td><i class="bi bi-bell"></i>
                                <a href="/board/detail/${board.num}?page=${currentPage}" id="board_detail_view" class="boardTitle">${board.title}</a></td>
                            <td>${board.writer}</td>
                            <td>${board.regTime}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty keyword}">
                        <c:forEach var="board" items="${boardList.content}">
                            <tr>
                              <th scope="row">${board.num}</th>
                              <c:choose>
                                  <c:when test="${board.secret=='Y'}">
                                    <td><a href="/board/detail/${board.num}?page=${currentPage}" id="board_detail_view" class="boardTitle"><i class="bi bi-key"></i> 비밀글입니다.</a></td>
                                  </c:when>
                                  <c:otherwise>
                                    <td><a href="/board/detail/${board.num}?page=${currentPage}" id="board_detail_view" class="boardTitle">${board.title}</a></td>
                                  </c:otherwise>
                              </c:choose>
                              <td>${board.writer}</td>
                              <td>${board.regTime}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty keyword}">
                        <c:forEach var="board" items="${searchBoardList.content}">
                             <tr>
                                <th scope="row">${board.num}</th>
                                <td><a href="/board/detail/${board.num}?page=${currentPage}" id="board_detail_view" class="boardTitle">${board.title}</a></td>
                                <td>${board.writer}</td>
                                <td>${board.regTime}</td>
                             </tr>
                        </c:forEach>
                    </c:if>
				  </tbody>
				</table>
				
				<div id="pageNum">
					<div style="flex-grow: 1;padding-left: 150px;">
					    <c:if test="${empty keyword}">
					    <a href="/board/page?page=1">처음</a>
                        <c:choose>
                           <c:when test="${boardList.isFirst()}">  <!--첫페이지이면 전페이지가 없음-->
                            이전
                           </c:when>
                            <c:otherwise>
                                <a href="/board/page?page=${currentPage-1}">이전</a>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="${startPage}" end="${endPage}" var="count">
                            <c:choose>
                                <c:when test = "${count != currentPage}">
                                    <a href="/board/page?page=${count}">${count}</a>
                                </c:when>
                                <c:otherwise>
                                    ${count}
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${boardList.isLast()}">
                                다음
                            </c:when>
                            <c:otherwise>
                                <a href="/board/page?page=${currentPage+1}">다음</a>
                            </c:otherwise>
                        </c:choose>
                        <a href="/board/page?page=${boardList.totalPages}">마지막</a>
                        </c:if>

               		    <c:if test="${not empty keyword}">
               			    <a href="/board/search?page=1&category=${category}&keyword=${keyword}">s처음</a>
                                <c:choose>
                                    <c:when test="${searchBoardList.isFirst()}">  <!--첫페이지이면 전페이지가 없음-->
                                        s이전
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/board/search?page=${currentPage-1}&category=${category}&keyword=${keyword}">s이전</a>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach begin="${startPage}" end="${endPage}" var="count">
                                    <c:choose>
                                        <c:when test = "${count != currentPage}">
                                            <a href="/board/search?page=${count}&category=${category}&keyword=${keyword}">${count}</a>
                                        </c:when>
                                        <c:otherwise>
                                            ${count}
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${searchBoardList.isLast()}">
                                        s다음
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/board/search?page=${currentPage+1}&category=${category}&keyword=${keyword}">s다음</a>
                                    </c:otherwise>
                                </c:choose>
                                   <a href="/board/search?page=${searchBoardList.totalPages}&category=${category}&keyword=${keyword}">s마지막</a>
                           </c:if>
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
				<div id = "searchInput">
					<form action="/board/search">
						<input type="hidden" name="page" value="1">
						<select name="category">
						    <option value="title">제목</option>
						    <option value="content">내용</option>
						    <option value="writer">작성자</option>
						<input type="text" name="keyword">
						<input type="submit" value="검색">
					</form>
				</div>
			</div>
			<div id="Test">hi</div>
			
			<%@ include file = "../footer.jsp" %>
		</div>
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>