package com.npl.global.controller.product;

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

import com.npl.global.controller.notice.NoticeController;
import com.npl.global.entity.Company;
import com.npl.global.model.product.ProductModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.product.ProductService;

@Controller
public class ProductController {
@Autowired private ProductService service;
	
	private Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@GetMapping("/products")
	public String viewHomePage(Model model) {
		model.addAttribute("pageTitle", "Quản lý sản phẩm");
		return "8020";
	}
	
	@GetMapping("/product/8020")
	public  @ResponseBody List<ProductModel> getAll() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			Company company = loggedUser.getUser().getCompany();
			List<ProductModel> listAll = service.findAll(company);
			return listAll;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
