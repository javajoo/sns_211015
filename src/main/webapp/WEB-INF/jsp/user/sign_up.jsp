<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div class="d-flex justify-content-center">
		<div>
			<div class="mb-3"><img src="/images/image.png" alt="image" width="100px" height="100px"></div>
	
	<div class="d-flex">
		<input type="text" id="loginId" name="loginId" class="form-control" placeholder="아이디">
		<div class="pl-3"><button type="button" id="loginIdCheckBtn" class="form-control btn-success col-4">중복확인</button></div>
	</div>
	
	<div id="idCheckLength" class="small text-primary font-weight-bold d-none">ID를 4자 이상 입력 해주세요.</div>
	<div id="isCheck" class="small text-primary font-weight-bold d-none">사용 가능한 아이디 입니다.</div>
	<div id="isCheckDupliceted" class="small text-danger font-weight-bold d-none">중복된 아이디 입니다.</div>
	
	<div class="d-flex mt-3">
		<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호">
	</div>
	
	<div class="d-flex mt-3">
		<input type="text" id="name" name="name" class="form-control" placeholder="이름">
	</div>
	
	<div class="d-flex mt-3">
		<input type="text" id="email" name="email" class="form-control" placeholder="이메일">
	</div>
	
	<button type="button" id="signUpBtn" class="form-control btn-info mt-3">회원가입</button>
	
		</div>
	</div>
	
	<script>
	$(document).ready(function () {
		//alert('click');
		$('#signUpBtn').on('click',function(e) {
			//alert('click');
			
			// validation check
			
			var loginId = $('#loginId').val().trim();
			if (loginId == '') {
				alert('아이디를 입력하세요');
				return;
			}
			
			var password = $('#password').val().trim();
			if (password == '') {
				alert('비밀번호를 입력하세요');
				return;
			}
			var name = $('#name').val().trim();
			if (name == '') {
				alert('이름를 입력하세요');
				return;
			}
			var email = $('#email').val().trim();
			if (email == '') {
				alert('이메일를 입력하세요');
				return;
			}
		});
		
		$()
	});
</script>