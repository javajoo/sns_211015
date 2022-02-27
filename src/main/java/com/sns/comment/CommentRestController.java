package com.sns.comment;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/commend")
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;
	
	@RequestMapping("/create")
	public Map<String, Object> createCommend(
			@RequestParam("postId") int postId,
			@RequestParam("commend") String commend
			) {
		
		return null;
	}
	
	
}
