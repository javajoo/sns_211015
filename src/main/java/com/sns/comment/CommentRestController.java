package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create")
	public Map<String, Object> createComment(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpServletRequest request
			) {
		
		HttpSession session = request.getSession();
		// 가져온 세션을 다시 userId에 저장해준다
		Integer userId = (Integer)session.getAttribute("userId");
	    commentBO.insertComment(postId, userId, content);
	
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		return result;
	}

}