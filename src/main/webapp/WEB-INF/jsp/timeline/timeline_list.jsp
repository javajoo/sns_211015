<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="w-50 my-5">
		<textarea id="content" name="content" rows="6" placeholder="내용을 입력해주세요" class="timeline-content form-control"></textarea>
		<div class="d-flex justify-content-between">
		<input type="file" id="file" name="file" accept=".jpg,.png,.jpeg,.gif">
		<button type="button" id="saveBtn" class="btn-info form-control col-3">업로드</button>
		</div>
			
			<c:forEach items="${postList}" var="postList">
			${postList.userId}
			<img src="${postList.imagePath}" >
			</c:forEach>
	</div>
</div>

<script>
	$(document).ready(function() {
		//alert("click");
		$('#saveBtn').on('click',function(e) {
			//alert("click");
			
			var content = $('#content').val();
			if (content == '') {
				alert('내용을 입력해주세요');
				return;
			}
			
			var file = $('#file').val();
			
			console.log(file); //C:\fakepath\Capture001.png
			
			// 파일이 있지만 4개의 확장자가 없다면~
			// 파일이 있으면 .jpg,.png,.jpeg,.gif
			if (file != null) { 
				var ext = file.split('.').pop().toLowerCase(); // 파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경
				if ($.inArray(ext, ['jpg','png','jpeg','gif']) == -1) { //저장된 변수에 4개의 확장자가 없으면 
					alert('jpg, png, jpeg, gif 파일만 업로드 할 수 있습니다.');
					$('#file').val(''); //내용 초기화
					return;
				}
			}
			
			// 폼 태그를 자바스크립트에서 만든다
			var formData = new FormData();
			formData.append("content",content);
			formData.append("file",$('#file')[0].files[0]);
			
			// ajax form 데이터 전송
			$.ajax({
				type: "post"
				,url: "/post/create"
				,data: formData
				// 파일 업로드를 위한 필수 설정
				,enctype: "multipart/form-data"
				,contentType: false
				,processData: false
				,success: function(data){
					if (data.result == "success") {
						alert("저장 되었습니다.");
						location.reload();
					}
				}
				,error: function(e) {
					alert("메모 저장에 실패했습니다. 관리자에게 문의해주세요.");
				}
			});
		})
	});
</script>