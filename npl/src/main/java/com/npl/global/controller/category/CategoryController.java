package com.npl.global.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.npl.global.model.category.CategoryModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.category.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired private CategoryService service;

	@GetMapping("/categories")
	public String viewHomePage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
		
		String comId = loggedUser.getUser().getCompany().getComId();
		List<CategoryModel> listCat = service.findAll(comId);
		model.addAttribute("pageTitle", "Quản lý danh mục");
		model.addAttribute("listCat", listCat);
		return "2020";
	}
	
}
