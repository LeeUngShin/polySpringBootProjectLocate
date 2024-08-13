<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark" data-bs-theme="dark" id="adminNav">
          <div class="container-fluid">
            <a class="navbar-brand">회원관리메뉴</a>

            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="/admin/memberApproval?page=1">
                    회원승인
                </a>
                <a class="nav-link active" aria-current="page" href="/admin/memberList?page=1">회원목록조회</a>
              </div>
            </div>
          </div>
        </nav>