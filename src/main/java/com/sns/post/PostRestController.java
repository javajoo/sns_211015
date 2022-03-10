package com.sns.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	// 로깅은 상단에 만든다. slf4j로 임포트 해야 한다
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PostBO postBO;

	/**
	 * 목록
	 * 
	 * @return
	 */
	@RequestMapping("/posts")
	public List<Post> posts() {
		return postBO.getPostList();
	}

	@PostMapping("/create")
	public Map<String, Object> create(@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {

		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");

		// 글쓴이 정보를 세션에서 가져온다.
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		String userLoginId = (String) session.getAttribute("userLoginId"); // 파일서비스매니저

		if (userId == null) { // 로그인 되어있지 않음
			result.put("result", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다.");
			return result;
		}

		// userId, userLoginId, content, file -> BO insert 요청
		postBO.addPost(userId, userLoginId, content, file);

		return result;
	}

	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		// 오래동안 잠시 비워있어서 세션 멈출 가능성이 있기 때문에
		// 페이지 권한 검사 안하기 때문에 Integer
		Integer userId = (Integer) session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		if (userId == null) {
			result.put("result", "error");
			result.put("errorMessage", "로그인을 다시 해주세요");
			// 로그 추가해주면 좋다 로거는 필수적인 항목,,, 남겨야 문제가 생겼을때 단서가 된다
			logger.error("[post delete] 로그인 세션이 없습니다. userId:{}, postId:{}", userId, postId);
			// 리턴해줘야 한다.
			return result;
		}

		//postBO
	
		postBO.deletePostByPostIdUserId(postId, userId);
		
		return result;

	}

}
