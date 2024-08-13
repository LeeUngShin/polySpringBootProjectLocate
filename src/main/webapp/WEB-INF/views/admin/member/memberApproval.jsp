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
  <title>Document</title>
</head>
<body>
  <%@include file = "../adminHeader.jsp" %>

  <section id="adminSection">
    <%@include file = "../adminSidebar.jsp" %>
    <article id="article02">
      <div>
        <nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark" data-bs-theme="dark" id="adminNav">
          <div class="container-fluid">
            <a class="navbar-brand">회원관리메뉴</a>

            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="/admin/">회원승인</a>
                <a class="nav-link" href="#">회원삭제</a>
              </div>
            </div>
          </div>
        </nav>
      </div>
      <div id="mainArticle">

      </div>
    </article>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>