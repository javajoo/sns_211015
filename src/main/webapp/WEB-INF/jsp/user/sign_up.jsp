<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div class="d-flex justify-content-center">
		<div>
			<div class="mb-3"><img src="/images/image.png" alt="image" width="100px" height="100px"></div>
	
	<div class="d-flex">
		<input type="text" id="loginId" name="loginId" class="form-control" placeholder="아이디">
		<div class="pl-3"><button type="button" id="loginIdCheckBtn" class="form-control btn-success col-4">중복확인</button></div>
	</div>
	
	<div id="idCheckLength"><small class="text-primary font-weight-bold">ID를 4자 이상 입력 해주세요.</small></div>
	<div id="isCheck"><small class="text-primary font-weight-bold d-none">사용 가능한 아이디 입니다.</small></div>
	<div id="isCheckDupliceted"><small class="text-danger font-weight-bold d-none">중복된 아이디 입니다.</small></div>
	
	<div class="d-flex mt-3">
		<input type="text" id="password" name="password" class="form-control" placeholder="비밀번호">
	</div>
	
	<div class="d-flex mt-3">
		<input type="text" id="name" name="name" class="form-control" placeholder="이름">
	</div>
	
	<div class="d-flex mt-3">
		<input type="text" id="email" name="email" class="form-control" placeholder="이메일">
	</div>
	
	<button type="button" class="form-control btn-info mt-3">회원가입</button>
	
		</div>
	</div>