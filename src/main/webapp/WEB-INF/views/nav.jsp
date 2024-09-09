<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg">
    <div class="itemCategory">
        <i class="bi bi-justify"></i>
    </div>

    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        상품안내
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/goods/menu?topCategory=all&subCategory=all&page=1">전체</a></li>
                        <li><a class="dropdown-item" href="/goods/menu?topCategory=빵&subCategory=all&page=1">빵</a></li>
                        <li><a class="dropdown-item" href="/goods/menu?topCategory=케이크&subCategory=all&page=1">케이크</a></li>
                        <li><a class="dropdown-item" href="/goods/menu?topCategory=디저트&subCategory=all&page=1">디저트</a></li>
                        <li><a class="dropdown-item" href="/goods/menu?topCategory=음료&subCategory=all&page=1">음료</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/board/board?board=plain&page=1">자유게시판</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        내정보
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/member/modify">내정보수정</a></li>
                        <li><a class="dropdown-item" href="/member/myBoard?page=1">내게시글 보기</a></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        고객센터
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="board?board=notice&page=1">공지사항</a></li>
                        <li><a class="dropdown-item" href="#">자주묻는 질문</a></li>
                        <li><a class="dropdown-item" href="#">1대1 문의</a></li>
                    </ul>
                </li>
            </ul>
           <div id="itemBucketDiv">
                <a class="itemBucket" href="/my/orderList?page=1">
                    <i class="bi bi-person-fill"></i>
                    내 주문
                </a>
                <a class="itemBucket" href="#">
                    <i class="bi bi-cart"></i>
                    장바구니
                </a>
            </div>
        </div>
    </div>
</nav>
