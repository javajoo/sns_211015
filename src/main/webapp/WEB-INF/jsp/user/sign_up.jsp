<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<form id="signUpForm" method="post" action="/user/sign_up">
	
		<div class=" d-flex justify-content-center">

			<div class="signUp-box border p-3 m-5">
				<div class="text-secondary my-5"><b>친구들의 사진과 동영상을 보려면 가입하세요</b></div>	
		
					<div class="d-flex">
						<input type="text" id="loginId" name="loginId" class="form-control" placeholder="아이디">
						<div class="pl-3"><button type="button" id="loginIdCheckBtn" class="form-control btn-light col-4"><b>중복확인</b></button></div>
					</div>
					
					<!-- 중복확인 눌렀을 때 문구 -->
					<div id="idCheckLength" class="small text-primary font-weight-bold d-none">ID를 4자 이상 입력 해주세요.</div>
					<div id="isCheck" class="small text-success font-weight-bold d-none">사용 가능한 아이디 입니다.</div>
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
					
					<button type="submit" id="signUpBtn" class="form-control btn-primary mt-3 col-2 mb-5">회원가입</button>
					
					<div class="border rounded py-3 text-center login-box-bottom">
						계정이 있으신가요? <a href="/user/sign_in_view">로그인</a>
					</div>
			
			</div>
		</div>
	</form>
	
	<script>
	$(document).ready(function () {
		//alert('click');
		
		// 아이디 중복확인
		$('#loginIdCheckBtn').on('click',function(e) {
			//alert('click');
			var loginId = $('#loginId').val().trim();	
			
			// 상황 문구 안보이게 모두 초기화
			// 조건문 갔다가 다시 초기화 시킨다!!
			$('#idCheckLength').addClass('d-none');
			$('#isCheck').addClass('d-none');
			$('#isCheckDupliceted').addClass('d-none');
		
			
			if (loginId.length < 4) {
				// id가 4자 미만일 때 경고 문구 노출하고 끝낸다.
				$('#idCheckLength').removeClass('d-none');
				return;
			}
			
			
			// ajax - 중복확인(api 호출) 기존에 있는 데이터와 비교해야 해서
			$.ajax({
				type: "get"
				,url: "/user/is_duplicated_id"
				,data: {"loginId": loginId}
				,success: function(data) {
					if (data.result == true) {
						// 중복인 경우 -> 이미 사용중인 아이디
						$('#isCheckDupliceted').removeClass('d-none');
					} else {
						// 중복이 아닌 경우 -> 사용 가능한 아이디
						$('#isCheck').removeClass('d-none');
					}
				}
				,error: function(e) {
					alert("아이디 중복확인에 실패 했습니다. 관리자에게 문의 해주세요.");
				}
			});
			
			
		}); 
		
		// 회원가입 -> 버튼을 눌렀을 때 하거나 form id로(버튼 submit) 2가지 방법 중 선택하면 된다.
		$('#signUpForm').on('submit',function(e) {
			//alert('click');
			
			e.preventDefault(); // 서브밋 기능 중단(다음 페이지로 안넘어간다)
			
			
			// validation check
			
			var loginId = $('#loginId').val().trim();
			
			
			if (loginId == '') {
				alert('아이디를 입력하세요');
				return false; // submit은 false로 리턴시켜야 한다. 안해주면 submit이 작동
			}
			
			var password = $('#password').val().trim(); // 비번은 trim() 안해줘도 된다
			if (password == '') {
				alert('비밀번호를 입력하세요');
				return false;
			}
			var name = $('#name').val().trim();
			if (name == '') {
				alert('이름를 입력하세요');
				return false;
			}
			var email = $('#email').val().trim();
			if (email == '') {
				alert('이메일를 입력하세요');
				return false;
			}
			
			
			// 아이디 중복확인이 되었는지 확인
			// isCheck <div>에 클래스 중 d-none이 있는 경우 -> 회원가입 x
			// d-none 클래스를 가지고 있다면 
			if ($('#isCheck').hasClass('d-none')) {
				alert('아이디 중복확인을 다시 해주세요');
				return false;
			}
			
			// 여기까지 오면 submit
			var url = $(this).attr('action');
			var params = $(this).serialize();
			
			$.post(url,params)
			.done(function(data) {
				if (data.result = 'success') {
					alert('회원 가입을 환영 합니다. 로그인을 해주세요');
					location.href= "/user/sign_in_view";
				} else {
					alert('회원 가입에 실패했습니다. 다시 시도해주세요');
				}
			});
			
		});
	
		
	});
</script>