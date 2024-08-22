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
          <i class="bi bi-chevron-down memberMenuUpDownShow" id = "memberMenuDown" ></i>
          <i class="bi bi-chevron-up" id= "memberMenuUp" class="" ></i>
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
            <i class="bi bi-chevron-down boardMenuUpDownShow" id="boardMenuDown"></i>
            <i class="bi bi-chevron-up" id="boardMenuUp"></i>
              게시판관리
          </a>
        </li>
        <ul>
          <div id="boardMenu">
            <li>
              <a href="/admin/noticeBoardForm" class="nav-link text-white">
                공지글 작성
              </a>
            </li>
            <li>
              <a href="/admin/boardList" class="nav-link text-white">
                게시글조회
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
            <i class="bi bi-chevron-down goodsMenuUpDownShow" id="goodsMenuDown"></i>
            <i class="bi bi-chevron-up" id="goodsMenuUp"></i>
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
              <a href="/admin/goodsList" class="nav-link text-white">
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