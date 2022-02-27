package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.Comment;
import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private FileManagerService fileManager;
	
	public List<Post> getPostList() {
		List<Post> list = postDAO.selectPostList();
		for(Post post:list) {
			int postId = post.getId();
			List<Comment> commentList = commentBO.getCommentList(postId);
			post.setCommentList(commentList);
		}
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
}
