<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <link rel="stylesheet" href="/css/admin.css">
  <script src="/js/admin.js"></script>
  <title>Document</title>
</head>
<body>
  <%@include file = "../adminHeader.jsp" %>

  <section id="adminSection">
    <%@include file = "../adminSidebar.jsp" %>
    <article id="article02">


      <div id="mainArticle">
        <%@include file="./boardNav.jsp" %>
        <div id="adminListDiv">
          <table class="table table-striped table-hover">
            <thead class="table-dark">
                 <tr>
                     <th scope="col" width=200px>글번호</th>
                     <th scope="col" width=500px>제목</th>
                     <th scope="col" width=200px>작성자</th>
                     <th scope="col" width=200px>작성일자</th>
                 </tr>
            </thead>
          <c:forEach items="${boardDtoPage.content}" var="board">
            <tbody>
              <tr>
                <th scope="row">${board.num}</th>
                <td>${board.title}</td>
                <td>${board.writer}</td>
                <td>${board.regTime}</td>
              </tr>
            </tbody>
          </c:forEach>
        </div>
          </table>
          <div id="adminPagingNum">
            <c:choose>
                 <c:when test="${boardDtoPage.isFirst()}">
                    처음
                 </c:when>
                 <c:otherwise>
                   <a href="/admin/boardList?page=1">처음</a>
                 </c:otherwise>
            </c:choose>
            <c:choose>
                 <c:when test="${currentPage<=blockLimit}">
                    이전
                 </c:when>
                 <c:otherwise>
                   <a href="/admin/boardList?page=${startPage-1}">이전</a>
                 </c:otherwise>
            </c:choose>
            <c:forEach begin="${startPage}" end="${endPage}" var="count">
              <c:choose>
                <c:when test="${currentPage==count}">
                  ${currentPage}
                </c:when>
                <c:otherwise>
                  <a href="/admin/boardList?page=${count}">${count}</a>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:choose>
               <c:when test="${lastPageSet==true}">
                  다음
               </c:when>
               <c:otherwise>
                 <a href="/admin/boardList?page=${endPage+1}">다음</a>
               </c:otherwise>
            </c:choose>
            <c:choose>
               <c:when test="${boardDtoPage.isLast()}">
                  마지막
               </c:when>
               <c:otherwise>
                 <a href="/admin/boardList?page=${totalLastPage}">마지막</a>
               </c:otherwise>
            </c:choose>
          </div>
      </div>
    </article>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>