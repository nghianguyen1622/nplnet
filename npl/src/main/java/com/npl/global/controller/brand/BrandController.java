package com.npl.global.controller.brand;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.npl.global.common.CheckUtil;
import com.npl.global.dto.brand.BrandDto;
import com.npl.global.model.category.CategoryModel;
import com.npl.global.service.category.CategoryService;

@Controller
public class BrandController {
	private Logger logger = LoggerFactory.getLogger(BrandController.class);

	@Autowired private CategoryService catService;

	@Autowired private CheckUtil checkUtil;

	@GetMapping("/2010")
	public String viewHomePage1(Model model) {
		String urlHelp = "/2010";
		String htmlNo = "2010";

		String result = checkUtil.canAccess(urlHelp, htmlNo);

		if (result.equals(htmlNo)) {
			String comId = checkUtil.getComId();
			try {
				BrandDto brand = new BrandDto();
				List<CategoryModel> listCat = catService.listCate(comId);
				model.addAttribute("pageTitle", "Quản lý thương hiệu");
				model.addAttribute("listCat", listCat);
				model.addAttribute("brand", brand);

			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return htmlNo;
		}

		return result;
	}

}
