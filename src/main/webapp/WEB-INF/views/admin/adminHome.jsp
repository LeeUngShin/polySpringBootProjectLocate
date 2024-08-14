<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <link rel="stylesheet" href="/css/admin.css">
  <script src="/js/admin.js"></script>
  <title>Document</title>
</head>
<body onload=checkAdmin()>
  <%@include file = "./adminHeader.jsp" %>

  <section id="adminSection">
    <%@include file = "./adminSidebar.jsp" %>

    <article id="article02">
      <div id="mainArticle">
      </div>
    </article>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <script>
    function checkAdmin(){
        var role = "${role}";
        if(role != "ROLE_ADMIN"){
            alert("관리자 권한만 접근 가능합니다.");
            window.location.href = "127.0.0.1:8080/home"; // 권한이 없으면 리다이렉트
        }
    }
  </script>
</body>
</html>