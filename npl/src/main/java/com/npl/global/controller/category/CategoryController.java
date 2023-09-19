package com.npl.global.controller.category;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.npl.global.common.CheckUtil;
import com.npl.global.dto.category.CategoryDto;
import com.npl.global.model.category.CategoryModel;
import com.npl.global.service.category.CategoryService;

@Controller
public class CategoryController {
	private Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService service;
	@Autowired
	private CheckUtil checkUtil;

	@GetMapping("/2020")
	public String viewHomePage(Model model) {
		String urlHelp = "/2020";
		String htmlNo = "2020";

		String result = checkUtil.canAccess(urlHelp, htmlNo);
		if (result.equals(htmlNo)) {
			try {
				String comId = checkUtil.getComId();
				CategoryDto category = new CategoryDto();
				List<CategoryModel> listCat = service.listCate(comId);
				model.addAttribute("pageTitle", "Quản lý danh mục");
				model.addAttribute("listCat", listCat);
				model.addAttribute("category", category);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return htmlNo;
		}

		return result;
	}

}
