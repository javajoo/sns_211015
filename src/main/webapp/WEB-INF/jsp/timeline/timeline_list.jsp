<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="d-flex justify-content-center">
	<div class="w-50 my-5 ">
	

		<!--상단: 글쓰기 영역 - 로그인 된 상태에서만 보임 -->
		<c:if test="${not empty userId}">
			<div>
				<textarea id="content" name="content" rows="6" placeholder="내용을 입력해주세요" class="timeline-content form-control"></textarea>
				<div class="d-flex justify-content-between ">
					<input type="file" id="file" name="file" accept=".jpg,.png,.jpeg,.gif" class="d-none"> 
					<a href="#"	id="fileUpLoadBtn"><img src="/static/image/upload.jpg" width="50"></a>

					<!--  업로드 될 임시 파일 이름 저장 될 곳-->
					<div id="fileName"></div>

					<button type="button" id="saveBtn" class="btn-primary form-control col-3">업로드</button>
				</div>
			</div>
		</c:if>

		<!--하단: 타임라인 카드 영역 -->
		<div>
			<c:forEach items="${contentViewList}" var="content">
				<div class="timeline m-3 border rounded bg-white">
				<div class=" d-flex justify-content-between">
					<div class="font-weight-bold mt-3 mb-3 ml-2">${content.user.loginId}</div>
					<div>
						<!-- 글쓴 사용자와 로그인 사용자가 일치할 때만 삭제 가능 -->
						<c:if test="${userLoginId == content.user.loginId}">
						<a href="#" class="more-btn" data-toggle="modal" data-target="#moreModal" data-post-id="${content.post.id}"><img src="/static/image/more-icon.png" width="30" class="mt-3 mb-3 mr-2"></a>
						</c:if>
					</div>
				</div>
				<div class="bg-white">
					<!-- 카드 이미지 -->
					<img src="${content.post.imagePath}" alt="image" class="w-100 mt-3 mb-3" height="300"> 
						
					<!-- 좋아요/해제 : 로그인 된 상태에서만 쓸 수 있다 -->
					<a href="#" class="like-btn" data-post-id="${content.post.id}" >
						<!-- 좋아요 해제 -->
						<c:if test="${content.filledLike == false}">
							<img src="/static/image/heart-icon.png" alt="image" width="25" class="ml-2">
						</c:if>
						
						<!-- 좋아요 -->
						<c:if test="${content.filledLike == true}">
							<img src="/static/image/heart-icon2.png" alt="image" width="25" class="ml-2">
						</c:if>
					</a>
					
					<span class="ml-1">좋아요 ${content.likeCount}개</span>
					<div class="mt-3">${content.post.content}</div>
					<hr>
					
					<!-- 댓글 목록 -->
					<!-- 댓글이 있는 경우만 댓글영역 노출 -->
					
					<c:if test="${not empty content.commentList}">
						<c:forEach items="${content.commentList}" var="comment">
							<div class="d-flex">
								<div class="mr-3"><b>${comment.user.loginId}</b></div>
								<div class="mr-2">${comment.comment.content}</div>
								<c:if test="${comment.user.id == userId}">
									<a href="#"><img src="/static/image/x-icon.png" width="10" height="10" class="commentDelBtn"
									data-comment-id="${comment.comment.id}"></a>
								</c:if>								
							</div>	
						</c:forEach>
					</c:if> 


					<!--댓글 쓰기: 로그인 된 상태에서만 쓸 수 있다 -->
					<c:if test="${not empty userId}">
						<div class="input-group mb-3 mt-3 col-10">
							<input type="text" class="form-control"	id="commentText${content.post.id}" placeholder="댓글달기">
							<div class="input-group-prepend">
								<span class="btn input-group-text commentBtn" data-post-id="${content.post.id}">게시</span>
							</div>
						</div>
					</c:if>
					
					
				</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="moreModal">
  <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
    <div class="modal-content">
      	<!-- modal 창 안에 내용 넣기 -->
      	<div>
      		<!-- class="d-block" : 버튼 전체를 선택할 수 있게 해준다. -->
      		<div class="my-3 text-center"><a href="#" class="del-post d-block">삭제하기</a></div>
      		<hr>
      		<div class="my-3 text-center"><a href="#" class="d-block" data-dismiss="modal">취소</a></div>
      	</div>
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
								&& extensi3on[extension.length - 1] != 'jpg' && extension[extension.length - 1] != 'png')) {
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
				if (file != '') {
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
				
				e.preventDefault();
				// 클릭된 게시글
				var postId = $(this).data('post-id'); //data-post-id
				//alert(postId);
	
				// commentText2
				let commentContent = $('#commentText' + postId).val().trim();
				// let commentContent = $(this).siblings('input').val(); 동일선상에 있는 형재테그를 가져올 떄 
				//alert(commentContent);
				
				if (commentContent == '') {
					alert('댓글을 입력해주세요');
					return;
				}
				
				$.ajax({
					type: "post"
					,url: "/comment/create"
					,data: {"postId":postId, "content":commentContent}
				// "postId" : restcontroller 파라미터 , postId: jsp변수!!
					,success: function(data) {
						if (data.result == 'success') {
							alert("댓글이 입력되었습니다.");
							location.reload();
						} else {
							alert(data.errorMessage);
						}
					} 
					,error: function() {
						alert('관리자에게 문의해주세요');
					}
				});
			});
			
			// 좋아요
			$('.like-btn').on('click',function(e) {
				//alert('click');
				e.preventDefault();
				var postId = $(this).data('post-id');
				
				
				
				$.ajax({
					type: "GET"
					,url: "/like/" + postId
					,data: {"postId": postId}
					,success: function(data) {
						if (data.result == 'success') {
							location.reload();
						} else {
							alert(data.errorMessage);
						}
					}
					,error: function(e) {
						alert('관리자에게 문의해주세요.');
					}
				});
			});
			
			// 카드에서 더보기(...) 클릭시 모달에 삭제될 글 번호를 넣어준다.
			$('.more-btn').on('click',function(e) {
				e.preventDefault();
				var postId = $(this).data('post-id');
				//alert(postId);
				
				$('#moreModal').data('post-id', postId);  // data-post-id = "1"
				
			});
			
			// 모달창 안에 있는 삭제하기 버튼 클릭
			/* 모달 버튼 안에 있는 삭제 */
			$('#moreModal .del-post').on('click',function(e) {
				e.preventDefault(); // 모달 버튼은 해주는게 좋다!
				
				var postId = $('#moreModal').data('post-id');
				alert(postId);
				
				// 삭제 ajax
				$.ajax({
					type: "DELETE"
					,url: "/post/delete"
					,data: {"postId" : postId}
					,success: function(data) {
						if (data.result == 'success') {
							alert('삭제 되었습니다.');
							location.reload();
						} else {
							alert(data.errorMessage);
						}
					}
					,error: function(e) {
						alert('관리자에게 문의해주세요');
					}
					
				});
				
				
			});
		
			
			$('.commentDelBtn').on('click', function(e) {
				e.preventDefault(); // a 기본 동작 중단
				
				let commentId = $(this).data('comment-id');
				//alert(commentId);
				
				$.ajax({
					type:"delete"
					, url:"/comment/delete"
					, data:{"commentId":commentId}
					, success:function(data) {
						if (data.result == "success") {
							alert("댓글이 삭제되었습니다.");
							location.reload();
						} else {
							alert(data.errorMessage);
						}
					}
					, error:function(e) {
						alert("댓글 삭제가 실패했습니다. 관리자에게 문의해주세요.");
					}
				});
			});
		});
	</script>