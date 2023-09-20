package com.npl.global.controller.category;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.npl.global.common.CheckUtil;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.category.CategoryDto;
import com.npl.global.model.category.CategoryModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.category.CategoryService;
import com.npl.global.service.system.StorageService;

@RestController
public class CategoryRestController extends BaseCategoryController{
	private Logger logger = LoggerFactory.getLogger(CategoryRestController.class);
	
	@Autowired private CategoryService service;
	
	@Autowired private CheckUtil checkUtil;
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/2020/list")
	public  @ResponseBody List<CategoryModel> getAll() {
		try {
			String comId = checkUtil.getComId();
			List<CategoryModel> listCat = service.findAll(comId);
			return listCat;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/2020/{id}")
	public  @ResponseBody CategoryModel getInfo(@PathVariable(name = "id") String catId) {
		try {
			String comId = checkUtil.getComId();
			CategoryModel catInfo = service.findInfo(catId, comId);
			return catInfo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping(value = "/2020/save")
	public @ResponseBody ResultProcDto save(@ModelAttribute  CategoryDto catDto) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			
			ResultProcDto result;

			String comId = loggedUser.getUser().getCompany().getComId();
			String workUser = loggedUser.getUser().getWorkUser();
			
			catDto.setComId(comId);
			catDto.setWorkUser(workUser);
			
			
			if(!catDto.getFileData().isEmpty()) {
				if(!catDto.getCatId().isEmpty()) {
					storageService.delete(service.findInfo(catDto.getCatId(), comId).getFileName(), "cat");
				}
				String fileName = storageService.store(catDto.getFileData(), "cat");
				
				catDto.setFilePath("fileupload/category/" + fileName);
				catDto.setFileName(fileName);
				catDto.setFileNameOrg(fileName);
				
			}else {
				catDto.setFilePath("");
				catDto.setFileName("");
				catDto.setFileNameOrg("");
			}
			result = this.service.saveCate(catDto);
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			return null;
		}
	}
	
	@RequestMapping(value = "/2020/delete/{catId}")
	public  @ResponseBody ResultProcDto  delete( @PathVariable(name = "catId") String catId) {
		try {
			String comId = checkUtil.getComId();
			
			String fileName = this.service.findInfo(catId, comId).getFileName();
			ResultProcDto result = this.service.delCat(catId);
			
			storageService.delete(fileName, "cat");
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/listCate")
	public  @ResponseBody List<CategoryModel> findListCateByBrand(@RequestParam("brandId") String brandId) {
		try {
			String comId = checkUtil.getComId();
			List<CategoryModel> listCat = service.findByBrand(comId, brandId);
			return listCat;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
