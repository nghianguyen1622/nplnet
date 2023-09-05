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

import com.npl.global.dto.brand.BrandDto;
import com.npl.global.model.category.CategoryModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.category.CategoryService;

@Controller
public class BrandController {
	private Logger logger = LoggerFactory.getLogger(BrandController.class);

	@Autowired private CategoryService catService;

	@GetMapping("/brands")
	public String viewHomePage1(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
		
		String comId = loggedUser.getUser().getCompany().getComId();
		try {
			BrandDto brand = new BrandDto();
			List<CategoryModel> listCat = catService.findAll(comId);
			model.addAttribute("pageTitle", "Quản lý thương hiệu");
			model.addAttribute("listCat", listCat);
			model.addAttribute("brand", brand);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "2010";
	}
	
}
