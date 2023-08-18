package com.npl.global;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.npl.global.entity.Company;
import com.npl.global.entity.User;
import com.npl.global.model.settings.ProgramModel;
import com.npl.global.model.settings.UserMenuModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.settings.ProgramService;
import com.npl.global.service.settings.UserMenuService;

@ControllerAdvice
public class MenuController {

	@Autowired
	private UserMenuService menuService;
	
	@Autowired
	private ProgramService prgService;
	
	@ModelAttribute
	public void handleRequest(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("deptText", "");
			model.addAttribute("username", "");
		} else {
			NplUserDetails userDetails = (NplUserDetails) auth.getPrincipal();
			User user = userDetails.getUser();
			
			if (userDetails.hasRole("Admin")) {

				List<ProgramModel> listMenus = prgService.listProgram();
				model.addAttribute("listMenus", listMenus);
			}else {
				Company company = user.getCompany();
			}
		}
	}
}
