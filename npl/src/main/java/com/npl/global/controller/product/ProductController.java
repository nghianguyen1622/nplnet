package com.npl.global.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.npl.global.service.product.ProductService;

@Controller
public class ProductController {
	
	@Autowired private ProductService service;
	
	@GetMapping("/products")
	public String viewHomePage(Model model) {
		model.addAttribute("pageTitle", "Quản lý sản phẩm");
		return "2030";
	}
	
}
