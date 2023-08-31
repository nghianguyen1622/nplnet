package com.npl.global.controller.product;

import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.npl.global.common.Constant;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.brand.BrandDto;
import com.npl.global.dto.product.PdtDetailDto;
import com.npl.global.dto.product.PdtDto;
import com.npl.global.model.product.ProductModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.product.ProductService;
import com.npl.global.service.system.StorageService;

@RestController
public class ProductRestController {
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired private ProductService service;
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/2030")
	public  @ResponseBody List<ProductModel> getAll() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			List<ProductModel> listAll = service.findAll(comId);
			return listAll;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping(value = "/2030/save")
	public @ResponseBody ResultProcDto save(@ModelAttribute PdtDto pdtDto) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			
			ResultProcDto result;

			String comId = loggedUser.getUser().getCompany().getComId();
			String workUser = loggedUser.getUser().getWorkUser();
			
			pdtDto.setComId(comId);
			pdtDto.setWorkUser(workUser);
			
			
			if(!pdtDto.getFileMainImage().isEmpty()) {
				String fileName = storageService.store(pdtDto.getFileMainImage(), "pdt");
				
				pdtDto.setFilePath("fileupload/product/" + fileName);
				pdtDto.setFileName(fileName);
				pdtDto.setFileNameOrg(fileName);
				
			}else {
				pdtDto.setFilePath("");
				pdtDto.setFileName("");
				pdtDto.setFileNameOrg("");
			}
			
			result = this.service.savePdt(pdtDto);
			
			if(pdtDto.getFileExtraImage().length > 0) {
				for (MultipartFile multipartFile : pdtDto.getFileExtraImage()) {
					if (!multipartFile.isEmpty()) {
						String fileName = storageService.store(pdtDto.getFileMainImage(), "pdtExtra");
						pdtDto.setFileExtraPath("fileupload/product/extra/" + fileName);
						pdtDto.setFileExtraName(fileName);
						pdtDto.setFileExtraNameOrg(fileName);
						pdtDto.setPdtId(result.getKeyValue());
						
						ResultProcDto result1 = this.service.savePdtImage(pdtDto);
						if(!result1.getRetCode().equals(Constant.RETCODE_OK)) {
							return result1;
						}
					}
				}
			}
			
			if(pdtDto.getPdtDetails().length > 0) {
				for (PdtDetailDto pdtDetailDto : pdtDto.getPdtDetails()) {
					pdtDetailDto.setComId(comId);
					pdtDetailDto.setWorkUser(workUser);
					pdtDetailDto.setPdtId(result.getKeyValue());
					ResultProcDto result2 = this.service.savePdtDetail(pdtDetailDto);
						if(!result2.getRetCode().equals(Constant.RETCODE_OK)) {
							return result2;
						}
				}
			}
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			return null;
		}
	}
	
	@GetMapping("/2030/{pdtId}")
	public  @ResponseBody ProductModel getInfo(@PathVariable(name = "pdtId") String pdtId) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			ProductModel pdtInfo = service.findInfo(pdtId, comId);
			return pdtInfo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/2030/delete/{pdtId}")
	public  @ResponseBody ResultProcDto  delete( @PathVariable(name = "pdtId") String pdtId) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			
			String comId = loggedUser.getUser().getCompany().getComId();
			
//			String fileName = this.service.findInfo(pdtId, comId).getFileName();
			ResultProcDto result = this.service.delPdt(pdtId);
			
//			storageService.delete(fileName, "product");
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

}
