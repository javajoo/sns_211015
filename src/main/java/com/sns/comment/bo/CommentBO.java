package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	
	// 남의 다오는 부를 수 없고 남의 비오는 부를 수 있다.
	
	@Autowired
	private UserBO userBO;
	
	public void insertComment(int postId,int userId,String content) {
		commentDAO.insertComment(postId, userId, content);
	}
	
	public List<Comment> getCommentListByPostId(int postId) {
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		// 글번호를 받아서 해당하는 댓글 목록을 가져온다.
		List<CommentView> resultList = new ArrayList<>();
		List<Comment> commentList = getCommentListByPostId(postId);
		
		for (Comment comment: commentList) { // Comment -> CommentView
			CommentView commentView = new CommentView();
			
			// 댓글
			commentView.setComment(comment);
			
			// 댓글쓴이
			User user = userBO.getUserByuserId(comment.getUserId()); // pk
			commentView.setUser(user);
			
			resultList.add(commentView);
			
		}
		
		return resultList;
	}
	
}