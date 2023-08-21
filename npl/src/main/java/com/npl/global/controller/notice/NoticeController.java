package com.npl.global.controller.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {
	
	@GetMapping("/notice")
	public String viewHomePage(Model model) {
		model.addAttribute("pageTitle", "Quản lý thông báo");
		return "8010";
	}
	
}
