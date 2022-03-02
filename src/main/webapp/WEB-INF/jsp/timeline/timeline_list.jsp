<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="d-flex justify-content-center">
	<div class="w-50 my-5">


		<!--상단: 글쓰기 영역 - 로그인 된 상태에서만 보임 -->
		<c:if test="${not empty userId}">
			<div id="writeBox" class="mb-3 border rounded bg-white">
				<textarea id="content" name="content" rows="6" placeholder="내용을 입력해주세요" class="timeline-content form-control"></textarea>
				<div class="d-flex justify-content-between m-1">
					<input type="file" id="file" name="file" accept=".jpg,.png,.jpeg,.gif" class="d-none"> 
					<a href="#"	id="fileUpLoadBtn"><img src="/image/upload.jpg" width="50"></a>

					<!--  업로드 될 임시 파일 이름 저장 될 곳-->
					<div id="fileName"></div>

					<button type="button" id="saveBtn" class="btn-primary form-control col-3">업로드</button>
				</div>
			</div>
		</c:if>

		<!--하단: 타임라인 카드 영역 -->
		<div class="card">
			<c:forEach items="${contentList}" var="content">
				<div class=" d-flex justify-content-between">
					<div class="display-4 font-weight-bold text-secondary">${content.user.loginId}</div>
					<div>
						<a href="#"><img src="/image/more-icon.png" width="50" class="mt-2"></a>
					</div>
				</div>
				<div>
					<img src="${content.post.imagePath}" alt="image" class="w-100 mt-3 mb-3" height="300"> 
					<a href="#"><img src="/image/heart-icon.png" alt="image" width="25"></a> 
					<a href="#"><img src="/image/heart-icon2.png" alt="image" width="25" class="d-none"></a>
					<span><b>좋아요 11개</b></span>
					<div class="mt-3">${content.post.content}</div>
					<hr>
					
					
					<!-- 댓글 목록 -->
					<!-- 댓글이 있는 경우만 댓글영역 노출 -->
					<c:if test="${not empty content.commentList}"> 
						<c:forEach items="${content.commentList}" var="comment">
							<div class="d-flex">
								<div class="mr-3"><b>${comment.user.loginId}</b></div>
								<div class="mr-2">${comment.comment.content}</div>
								<a href="#"><img src="/image/x-icon.png" width="10" height="10"></a>
							</div>	
						</c:forEach>
					</c:if> 


					<!--댓글 쓰기: 로그인 된 상태에서만 쓸 수 있다 -->
					<c:if test="${not empty userId}">
						<div class="input-group mb-3 mt-3">
							<input type="text" class="form-control"	id="commentText${content.post.id}" placeholder="댓글달기">
							<div class="input-group-prepend">
								<span class="btn input-group-text commentBtn" data-post-id="${content.post.id}">게시</span>
							</div>
						</div>
					</c:if>
					
					
				</div>
			</c:forEach>
		</div>
	</div>
</div>





	<script>
		$(document).ready(function() {
			//alert("click");
	
			// 파일 업로드 이미지 클릭 -> 파일 선택 창이 떠야함
			$('#fileUpLoadBtn').on('click', function(e) {
				//alert("click");
				e.preventDefault();
				$('#file').click(); // input file을 클릭한 것과 같은 효과 (file d-none 해줌)
	
			});
	
			// 사용자가 파일 업로드를 했을 때, 유효성 확인 및 업로드 된 파일 이름 노출
			// change로 해줘야한다: 이미지를 눌러서 파일이 올라왔을 때 
			$('#file').on('change',function(e) {
				//alert("click");
	
				let fileName = e.target.files[0].name; // 파일의 이름을 가져온다 mainImage.jpg
				alert(fileName);
	
				// 확장자 유효성 확인
				let extension = fileName.split('.');
	
				if (extension.length < 2
						|| (extension[extension.length - 1] != 'gif'
								&& extension[extension.length - 1] != 'jpeg'
								&& extension[extension.length - 1] != 'jpg' && extension[extension.length - 1] != 'png')) {
					alert('이미지 파일만 업로드 할 수 있습니다.');
					$(this).val(''); // 비워주어야 한다.(파일자체를 지움)
					$('#fileName').text(''); //파일 이름도 비워준다.
					return;
				}
	
				$('#fileName').text(fileName);
	
			});
	
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
					if ($.inArray(ext, [ 'jpg','png', 'jpeg', 'gif' ]) == -1) { //저장된 변수에 4개의 확장자가 없으면 
						alert('jpg, png, jpeg, gif 파일만 업로드 할 수 있습니다.');
						$('#file').val(''); //내용 초기화
						return;
					}
				}
	
				// 폼 태그를 자바스크립트에서 만든다
				var formData = new FormData();
				formData.append("content", content);
				formData.append("file",$('#file')[0].files[0]);
	
				// ajax form 데이터 전송
				$.ajax({
					type : "post",
					url : "/post/create",
					data : formData,
					// 파일 업로드를 위한 필수 설정
					enctype : "multipart/form-data",
					contentType : false,
					processData : false,
					success : function(data) {
						if (data.result == "success") {
							alert("저장 되었습니다.");
							location.reload();
						}
					},
					error : function(e) {
						alert("메모 저장에 실패했습니다. 관리자에게 문의해주세요.");
					}
				});
			});
	
			// 댓글 쓰기 - 게시 버튼 클릭
			$('.commentBtn').on('click',function(e) {
				// 클릭된 게시글
				var postId = $(this).data('post-id'); //data-post-id
				alert(postId);
	
				// commentText2
				let commentContent = $('#commentText' + postId).val().trim();
				// let commentContent = $(this).siblings('input').val(); 동일선상에 있는 형재테그를 가져올 떄 
				alert(commentContent);
				
				$.ajax({
					type: "post"
					,url: "/comment/create"
					,data: {"postId":postId, "content":commentContent}
				// "postId" : restcontroller 파라미터 , postId: jsp변수!!
					,success: function(data) {
						if (data.result == 'success') {
							alert("댓글이 입력되었습니다.");
							location.reload();
						}
					} 
				});
			});
		});
	</script>