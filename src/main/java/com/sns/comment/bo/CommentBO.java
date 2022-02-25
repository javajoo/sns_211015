package com.sns.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.sns.comment.dao.CommentDAO;

public class CommentBO {

	// 글 ,내용
	@Autowired
	private CommentDAO commentDAO;
	
	
	public void createComment(int postId,String commend, String content) {
		commentDAO.
	}
}
