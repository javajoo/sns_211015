package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		
		Map<String, Object> result = new HashMap<>();
		boolean existLoginId = userBO.existLoginId(loginId);
		
		result.put("result", existLoginId);
				
		
		return result;
	}
	
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			) {
		
		// 비밀번호 암호화
		String encryptPassword = EncryptUtils.md5(password);
		
		// db insert
		int count = userBO.addUser(loginId, encryptPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		if (count < 1) {
			result.put("result", "error");
		}
	
		
		return result;
	}
	
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request
			) {
		
		//비밀번호 암호화
		String encrtptUtils = EncryptUtils.md5(password);
		
		User user = userBO.getUserByLoginIdPassword(loginId, encrtptUtils);
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", user.getName());
		} else {
			result.put("result", "error");
			result.put("errorMessage", "존재하지 않는 사용자 입니다. 관리자에게 문의해주세요.");
		}
	
		return result;
	}
}
