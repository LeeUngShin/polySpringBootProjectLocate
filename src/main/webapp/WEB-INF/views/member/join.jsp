<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href = "/css/project01.css">
	</head>
	<body>
	    <div class="middleLogo">
            <a href="/home">
                <img src="../img/logo.PNG">
            </a>
        </div>
		<form action="/member/join" method="post" id="registerForm">
		  <div id="registerform">
		    <div class="mb-3">
		      <label for="exampleInputId1" class="form-label">아이디</label>
		      <input type="text" class="form-control" id="exampleInputId1"name="id" value="${joinDto.id}" required>
		      <c:if test="${not empty errorMsg['id']}">
              	<span class="error">${errorMsg['id']}</span><br>
              </c:if>
		    </div>
		    <div class="mb-3">
		      <label for="exampleInputPassword1" class="form-label">비밀번호</label>
		      <input type="password" class="form-control" id="exampleInputPassword1" name ="pw" required>
		    </div>
		    <div class="mb-3">
                 <label for="exampleInputPassword2" class="form-label">비밀번호</label>
                 <input type="password" class="form-control" id="exampleInputPassword2" name ="pwCheck" required>
            </div>
            <c:if test="${not empty errorMsg['pwCheck']}">
                <span class="error">${errorMsg['pwCheck']}</span><br>
            </c:if>
		    <div class="mb-3">
		      <label for="exampleInputName1" class="form-label">이름</label>
		      <input type="text" class="form-control" id ="exampleInputName1" name="name" required>
		    </div>
			<div class="mb-3">
				<label class="form-label">주소</label><br>
				<input type="text" class="form-control" id="sample6_postcode" placeholder="우편번호" name="post" style="width:150px; display:inline-block" required>
				<input type="button" class="form-control" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" style="width:150px; display:inline-block">
			</div>
			<div class="mb-3">
				<input type="text" class="form-control" id="sample6_address" name="addr" placeholder="주소" required>
			</div>
			<div class="mb-3">
				<input type="text" class="form-control" id="sample6_detailAddress" name="addrDetail"" placeholder="상세주소" style="width:304px; display:inline-block" required>
				<input type="hidden" class="form-control" id="sample6_extraAddress" placeholder="참고항목"style="width:90px; display:inline-block">
			</div>
	           <div class="mb-3">
                  <label for="exampleInputEmail1" class="form-label">이메일</label>
                  <input type="text" class="form-control" id="exampleInputEmail1" name="email" required>
               </div>

             <!--
            <div style="display: flex;">
               <div class="mb-3" style="flex-basis: 300px; flex-grow: 1;">
                  <input type="text" class="form-control" id="exampleInputEmail1" name="email" required>
               </div>
               <div class="input-group-text" style="flex-basis: 50px; margin-bottom: 16px;">@</div>
               <div class="col-12" style="flex-basis: 170px;">
                 <label class="visually-hidden" for="inlineFormSelectPref">Preference</label>
                 <select class="form-select" id="inlineFormSelectPref" name="email2">
                    <option selected>이메일을 선택하세요</option>
                    <option value="@naver.com">naver.com</option>
                    <option value="@daum.net">daum.net</option>
                    <option value="@google.com">google.com</option>
                 </select>
               </div>
            </div>
            -->
		    <button type="submit" class="btn btn-primary">회원가입</button>
		</div>
		</form>


		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous">
		</script>
		<script>
		  document.addEventListener('DOMContentLoaded', () => {
		    const form = document.getElementById('registerForm');
		    const passwordInput = document.getElementById('exampleInputPassword1');

		    form.addEventListener('submit', (event) => {
		      const password = passwordInput.value;
		      const regex = /[A-Z]/;

		      if (!regex.test(password)) {
		        event.preventDefault();
		        alert('비밀번호에는 최소한 한 개의 대문자가 포함되어야 합니다.');
		      }
		    });
		  });
		</script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
		    function sample6_execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 주소 변수
		                var extraAddr = ''; // 참고항목 변수

		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }

		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
		                    // 조합된 참고항목을 해당 필드에 넣는다.
		                    //document.getElementById("sample6_extraAddress").value = extraAddr;
		                
		                } else {
		                    document.getElementById("sample6_extraAddress").value = '';
		                }

		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('sample6_postcode').value = data.zonecode;
		                document.getElementById("sample6_address").value = addr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("sample6_detailAddress").focus();
		            }
		        }).open();
		    }
		</script>
		
		</body>
</html>