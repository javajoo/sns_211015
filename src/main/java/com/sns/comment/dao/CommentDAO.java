package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sns.comment.model.Comment;

public interface CommentDAO {

	public void insertComment(
			@Param("postId") int postId,
			@Param("commend") String commend, 
			@Param("content") String content);
	
	public List<Comment> getCommentList(int postId);
}