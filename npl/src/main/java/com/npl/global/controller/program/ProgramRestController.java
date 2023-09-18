package com.npl.global.controller.program;

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
import com.npl.global.dto.settings.ProgramDto;
import com.npl.global.model.settings.ProgramModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.settings.ProgramService;

@RestController
public class ProgramRestController {
	private Logger logger = LoggerFactory.getLogger(ProgramRestController.class);
	
	@Autowired private ProgramService service;
	
	@GetMapping("/6030")
	public  @ResponseBody List<ProgramModel> getAll() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			List<ProgramModel> listAll = service.listProgram();
			return listAll;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@GetMapping("/6030/{id}")
	public  @ResponseBody ProgramModel getInfo(@PathVariable(name = "id") String prgId) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String userId = loggedUser.getUser().getUserId();
			//ProgramModel programInfo = service.?;
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping(value = "/6030/save")
	public @ResponseBody ResultProcDto save(@ModelAttribute ProgramDto prgDto) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			
			String comId = loggedUser.getUser().getCompany().getComId();
			String workUser = loggedUser.getUser().getWorkUser();
			
			ResultProcDto result = this.service.save(prgDto);
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			return null;
		}
	}
	
	@RequestMapping(value = "/6030/delete/{prgId}")
	public  @ResponseBody ResultProcDto  delete( @PathVariable(name = "prgId") String prgId) {
		try {
			ResultProcDto result = this.service.del(prgId);
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
