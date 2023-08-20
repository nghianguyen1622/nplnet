package com.npl.global.controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.notice.NoticeDto;
import com.npl.global.entity.Company;
import com.npl.global.model.notice.NoticeModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.notice.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired private NoticeService service;
	
	private Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@GetMapping("/notice")
	public String viewHomePage(Model model) {
		model.addAttribute("pageTitle", "Quản lý thông báo");
		return "8010";
	}
	
	@GetMapping("/notice/8010")
	public  @ResponseBody List<NoticeModel> getAll() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String company = loggedUser.getUser().getCompany().getComId();
			List<NoticeModel> listNotice = service.findAll(company);
			return listNotice;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/8010/{id1}")
	public  @ResponseBody NoticeModel getInfo(@PathVariable(name = "id") String id) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			NoticeModel noticeInfo = service.noticeInfo(comId, id);
			return noticeInfo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping(value = "/board/save")
	public @ResponseBody ResultProcDto save(Model model, @RequestBody NoticeDto NoticeAddSave) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
	   	 	
	   	 	
			String comId = loggedUser.getUser().getCompany().getComId();
			String workUser = loggedUser.getUser().getWorkUser();
			
			NoticeAddSave.setComId(comId);
			NoticeAddSave.setWokUser(workUser);
	   	  	System.out.println("step :1 ★★★★★★★");
	   	    ResultProcDto result = this.service.saveAddNotice(NoticeAddSave);
	   	
			model.addAttribute("msgsuccess", result.getRetStr());
			
			System.out.println("step :5 ★★★★★★★");
			
			return result;
		} catch (Exception e) {
			//logger.error(e.getMessage());
			model.addAttribute("msgerr", e.getMessage());	
			
			return null;
		}
	}
	
	@RequestMapping(value = "/8010/upd", method = RequestMethod.POST)
	public @ResponseBody ResultProcDto upd(Model model, @ModelAttribute("NoticeAddSave") NoticeDto NoticeAddSave) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			
			
			String comId = loggedUser.getUser().getCompany().getComId();
			String workUser = loggedUser.getUser().getWorkUser();
			
			NoticeAddSave.setComId(comId);
			NoticeAddSave.setWokUser(workUser);
			System.out.println("step :1 ★★★★★★★");
			ResultProcDto result = this.service.updNotice(NoticeAddSave);
			
			model.addAttribute("msgsuccess", result.getRetStr());
			
			System.out.println("step :5 ★★★★★★★");
			
			return result;
		} catch (Exception e) {
			//logger.error(e.getMessage());
			model.addAttribute("msgerr", e.getMessage());	
			
			return null;
		}
	}
	
	@RequestMapping(value = "/8010/delete/{id}")
	public  @ResponseBody ResultProcDto  delete(Model model, @PathVariable(name = "id") String boardNo) {
		try {
	   	  	
			ResultProcDto result = this.service.delNotice(boardNo);

			model.addAttribute("msgsuccess", result.getRetStr());
			
			//return
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute("msgerr", e.getMessage());	
			
			return null;
		}
	}
}
