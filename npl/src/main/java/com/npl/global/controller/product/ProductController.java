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
import org.springframework.web.bind.annotation.PathVariable;

import com.npl.global.common.CheckUtil;
import com.npl.global.dto.product.PdtDto;
import com.npl.global.model.brand.BrandModel;
import com.npl.global.model.category.CategoryModel;
import com.npl.global.model.product.ProductModel;
import com.npl.global.model.settings.CompanyModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.brand.BrandService;
import com.npl.global.service.category.CategoryService;
import com.npl.global.service.product.ProductService;
import com.npl.global.service.settings.CompanyService;

@Controller
public class ProductController extends BaseProductController{
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired private ProductService service;
	@Autowired private CategoryService cateService;
	@Autowired private BrandService brandService;
	@Autowired private CheckUtil checkYN;
	@Autowired private CompanyService comService;
	
	@GetMapping("/2030")
	public String viewPage(Model model) {
		if(checkYN.isAdmin()) {
			List<CompanyModel> com = comService.getAll();
			model.addAttribute("isAdmin", com);
		}
		model.addAttribute("pageTitle", "Quản lý sản phẩm");
		String urlHepl = "/product/2030";
		String htmlNo = "product/2030";
		return checkYN.canAccess(urlHepl, htmlNo);
	}
	
	@GetMapping("/2031")
	public String view2031(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
		try {
			PdtDto pdt = new PdtDto();
			String comId = loggedUser.getUser().getCompany().getComId();
			List<BrandModel> listBrand = brandService.findAll(comId);
			List<CategoryModel> listCat = cateService.findAll(comId);
			model.addAttribute("listBrand", listBrand);
			model.addAttribute("listCat", listCat);
			model.addAttribute("pdt", pdt);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "product/2031";
	}
	
	@GetMapping("/2032/{pdtId}")
	public String getInfo(@PathVariable(name = "pdtId") String pdtId, Model model) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			ProductModel pdtInfo = service.findInfo(pdtId, comId);
			List<ProductModel> listDetail = service.findPdtDetail(pdtId);
			List<BrandModel> listBrand = brandService.findAll(comId);
			List<CategoryModel> listCat = cateService.findAll(comId);
			model.addAttribute("listBrand", listBrand);
			model.addAttribute("listCat", listCat);
			model.addAttribute("pdt", pdtInfo);
			model.addAttribute("listDetail", listDetail);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "product/2032";
	}
	
}
