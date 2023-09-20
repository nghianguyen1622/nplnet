package com.npl.global.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.npl.global.dto.user.UserDto;
import com.npl.global.model.user.RoleModel;
import com.npl.global.service.user.UserService;

@Controller
public class UserController extends BaseUserController{
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired UserService servide;
	
	@GetMapping("/1010")
	public String viewHomePage(Model model) {
		try {
			UserDto user = new UserDto();
			List<RoleModel> listRole = servide.findRole();
			model.addAttribute("pageTitle", "Quản lý thành viên");
			model.addAttribute("listRole", listRole);
			model.addAttribute("user", user);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "user/1010";
	}
}
