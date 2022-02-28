package com.sns.like;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeRestController {

	
	// /like/{postId} 와일드카드에 있는 값을 가져다가 사용할 수 있다 @PathVariable
	@RequestMapping("/like/{postId}") 
	public Map<String, Object> like(
			@PathVariable int postId) {
	
		
		return null;
	}
	
}
