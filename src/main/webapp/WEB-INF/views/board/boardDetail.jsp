<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
		<link rel="stylesheet" href = "/css/project01.css">

	</head>
	<body>
		<div id="container">
			<%@include file = "../header.jsp" %>
			<%@include file = "../nav.jsp" %>
			<div class="section" ">
				<table class="table table-bordered" id="boardDetail">
				  
				    <tr>
					  <th scope="col">글번호</th>
					  <td scope="col">${boardDto.num}</td>	
				      <th scope="col">글작성자</th>
				      <td scope="col">${boardDto.writer}</td>
				      <th scope="col">등록일</th>
				      <td scope="col">${boardDto.regTime}</td>
				    </tr>
				    <tr>
				      <th>제목</th>
				      <td colspan="6">${boardDto.title}</td>
				    </tr>
				    <tr>
				      <th>내용</th>
				      <td colspan="6" style="white-space:pre;" id="boardContent">${boardDto.content}</td>
				    </tr>
				    <tr>
				        <th>이미지</th>
				        <td colspan="6"><img src="/upload/${boardDto.storedFileName}" alt=""></td>
				    </tr>

				</table>

                <div class="d-grid gap-2 d-md-flex justify-content-md-end boardButton">
                    <form action="/board/page" method="GET">
                        <input type="hidden" name="page" value="${currentPage}">
                        <button type="submit" class="btn btn-outline-secondary" id="writeFormButton">
                            <i class="bi bi-pencil"></i>
                            ${currentPage}
                            목록으로
                        </button>
                    </form>
                </div>

				<c:if test="${sessionScope.loginId == boardDto.writer}">
				<div class="d-grid gap-2 d-md-flex justify-content-md-end boardButton">
					<form action="/board/modify/${boardDto.num}">
					<input type=hidden name="boardId" value=${boardDto.num}>
						<button type=submit" class="btn btn-outline-secondary" id="writeFormButton">
							<i class="bi bi-pencil"></i>
							글수정
						</button>
					</form>
					<form action="/board/delete/${boardDto.num}" method="POST", id="deleteButtonForm">
					    <input type="hidden" name="currentPage" value="${currentPage}">
                        <button type="button" class="btn btn-outline-secondary" id="delete" onclick="javascript:deleteBoard()">
                            <i class="bi bi-pencil"></i>
                            글삭제
                        </button>
                    </form>
				</div>
				</c:if>
			</div>


			<%@include file = "../footer.jsp" %>
		</div>
		    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
            <script>
                function deleteBoard(){
                    var confirmData = confirm("정말 삭제하시겠습니까?");
                    var deleteButtonForm = document.getElementById("deleteButtonForm");
                    deleteButtonForm.innerHTML +=
                        "<input type='hidden' name='confirmData' value='" + confirmData +
                        "'>"
                    deleteButtonForm.submit();
                }
            </script>
	</body>
</html>