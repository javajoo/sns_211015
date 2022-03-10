package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.ContentBO;
import com.sns.timeline.model.ContentView;

@Controller
@RequestMapping("/timeline")
public class TimelineController {

	@Autowired
	private ContentBO contentBO;

	// localhost:8080/timeline/timeline_list_view
	@RequestMapping("/timeline_list_view")
	public String timelineListView(Model model, HttpServletRequest request) { // 세션에 대한 정보를 가져와야 한다

		HttpSession session = request.getSession();
		// 로그인 되어있든 되어있지 않든 허용 null 허용하기 위해 Integer
		Integer userId = (Integer) session.getAttribute("userId");
		// 웹 만들 때 null 허용할지 말지 정해야 한다.

		// 하나의 카드 -> ContentView 객체 (VIEW용 객체)
		List<ContentView> contentViewList = contentBO.generateContentViewList(userId);

		model.addAttribute("contentViewList", contentViewList);
		model.addAttribute("viewName", "timeline/timeline_list");
		return "template/layout";
	}
}
