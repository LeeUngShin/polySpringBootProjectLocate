$<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <html lang="ko">
 <head>
 <meta charset="UTF-8">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
 <link rel="stylesheet" href = "/css/project01.css">
 <link rel="stylesheet" href = "/css/menu.css">
 </head>
 <body>
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/home">Home</a></li>
        <li class="breadcrumb-item"><a href="/goods/menu?topCategory=all&subCategory=all&page=1">상품안내</a></li>
        <li class="breadcrumb-item"><a href="/goods/menu?topCategory=${topCategory}&subCategory=all&page=1">${topCategory}</a></li>
        <li class="breadcrumb-item"><a href="/goods/menu?topCategory=${topCategory}&subCategory=${subCategory}&page=1">${subCategory}</a></li>
        <li class="breadcrumb-item active" aria-current="page">Data</li>
      </ol>
    </nav>
    ${goodsDto}
    ${page}
    <img src="/upload/goods/${goodsDto.storedGoodsImageName}">

 </body>