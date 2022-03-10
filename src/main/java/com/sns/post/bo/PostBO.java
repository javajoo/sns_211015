package com.sns.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private PostDAO postDAO;

	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private CommentBO commentBO;
	
	public List<Post> getPostList() {
		List<Post> list = postDAO.selectPostList();

		return list;
	}
	
	public Post getPostByPostIdAndUserId(int postId, int userId) {
		return postDAO.selectPostByPostIdAndUserId(postId, userId);
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
		Post post = getPostByPostIdAndUserId(postId, userId);
		
		if (post == null) {
			logger.error("[delete post] 삭제할 게시물이 없습니다. postId: {}" , postId);
			return;
		}
		
		// 이미지가 있으면 이미지 삭제
		
		if (post.getImagePath()  != null) {
			try {
				fileManager.deleteFile(post.getImagePath());
			} catch (IOException e) {
				logger.error("[delete post] 이미지 삭제 실패, postId: {}, path: {}", postId, post.getImagePath());
			}
		}
		
		
		// 글 삭제 byPostIdUserId
		
		postDAO.deletePost(postId, userId);
		
		// 댓글들 삭제 byPostId
		
		commentBO.deleteCommentsByPostId(postId);
		
		// 좋아요들 삭제 byPostId
		
		likeBO.deleteLikeByPostId(postId);
		
		
		
	}
}
