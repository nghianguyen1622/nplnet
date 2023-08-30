package com.npl.global;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.npl.global.config.InfoPcClientUtil;
import com.npl.global.dto.settings.ProgramDto;
import com.npl.global.entity.Company;
import com.npl.global.entity.User;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.settings.UserMenuService;
import com.npl.global.service.user.UserService;

import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

@Controller
public class MainController {

	@Autowired
	private UserMenuService menuService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("pageTitle", "Trang chủ");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails userDetails = (NplUserDetails) auth.getPrincipal();
		System.out.println("===================  : " +userDetails.getMyMenuList());

		return "index";
	}

	@GetMapping("/login")
	public String viewLoginPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			InfoPcClientUtil.setIpAddress(request.getRemoteAddr());
			UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
			String userAgent = request.getHeader("User-Agent");

			if (userAgent != null) {
				InfoPcClientUtil.setBrowserName(parser.parse(userAgent).getName());
				InfoPcClientUtil.setBrowserVersion(parser.parse(userAgent).getVersionNumber().toVersionString());
			}
			
			if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
				return "login";
			}
			

			model.addAttribute("pageTitle", "Đăng nhập");
		}

		return "redirect:/";
	}

	@GetMapping("/mainList")
	public @ResponseBody List<ProgramDto> mainList() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails userDetails = (NplUserDetails) auth.getPrincipal();

		Company comId = userDetails.getUser().getCompany();
		User user = userDetails.getUser();

		List<ProgramDto> mainList = userDetails.getMainList();

		return mainList;
	}
}
