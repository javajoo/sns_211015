package com.sns.post.model;

import java.util.Date;
import java.util.List;

import com.sns.comment.model.Comment;

public class Post {

	private int id;
	private int userId;
	private String content;
	private String imagePath;
	private Date createdAt;
	private Date updatedAt;
	private List<Comment> commentList;

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", userId=" + userId + ", content=" + content + ", imagePath=" + imagePath
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", commentList=" + commentList + "]";
	}

}
