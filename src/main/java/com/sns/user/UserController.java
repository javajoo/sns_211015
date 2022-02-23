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
		session.removeAttribute("userName");
		
		return "redirect:/user/sign_in_view";
	}

	
}
