<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 세션이 있을때만(로그인 되었을 때만) 출력 -->
<div class="d-flex justify-content-between">
<div class="display-4 font-weight-bold ml-5"><div id="logo">In★gram</div></div>

		<div class="mt-5">
			<c:if test="${not empty userName}">
				<div class="text-secondary"><b>${userName}</b><span class="text-dark">님 안녕하세요</span></div>
			</c:if>
			<div><a href="/user/sign_out" class="font-weight-bold text-primary">로그아웃</a></div>
		</div>

</div>

