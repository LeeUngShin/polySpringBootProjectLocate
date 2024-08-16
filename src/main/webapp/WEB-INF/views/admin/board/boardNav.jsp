<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark" data-bs-theme="dark" id="adminNav">
          <div class="container-fluid">
            <a class="navbar-brand">게시글관리메뉴</a>

            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="/admin/noticeBoardForm">
                    공지글 작성
                </a>
                <a class="nav-link active" aria-current="page" href="/admin/boardList">
                    게시글 조회
                </a>
              </div>
            </div>
          </div>
        </nav>