package com.npl.global.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.npl.global.model.user.RoleModel;
import com.npl.global.service.user.UserService;

@Controller
public class UserController {
	
	@Autowired UserService servide;
	
	@GetMapping("/users")
	public String viewHomePage(Model model) {
		List<RoleModel> listRole = servide.findRole();
		model.addAttribute("pageTitle", "Quản lý User");
		model.addAttribute("listRole", listRole);
		return "1010";
	}
}
