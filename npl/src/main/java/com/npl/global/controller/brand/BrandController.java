package com.npl.global.controller.brand;

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

import com.npl.global.dto.ResultProcDto;
import com.npl.global.model.category.CategoryModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.brand.BrandService;
import com.npl.global.service.category.CategoryService;

@Controller
public class BrandController {
	
	@Autowired private CategoryService catService;

	private Logger logger = LoggerFactory.getLogger(BrandController.class);
	
	@GetMapping("/brands")
	public String viewHomePage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
		
		String comId = loggedUser.getUser().getCompany().getComId();
		List<CategoryModel> listCat = catService.findAll(comId);
		model.addAttribute("pageTitle", "Quản lý thương hiệu");
		model.addAttribute("listCat", listCat);
		return "2010";
	}
	
}
