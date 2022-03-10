package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.ContentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class ContentBO {

	// 다른 BO를 부를 수 있다.
	@Autowired
	private PostBO postBO; //db에 가깝다
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	
	
	// 로그인 되지 않아도 타임라인은 볼 수 있으므로 userId는 Integer 	
	public List<ContentView> generateContentViewList(Integer userId) {
		
		
		List<ContentView> contentViewList = new ArrayList<>();
		
		// 글 List를 가져온다. -> 반복문 돌림
		List<Post> postList = postBO.getPostList();
		
		for (Post post : postList) { // 글의 갯수만큼 돌려서 하나씩 꺼낸다.
			ContentView content = new ContentView(); // 해당하는 곳 들어가려면 f3
			
			// 글 정보
			content.setPost(post);
			
			// 글마다 글쓴이가 다르기 때문에 여기서 만들어줘야 한다
			// 글쓴이 정보
			User user = userBO.getUserByuserId(post.getUserId());
			
			content.setUser(user);
			
			// 댓글 정보
			
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			content.setCommentList(commentList);
			
			// 좋아요 개수 세팅
			content.setLikeCount(likeBO.getLikeCountByPostId(post.getId()));
			
			
			// 로그인 된 사용자의 좋아요 여부 세팅
			content.setFilledLike(likeBO.existLike(post.getId(), userId));
			
			
			contentViewList.add(content);
		}
		
			return contentViewList;
		}
		
	}
