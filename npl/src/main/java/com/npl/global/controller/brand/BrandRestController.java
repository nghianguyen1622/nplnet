package com.npl.global.controller.brand;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.npl.global.common.CheckUtil;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.brand.BrandDto;
import com.npl.global.model.brand.BrandModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.brand.BrandService;
import com.npl.global.service.system.StorageService;

@RestController
public class BrandRestController extends BaseBrandController{
	private Logger logger = LoggerFactory.getLogger(BrandRestController.class);
	
	@Autowired private BrandService service;
	
	@Autowired private CheckUtil checkUtil;
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/2010/list")
	public  @ResponseBody List<BrandModel> getAll() {
		try {
			String comId = checkUtil.getComId();
			List<BrandModel> listBrand = service.findAll(comId);
			return listBrand;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/2010/{id}")
	public  @ResponseBody BrandModel getInfo(@PathVariable(name = "id") String brandId) {
		try {
			String comId = checkUtil.getComId();
			BrandModel brandInfo = service.findInfo(brandId, comId);
			return brandInfo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping(value = "/2010/save")
	public @ResponseBody ResultProcDto save(@ModelAttribute BrandDto brandDto) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			
			ResultProcDto result;

			String comId = loggedUser.getUser().getCompany().getComId();
			String workUser = loggedUser.getUser().getWorkUser();
			
			brandDto.setComId(comId);
			brandDto.setWorkUser(workUser);
			
			
			if(!brandDto.getFileData().isEmpty()) {
				if(!brandDto.getBrandId().isEmpty()) {
					storageService.delete(service.findInfo(brandDto.getBrandId(), comId).getFileName(), "brand");
				}
				String fileName = storageService.store(brandDto.getFileData(), "brand");
				
				brandDto.setFilePath("fileupload/brand/" + fileName);
				brandDto.setFileName(fileName);
				brandDto.setFileNameOrg(fileName);
				
			}else {
				brandDto.setFilePath("");
				brandDto.setFileName("");
				brandDto.setFileNameOrg("");
			}
			result = this.service.saveBrand(brandDto);
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			return null;
		}
	}
	
	@RequestMapping(value = "/2010/delete/{brandId}")
	public  @ResponseBody ResultProcDto  delete( @PathVariable(name = "brandId") String brandId) {
		try {
			String comId = checkUtil.getComId();
			
			String fileName = this.service.findInfo(brandId, comId).getFileName();
			ResultProcDto result = this.service.delBrand(brandId);
			
			storageService.delete(fileName, "brand");
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
