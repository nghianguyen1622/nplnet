package com.npl.global.controller.notice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.notice.NoticeDto;
import com.npl.global.model.notice.NoticeModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.notice.NoticeService;

@RestController
public class NoticeRestController {

@Autowired private NoticeService service;
	
	private Logger logger = LoggerFactory.getLogger(NoticeController.class);

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
	
	@GetMapping("/8010/{id}")
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
	public @ResponseBody ResultProcDto save(@RequestBody NoticeDto NoticeAddSave) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
	   	 	
	   	 	
			String comId = loggedUser.getUser().getCompany().getComId();
			String workUser = loggedUser.getUser().getWorkUser();
			
			NoticeAddSave.setComId(comId);
			NoticeAddSave.setWokUser(workUser);
	   	    ResultProcDto result = this.service.saveAddNotice(NoticeAddSave);
	   	
			return result;
		} catch (Exception e) {
			//logger.error(e.getMessage());
			
			return null;
		}
	}
	
	@RequestMapping(value = "/8010/delete/{id}")
	public  @ResponseBody ResultProcDto  delete( @PathVariable(name = "id") String boardNo) {
		try {
			ResultProcDto result = this.service.delNotice(boardNo);
			//return
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
