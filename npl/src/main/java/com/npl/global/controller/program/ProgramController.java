package com.npl.global.controller.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProgramController {
	private Logger logger = LoggerFactory.getLogger(ProgramController.class);
	
	@GetMapping("/programs")
	public String viewHomePage(Model model) {
		model.addAttribute("pageTitle", "Quản lý program");
		return "6030";
	}
}
