package com.sns.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 회원가입 화면
	 * @param model
	 * @return
	 */
	//localhost:8080/user/sign_up_view	
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("viewName","user/sign_up");
		return "/template/layout";
	}
	
	/**
	 * 로그인 화면
	 * @param model
	 * @return
	 */
	//localhost:8080/user/sign_in_view
	@RequestMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName","user/sign_in");
		return "/template/layout";
	}
	
	/**
	 * 로그아웃
	 * @param request
	 * @return
	 */
	@RequestMapping("/sign_out")
	public String signOut(
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		// 로그인 한거를 끊으려면 모두 끊어야 한다!!!!
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		session.removeAttribute("userId");
		
		// 리다이렉트 안하면 @RequestMapping으로 넘어간다.
		return "redirect:/user/sign_in_view";
	}

	
}
