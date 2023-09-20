package com.npl.global.controller.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.npl.global.dto.settings.CompanyDto;

@Controller
public class CompanyController extends BaseSystemController{
	private Logger logger = LoggerFactory.getLogger(CompanyController.class);


	@GetMapping("/6020")
	public String company(Model model) {
		try {
			CompanyDto company = new CompanyDto();
			model.addAttribute("pageTitle", "Quản lý công ty");
			model.addAttribute("company", company);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "system/6020";
	}
}
