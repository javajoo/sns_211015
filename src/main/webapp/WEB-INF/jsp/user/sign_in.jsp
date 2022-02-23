<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
    
<form id="loginIdForm" method="post" action="/user/sign_in">

<section class="d-flex">

	<section class="log-in-image col-6 d-flex justify-content-end mt-5">
		<div><img src="/images/instagram.png" width=400 height=400 alt="image"></div>
	</section>
	
	<section class="col-6 d-flex justify-content-center align-items-center">
		<div>
			<div class="d-flex justify-content-center mb-5">
				<div class="display-4 font-weight-bold"><div id="logo">In★gram</div></div>
			</div>
				
			<div class="d-flex justify-content-center mb-3">
				<div><input type="text" id="loginId" name="loginId" class="form-control" placeholder="아이디"></div>
			</div>
	
			<div class="d-flex justify-content-center mb-3">
				<div><input type="password" id="password" name="password" class="form-control" placeholder="비밀번호"></div>
			</div>
		
			<div class="d-flex justify-content-center mb-3">
				<button type="submit" id="signInBtn" class="btn-info form-control col-12">로그인</button>
			</div>
	
			<div class="d-flex justify-content-center mb-3">
				<a class="btn btn-secondary form-control col-12" href="/user/sign_up_view" >회원가입</a>
			</div>
		</div>
	</section>
	
</section>
</form>

<!--form태그 사용시 3종 세트: form태그 , name, 버튼 타입:submit  -->
<script>
	$(document).ready(function(){
		//alert("click");
		$('#loginIdForm').on('submit',function(e) {
			//alert("click");
			e.preventDefault();
			
			var loginId = $('#loginId').val().trim();
			
			if (loginId == '') {
				alert("아이디를 입력해주세요");
				return false;
			}
			var password = $('#password').val();
			if (password == '') {
				alert("비밀번호를 입력해주세요");
				return false;
			}
			
			// ajax 호출
			
			var url = $(this).attr('action');
			var params = $(this).serialize();
			
		 	$.post(url,params)
			.done(function(data) {
				 if (data.result == 'success') {
					alert(loginId + "님 환영합니다!!!");
					 location.href = "/timeline/timeline_list_view"; 
				} else {
					alert(data.errorMessage);
				} 
			});
			
		});
		
		
	});
</script>
