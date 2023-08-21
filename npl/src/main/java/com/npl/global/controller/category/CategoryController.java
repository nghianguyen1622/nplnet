package com.npl.global.controller.category;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.npl.global.model.CategoryModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.category.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired private CategoryService service;

	private Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@GetMapping("/categories")
	public String viewHomePage(Model model) {
		model.addAttribute("pageTitle", "Quản lý danh mục");
		return "8030";
	}
	
	@GetMapping("/category/8030")
	public  @ResponseBody List<CategoryModel> getAll() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			List<CategoryModel> listNotice = service.findAll(comId);
			return listNotice;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
