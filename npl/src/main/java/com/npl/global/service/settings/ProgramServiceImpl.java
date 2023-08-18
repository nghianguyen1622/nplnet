package com.npl.global.service.settings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.settings.ProgramDao;
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

}
