package com.npl.global.controller.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.npl.global.common.CheckUtil;

@Controller
public class NoticeController extends BaseOmnitrionController{

	
	@Autowired
    private CheckUtil checkYN;

	@GetMapping("/4020")
	public String viewPage(Model model) {
		String urlHepl = "/omnitrion/4020";
		String htmlNo = "omnitrion/4020";
		model.addAttribute("pageTitle", "Quản lý thông báo");
		return checkYN.canAccess(urlHepl, htmlNo);
	}

}
