package com.npl.global.service.settings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.settings.ProgramDao;
import com.npl.global.dao.settings.ProgramDaoExtend;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.settings.ProgramDto;
import com.npl.global.model.settings.ProgramModel;

@Service
public class ProgramServiceImpl implements ProgramService{

	@Autowired
	private ProgramDao dao;
	
	@Autowired
	private ProgramDaoExtend daoExtend;
	
	@Override
	public List<ProgramDto> allPrograms() {
		List<ProgramModel> prgList = this.dao.allPrograms();
		
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
			menu.setIcon(info.getIcon());
			
			menuDtos.add(menu);
		}
		
		return menuDtos;
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
			menu.setIcon(info.getIcon());
			
			menuDtos.add(menu);
		}
		
		return menuDtos;
	}

	@Override
	public ResultProcDto save(ProgramDto dto) {
		// TODO Auto-generated method stub
		return daoExtend.save(dto);
	}

	@Override
	public ResultProcDto del(String prgCd) throws Exception {
		// TODO Auto-generated method stub
		return daoExtend.del(prgCd);
	}

	@Override
	public List<ProgramModel> listProgram() {
		// TODO Auto-generated method stub
		return this.dao.getListPrograms();
	}

}
