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
                    <th scope="col" width=200px>글번호</th>
                    <th scope="col" width=500px>제목</th>
                    <th scope="col" width=200px>작성자</th>
                    <th scope="col" width=200px>작성일자</th>
                </tr>
            </thead>
            <tbody class="table-group-divider">
                <c:if test="${empty keyword}">
                    <c:forEach var="myBoard" items="${myBoardList.content}">
                        <tr>
                            <th scope="row">${myBoard.num}</th>
                            <c:choose>
                                <c:when test="${myBoard.secret=='Y'}">
                                    <td><a href="/board/detail/${myBoard.num}?page=${currentPage}" id="board_detail_view" class="boardTitle"><i class="bi bi-key"></i> 비밀글입니다.</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="/board/detail/${myBoard.num}?page=${currentPage}" id="board_detail_view" class="boardTitle">${myBoard.title}</a></td>
                                </c:otherwise>
                            </c:choose>
                            <td>${myBoard.writer}</td>
                            <td>${myBoard.regTime}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${not empty keyword}">
                    <c:forEach var="mySearchBoard" items="${myBoardSearchList.content}">
                        <tr>
                            <th scope="row">${mySearchBoard.num}</th>
                            <c:choose>
                                <c:when test="${mySearchBoard.secret=='Y'}">
                                    <td><a href="/board/detail/${mySearchBoard.num}?page=${currentPage}" id="board_detail_view" class="boardTitle"><i class="bi bi-key"></i> 비밀글입니다.</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="/board/detail/${mySearchBoard.num}?page=${currentPage}" id="board_detail_view" class="boardTitle">${myBoard.title}</a></td>
                                </c:otherwise>
                            </c:choose>
                            <td>${mySearchBoard.writer}</td>
                            <td>${mySearchBoard.regTime}</td>
                        </tr>
                    </c:forEach>
                </c:if>
          </tbody>
        </table>

        <div id="pageNum">
            <c:if test = "${empty keyword}">
                <div style="flex-grow: 1;padding-left: 150px; margin-bottom : 32px">
                        <a href="/member/myBoard?page=1">처음</a>
                    <c:choose>
                       <c:when test="${myBoardList.isFirst()}">  <!--첫페이지이면 전페이지가 없음-->
                          이전
                       </c:when>
                        <c:otherwise>
                            <a href="/member/myBoard?page=${currentPage-1}">이전</a>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="${startPage}" end="${endPage}" var="count">
                        <c:choose>
                            <c:when test = "${count != currentPage}">
                                <a href="/member/myBoard?page=${count}">${count}</a>
                            </c:when>
                            <c:otherwise>
                                ${count}
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${myBoardList.isLast()}">
                            다음
                        </c:when>
                        <c:otherwise>
                            <a href="/member/myBoard?page=${currentPage+1}">다음</a>
                        </c:otherwise>
                    </c:choose>
                    <a href="/member/myBoard?page=${myBoardList.totalPages}">마지막</a>
                </div>
            </c:if>
            <c:if test = "${not empty keyword}">
                <div style="flex-grow: 1;padding-left: 150px; margin-bottom : 32px">
                        <a href="/member/myBoardSearch?page=1">처음</a>
                    <c:choose>
                       <c:when test="${myBoardSearchList.isFirst()}">  <!--첫페이지이면 전페이지가 없음-->
                          이전
                       </c:when>
                        <c:otherwise>
                            <a href="/member/myBoardSearch?page=${startPage-1}">이전</a>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="${startPage}" end="${endPage}" var="count">
                        <c:choose>
                            <c:when test = "${count != currentPage}">
                                <a href="/member/myBoardSearch?page=${count}">${count}</a>
                            </c:when>
                            <c:otherwise>
                                ${count}
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${isLast}">
                            다음
                        </c:when>
                        <c:otherwise>
                            <a href="/member/myBoardSearch?page=${endPage+1}">다음</a>
                        </c:otherwise>
                    </c:choose>
                    <a href="/member/myBoardSearch?page=${myBoardList.totalPages}">마지막</a>
                </div>
            </c:if>
        </div>
        <div id = "searchInput">
            <form action="/board/search">
                <input type="hidden" name="page" value="1">
                <select name="category">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
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