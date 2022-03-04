package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;

	
	@Autowired
	private FileManagerService fileManager;
	
//	@Autowired
//	private CommentBO commentBO;
	
	public List<Post> getPostList() {
		List<Post> list = postDAO.selectPostList();
//		for(Post post:list) {
//			int postId = post.getId();
//			List<Comment> commentList = commentBO.getCommentList(postId);
//			post.setCommentList(commentList);
//		}
		return list;
	}
	
	// 가공, 로직을 하는 곳
	// userId, userLoginId, content, file
	public void addPost(int userId, String userLoginId, String content, MultipartFile file) {
		
		String imagePath = null;
		
		if (file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		
		
		// insert DAO
		postDAO.insertPost(userId, content, imagePath);
	}
	
	public void deletePostByPostIdUserId(int postId, int userId) {
		// 삭제 할때 거기에 종속되어 있는게 뭐가 있는지 확인하고 삭제해야 한다.
		// postId로 select Post
		Post post = postDAO.selectPostList(postId);
		
		// 이미지가 있으면 이미지 삭제
		
		String imagePath = null;
		if (imagePath != null) {
			fileManager.deleteFile(imagePath);
		}
		
		
		// 글 삭제 byPostIdUserId
		
		// 댓글들 삭제 byPostId
		
		// 좋아요들 삭제 byPostId
		
		
		
		
		
	}
}
