package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {

	// 글 ,내용
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;
	
	
	public void createComment(int postId,String commend, String content) {
		//commentDAO.
	}
	
	public List<Comment> getCommentList(int postId){
		List<Comment> commentList = commentDAO.getCommentList(postId);
		for(Comment comment : commentList) {
			int userId = comment.getUserId();
			User user = userBO.getUserById(userId);
			comment.setLoginId(user.getLoginId());
		}
		return commentList;
	}
}
