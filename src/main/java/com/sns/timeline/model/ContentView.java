package com.sns.timeline.model;

import java.util.List;

import com.sns.Comment.model.Comment;
import com.sns.post.model.Post;

// 타임라인 카드 한장
public class ContentView {
	// 새로 만들어도 되지만 있는거 활용해도 된다.
	private Post post;
	private List<Comment> commentList;

//	private List<Like> likeList;
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
