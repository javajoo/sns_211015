package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

@RestController
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;

	// /like/{postId} // 와일드카드
	@RequestMapping("/like/{postId}") // pathVariable
	public Map<String, Object> like(@PathVariable int postId, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");

		// TODO 좋아요 갯수 BO

		// TODO 사용자의 좋아요 여부
		likeBO.addLike(postId, userId);

		return result;

	}

}
