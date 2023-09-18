package com.npl.global.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.npl.global.security.NplUserDetails;

@ControllerAdvice
public class PageControllerAdvice {
	@ModelAttribute
	public void handleRequest(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("deptText", "");
			model.addAttribute("username", "");
		} else {
			NplUserDetails userDetails = (NplUserDetails) auth.getPrincipal();
			if (userDetails.hasRole("Admin")) {
				model.addAttribute("listMenus", userDetails.getMainList());
			}else {
				model.addAttribute("listMenus", userDetails.getMyMenuList());
			}
		}
	}
}
