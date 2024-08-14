<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article id="article01">
  <div id="sideBar" class="d-flex flex-column mb-3">
    <div class="p-2">
        관리자메뉴
    </div>
    <hr>
    <div class="p-2">
      <ul class="sideBar">
        <li>
          <a href="#" class="nav-link text-white" onclick="memberMenuToggle()">
          <i class="bi bi-chevron-down"></i>
            회원관리
          </a>
        </li>
        <ul>
          <div id="memberMenu">
            <li>
              <a href="/admin/memberApproval?page=1" class="nav-link text-white" onclick="memberMenuToggle()">
                회원승인
              </a>
            </li>
            <li>
              <a href="/admin/memberList?page=1" class="nav-link text-white" onclick="memberMenuToggle()">
                회원목록조회
              </a>
            </li>
          </div>
        </ul>
      </ul>
    </div>
    <hr>
    <div class="p-2">
      <ul class="sideBar">
        <li>
          <a href="#" class="nav-link text-white" onclick="boardMenuToggle()">
            <i class="bi bi-chevron-down"></i>
            게시판관리
          </a>
        </li>
        <ul>
          <div id="boardMenu">
            <li>
              <a href="#" class="nav-link text-white">
                게시판메뉴1
              </a>
            </li>
            <li>
              <a href="#" class="nav-link text-white">
                게시판메뉴2
              </a>
            </li>
          </div>
        </ul>
      </ul>
    </div>
    <hr>
    <div class="p-2">
      <ul class="sideBar">
        <li>
          <a href="#" class="nav-link text-white"  onclick="goodsMenuToggle()">
            <i class="bi bi-chevron-down"></i>
            상품관리
          </a>
        </li>
        <ul>
          <div id="goodsMenu">
            <li>
              <a href="/admin/goodsRegisterForm" class="nav-link text-white">
                상품등록
              </a>
            </li>
            <li>
              <a href="#" class="nav-link text-white">
                상품조회
              </a>
            </li>
          </div>
        </ul>
      </ul>

    </div>
  </div>
  </ul>
</article>