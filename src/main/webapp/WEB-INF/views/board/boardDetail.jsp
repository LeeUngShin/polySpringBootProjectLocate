<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
		<link rel="stylesheet" href = "/css/project01.css">

	</head>
	<body onload="getCommentList(${boardDto.num})">
        <div id="container">
            <%@include file = "../header.jsp" %>
            <%@include file = "../nav.jsp" %>
            <hr>
            <div class="section"">
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
                        <td colspan="6">
                            <div><img src="/upload/board/${boardDto.storedFileName}" alt=""></div>
                            <div id="boardContent">${contentEnter}</div>
                        </td>
                    </tr>
                    <!--
                    <tr>
                        <th>이미지</th>
                        <td colspan="6"><img src="/upload/board/${boardDto.storedFileName}" alt=""></td>
                    </tr>
                    -->
                </table>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end boardButton">
                        <form action="/board/board" method="GET">
                            <input type="hidden" name="board" value="plain">
                            <input type="hidden" name="page" value="${currentPage}">
                            <button type="submit" class="btn btn-outline-secondary" id="writeFormButton">
                                <i class="bi bi-pencil"></i>
                                목록으로
                            </button>
                        </form>
                    <c:if test="${sessionScope.loginId == boardDto.writer}">
                        <form action="/board/modify/${boardDto.num}">
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
                <div>
                    <label for="exampleFormControlInput1" class="form-label" id="comment">Comment</label>
                </div>
                <div class="mb-3" id="commentDiv">
                    <div>
                        <input type="text" class="form-control" id="commentInput">
                    </div>
                    <div>
                        <button class="btn btn-outline-secondary" id="writeFormButton" onclick="commentAdd(${boardDto.num})">
                            <i class="bi bi-pencil"></i>
                            댓글작성
                        </button>
                    </div>
                </div>
            <div id="commentDiv">
                <table id="commentTable">
                </table>
            </div>

            <%@include file = "../footer.jsp" %>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script>

            var boardWriter = '${boardDto.writer}';
            var loginId = '<%= (session.getAttribute("loginId") != null ? (String)session.getAttribute("loginId") : "") %>';

            function deleteBoard(){
                var confirmData = confirm("정말 삭제하시겠습니까?");
                var deleteButtonForm = document.getElementById("deleteButtonForm");
                deleteButtonForm.innerHTML +=
                    "<input type='hidden' name='confirmData' value='" + confirmData +
                    "'>"
                deleteButtonForm.submit();
            }


            function commentAdd(boardNum){
                var commentContent = document.getElementById("commentInput").value;
                const payload = new FormData();
                payload.append("commentContent", commentContent);
                payload.append("boardNum", boardNum);
                fetch("http://127.0.0.1:8080/comment/commentAdd",{
                    method : "POST",
                    body : payload,
                })
                .then((response) => response.json())
                .then((data) => {
                    if(data.result == "fail"){  // json으로 변환한 데이터.키 = 값
                            alert("댓글 입력 실패");
                    }
                    else{
                        alert("댓글입력 성공");
                        document.getElementById("commentInput").value="";
                        getCommentList(boardNum);
                    }
                })
                .catch((error) => {
                    console.log(error)
                });
            }

            function getCommentList(boardNum){
                fetch("http://127.0.0.1:8080/comment/commentList?boardNum="+boardNum)
                    .then((response) => response.json())
                    .then((data) => {
                        document.getElementById("commentTable").innerHTML = "";

                        for(index=0; index < data.length; index++){

                            var commentDelButton = "";  // 삭제버튼
                            if(data[index].commentWriter == loginId){
                                commentDelButton = "<a href = 'javascript:commentDel(" + data[index].num + ")' id='commentDelButton_" + data[index].num + "'>" +
                                "<i class='bi bi-trash'>" + "</i>" +
                                "</a>";
                            }

                            var commentModifyButton = "";  // 수정버튼
                            if(data[index].commentWriter == loginId){
                                commentModifyButton = "<a href = 'javascript:commentModify(" + data[index].num + ")' id='commentModifyButton_" + data[index].num + "'>"+
                                "<i class='bi bi-pencil'>" + "</i>" +
                                "</a>";
                            }

                            var commentModifyCompleteButton="";  // 수정완료버튼
                            if(data[index].commentWriter == loginId){
                                commentModifyCompleteButton = "<a href = 'javascript:commentCompleteModify(" + data[index].num+"," +  boardNum + ")' id='commentModifyCompleteButton_" + data[index].num + "' style='display:none'>" +
                                "<i class='bi bi-pencil-fill'>" + "</i>" +
                                "</a>";
                            }

                            // 수정 입력 폼
                            var commentModifyForm = "<input class = 'commentContent' type='text' value='" + data[index].commentContent + "' name='commentModifyForm' style='display: none;' id='modifyFormInput_" + data[index].num + "'>";

                            document.getElementById("commentTable").innerHTML +=
                                        "		<tr>" +
                                        "			<td>" + data[index].commentWriter + "</td>" +
                                        "			<td class = 'commentContent' id='commentContentTd_" + data[index].num+"'>" + data[index].commentContent + "</td>" +
                                        "			<td class = 'commentContent' id='commentModifyFormTd_" + data[index].num + "'style='display:none'>" + commentModifyForm  + "</td>" +
                                        "			<td class = 'commentRight' id='commentDelButtonTd_" + data[index].num+"'>"  + commentDelButton + "</td>" +
                                        "			<td class = 'commentRight commentButton' id='commentModifyButtonTd_" + data[index].num+"'>" + commentModifyButton + "</td>" +
                                        "			<td class = 'commentRight commentButton' id='commentModifyCompleteButtonTd_" + data[index].num+"'style='display:none'>" + commentModifyCompleteButton  + "</td>" +
                                        "           <td class = 'commentRight commentDate'>" + data[index].commentCreateTime + "</td>" +
                                        "		</tr>";
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            }

            function commentDel(commentNum){
				if(confirm("정말로 삭제하겠습니까")){
                    fetch("http://127.0.0.1:8080/comment/commentDel?commentNum="+commentNum,{
                        method : "PUT",
                    })
                        .then((response) => response.json())
                        .then((data) =>{
                            if(data.result == "fail"){  // json으로 변환한 데이터.키 = 값
                                alert("댓글 삭제 실패");
                            }
                            else{
                                alert("댓글 삭제 성공");
                                getCommentList(${boardDto.num});
                            }
                        })
                        .catch((error) => {
                             console.log(error)
                        });
                }
            }

            function commentModify(commentNum){

                // 댓글 내용 Td
                var commentContentTd = document.getElementById("commentContentTd_" + commentNum);
                commentContentTd.style.display = "none";

                // 댓글 수정 입력 폼 Td
                var commentModifyFormTd = document.getElementById("commentModifyFormTd_" + commentNum);
                commentModifyFormTd.style.display = "table-cell";

                // 댓글 수정 입력 폼 input
                var modifyFormInput = document.getElementById("modifyFormInput_" + commentNum);
                modifyFormInput.style.display = "table-cell"

                // 수정 버튼 Td
                var commentModifyButtonTd = document.getElementById("commentModifyButtonTd_" + commentNum);
                commentModifyButtonTd.style.display = "none";

                // 수정 a태그
                var commentModifyButton = document.getElementById("commentModifyButton_" + commentNum);
                commentModifyButton.style.display = "none";

                // 수정완료 버튼 Td
                var commentModifyCompleteButtonTd = document.getElementById("commentModifyCompleteButtonTd_" + commentNum);
                commentModifyCompleteButtonTd.style.display = "table-cell";

                // 수정완료 버튼 a 태그
                var commentModifyCompleteButton = document.getElementById("commentModifyCompleteButton_" + commentNum);
                commentModifyCompleteButton.style.display = "table-cell";

                // 삭제버튼 Td
                //var commentDelButtonTd = document.getElementById("commentDelButtonTd_" + commentNum);
                //commentDelButtonTd.style.display = "none";

                // 삭제버튼 a 태그
                //var commentDelButton = document.getElementById("commentDelButton_" + commentNum);
                //commentDelButton.style.display = "none";
            }

        function commentCompleteModify(commentNum, boardNum){

            var modifyContent = document.getElementById("modifyFormInput_" + commentNum).value;
            const payload = new FormData();
            payload.append("commentContent", modifyContent);
            fetch("http://127.0.0.1:8080/comment/commentModify?commentNum=" + commentNum,{
                method : "PUT",
                body : payload,
            })
            .then((response) =>{
                document.getElementById("modifyFormInput_" + commentNum).value="";
                alert("수정한 댓글 내용 : " + modifyContent);
                getCommentList(boardNum);

            })
            .catch((error) => {
                console.log(error);
            });

        }
        </script>

</body>
</html>