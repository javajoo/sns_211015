package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView; 
import com.sns.post.model.Post;
import com.sns.user.model.User;

// 타임라인 카드 한장
public class ContentView { // ${content.post.imagePath} 그 안의 객체의 필드를 사용할 수 있다
	// 새로 만들어도 되지만 있는거 활용해도 된다.
	private Post post;
	private User user; // 글쓴이
	private List<CommentView> commentList;  // ${content.commentList.user.name}
	private int likeCount; // 좋아요의 개수
	private boolean filledLike; // 내가 좋아요를 눌렀는지의 여부
	
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	// boolean 인경우 is로 게터,세터 만들어진다.
	public boolean isFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}

}
