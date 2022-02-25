package com.sns.timeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.ContentView;

@Controller
@RequestMapping("/timeline")
public class TimelineController {

	@Autowired
	private PostBO postBO;
	
//	@Autowired
//	private CommentBO commentBO;
	
	
	//localhost:8080/timeline/timeline_list_view
	@RequestMapping("/timeline_list_view")
	public String timelineListView(Model model) {
		
		
		// 하나의 카드 -> ContentView 객체 (VIEW용 객체)
		List<ContentView> contentList = new ArrayList<>();
		
		
		
		
		// POST 내용
	//	List<Comment> commentList;
		
		model.addAttribute("viewName","timeline/timeline_list");
		
		 List<Post> postList = postBO.getPostList();
		 model.addAttribute("postList",postList);
		 
		return "template/layout";
	}
}
