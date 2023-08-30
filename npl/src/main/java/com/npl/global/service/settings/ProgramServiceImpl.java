package com.npl.global.service.settings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.settings.ProgramDao;
import com.npl.global.dto.settings.ProgramDto;
import com.npl.global.model.settings.ProgramModel;

@Service
public class ProgramServiceImpl implements ProgramService{

	@Autowired
	private ProgramDao dao;
	
	@Override
	public List<ProgramModel> listProgram() {
		// TODO Auto-generated method stub
		return this.dao.getListPrograms();
	}

	@Override
	public List<ProgramDto> findMenuByUser(String userid) {
		List<ProgramModel> prgList = this.dao.findMenuByUser(userid);
		
		List<ProgramDto> menuDtos = new ArrayList<>();
		for(ProgramModel info: prgList) {
			ProgramDto menu = new ProgramDto();

			menu.setPrgCd(info.getPrgCd());
			menu.setPrgKind(info.getPrgKind());
			menu.setMenuCd(info.getMenuCd());
			menu.setMenuName(info.getMenuName());
			menu.setFormNo(info.getFormNo());
			menu.setFormName(info.getFormName());
			menu.setUserid(info.getUserId());
			menu.setHelpUrl(info.getHelpUrl());
			menu.setDeleteYn(info.getDeleteYn());
			menu.setExpImpYn(info.getExpImpYn());
			menu.setInsertYn(info.getInsertYn());
			menu.setUpdateYn(info.getUpdateYn());
			menu.setUseYn(info.getUseYn());
			menu.setPrintYn(info.getPrintYn());
			
			menuDtos.add(menu);
		}
		
		return menuDtos;
	}

}
