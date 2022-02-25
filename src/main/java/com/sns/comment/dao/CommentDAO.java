package com.sns.comment.dao;

import org.apache.ibatis.annotations.Param;

public interface CommentDAO {

	public void insertComment(
			@Param("postId") int postId,
			@Param("commend") String commend, 
			@Param("content") String content);
}
