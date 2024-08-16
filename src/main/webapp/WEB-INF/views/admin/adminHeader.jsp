<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
  <div>
  <a href="/admin/home">
    <img src="/img/home/logo.PNG" alt="로고">
  </a>
  </div>
    <div id="adminButton">
        <div>
            <form action="/member/logout" method="POST" id=logout-form>
                <button type="submit" class="btn btn-main" id="adminLogoutButton">
                    <i class="bi bi-person-fill"></i>
                    로그아웃
                </button>
            </form>
        </div>
        <div>
            <form action="/home" method="GET" id=logout-form>
                <button type="submit" class="btn btn-main" id="adminLogoutButton">
                    <i class="bi bi-person-fill"></i>
                    사용자페이지로 이동
                </button>
            </form>
        </div>
  </div>
</header>