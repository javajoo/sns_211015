package com.sns.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postsBO;
	
	/**
	 * 테스트용 컨트롤러
	 * @return
	 */
	@RequestMapping("/posts")
	public List<Post> posts() {
		return postsBO.getPostList();
	}
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("content") String content,
			@RequestParam(value ="file", required = false) MultipartFile file,
			HttpServletRequest request
			) {
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		// 글쓴이 정보를 세션에서 가져온다.
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		if (userId == null) { // 로그인 되어있지 않음
			result.put("result", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다.");
			return result;
		}
		
		// userId, userLoginId, content, file -> BO insert 요청
		postsBO.addPost(userId, userLoginId, content, file);
		
		
		
		return result;
	}
}
