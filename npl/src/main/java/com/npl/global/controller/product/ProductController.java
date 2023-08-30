package com.npl.global.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.npl.global.model.brand.BrandModel;
import com.npl.global.model.category.CategoryModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.brand.BrandService;
import com.npl.global.service.category.CategoryService;
import com.npl.global.service.product.ProductService;

@Controller
public class ProductController {
	
	@Autowired private ProductService service;
	
	@Autowired private CategoryService cateService;
	@Autowired private BrandService brandService;
	
	@GetMapping("/products")
	public String viewHomePage(Model model) {
		model.addAttribute("pageTitle", "Quản lý sản phẩm");
		return "2030";
	}
	
	@GetMapping("/products/2031")
	public String view2031(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
		
		String comId = loggedUser.getUser().getCompany().getComId();
		List<BrandModel> listBrand = brandService.findAll(comId);
		List<CategoryModel> listCat = cateService.findAll(comId);
		model.addAttribute("listBrand", listBrand);
		model.addAttribute("listCat", listCat);
		return "2031";
	}
	
}
