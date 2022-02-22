<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 세션이 있을때만(로그인 되었을 때만) 출력 -->

<div class="d-flex justify-content-end mt-5">
	<div>
		<c:if test="${not empty userName}">
			<span class="text-secondary"><b>${userName}</b><span class="text-dark">님 안녕하세요</span></span>
			<div><a href="/user/sign_out" class="font-weight-bold text-primary">로그아웃</a></div>
		</c:if>
	</div>
</div>
