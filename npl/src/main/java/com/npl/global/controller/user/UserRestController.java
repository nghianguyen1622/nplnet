package com.npl.global.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.user.UserDto;
import com.npl.global.model.user.UserModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.user.UserService;

@RestController
public class UserRestController {

	@Autowired private UserService service;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/1010")
	public  @ResponseBody List<UserModel> getAll() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			List<UserModel> listUser = service.findAll(comId);
			return listUser;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/1010/{id}")
	public  @ResponseBody UserModel getInfo(@PathVariable(name = "id") String userId) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			UserModel userInfo = service.findUserName(userId);
			return userInfo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping(value = "/1010/save")
	public @ResponseBody ResultProcDto save(@RequestBody UserDto userSave) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
	   	 	
	   	 	
			String comId = loggedUser.getUser().getCompany().getComId();
			String workUser = loggedUser.getUser().getWorkUser();
			
			userSave.setComId(comId);
			userSave.setWorkUser(workUser);
	   	    ResultProcDto result = this.service.saveUser(userSave);
	   	
			return result;
		} catch (Exception e) {
			//logger.error(e.getMessage());
			
			return null;
		}
	}
	
	@RequestMapping(value = "/1010/delete/{id}")
	public  @ResponseBody ResultProcDto  delete( @PathVariable(name = "id") String userId) {
		try {
			ResultProcDto result = this.service.delUser(userId);
			//return
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
}
