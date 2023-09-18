package com.npl.global.controller.company;

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

import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.settings.CompanyDto;
import com.npl.global.model.settings.CompanyModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.settings.CompanyService;
import com.npl.global.service.system.StorageService;

@RestController
public class CompanyRestController {
private Logger logger = LoggerFactory.getLogger(CompanyRestController.class);
	
	@Autowired private CompanyService service;
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/6020")
	public  @ResponseBody List<CompanyModel> getAll() {
		try {
			List<CompanyModel> list = service.getAll();
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/6020/{id}")
	public  @ResponseBody CompanyModel getInfo(@PathVariable(name = "id") String comId) {
		try {
			CompanyModel info = service.findByComId(comId);
			return info;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping(value = "/6020/save")
	public @ResponseBody ResultProcDto save(@ModelAttribute CompanyDto dto) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			
			ResultProcDto result;

			String workUser = loggedUser.getUser().getWorkUser();
			
			dto.setWorkUser(workUser);
			
			
			if(!dto.getFileData().isEmpty()) {
//				if(!dto.getBrandId().isEmpty()) {
//					storageService.delete(service.findInfo(dto.getBrandId(), comId).getFileName(), "brand");
//				}
				String fileName = storageService.store(dto.getFileData(), "company");
				
				dto.setFilePath("fileupload/company/" + fileName);
				dto.setFileName(fileName);
				dto.setFileNameOrg(fileName);
				
			}else {
				dto.setFilePath("");
				dto.setFileName("");
				dto.setFileNameOrg("");
			}
			result = this.service.save(dto);
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			return null;
		}
	}
	
	@RequestMapping(value = "/6020/delete/{comId}")
	public  @ResponseBody ResultProcDto  delete( @PathVariable(name = "comId") String comId) {
		try {
			
			String fileName = this.service.findByComId(comId).getFileName();
			ResultProcDto result = this.service.del(comId);
			
			storageService.delete(fileName, "company");
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
