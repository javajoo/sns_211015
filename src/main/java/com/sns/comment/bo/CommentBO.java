package com.sns.Comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.Comment.dao.CommentDAO;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	
	public void insertComment(int userId, int postId,String content) {
		commentDAO.insertComment(userId, postId, content);
	}
	
}
