package com.npl.global.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/users")
	public String viewHomePage(Model model) {
		model.addAttribute("pageTitle", "Quản lý User");
		return "1010";
	}
}
