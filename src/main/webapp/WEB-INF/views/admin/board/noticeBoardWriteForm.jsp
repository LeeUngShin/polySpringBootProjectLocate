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
          <div style="text-align : center">
            <h1 style="padding-top : 50px">공지글 작성</h1>
          </div>
            <div id="noticeBoardRegisterDiv">
                <form action="/admin/noticeBoardWrite" method="POST" enctype="multipart/form-data">
                    <div class="mb-3">
                      <label for="formGroupExampleInput" class="form-label">제목</label>
                      <input type="text" class="form-control" id="formGroupExampleInput" name="title">
                    </div>
                    <div class="mb-3">
                      <label for="formGroupExampleInput2" class="form-label">내용</label>
                      <textarea id="formGroupExampleInput2" class="form-control" rows="10" cols="118" style="resize:none" name="content"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="formGroupExampleInput2" class="form-label">파일</label>
                        <input type="file" class="form-control" id="formGroupExampleInput" name="boardFile">
                    </div>
                    <div class="mb-3">
                        <input type=checkbox name=notice value="Y"> 게시판상단고정
                    </div>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <button type="submit" class="btn btn-outline-secondary" id="writeButton">
                        <i class="bi bi-pencil"></i>
                        글쓰기
                    </button>
                    </div>
                </form>
            </div>
            </div>
      </div>

    </article>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>