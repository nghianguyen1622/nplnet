package com.npl.global.service.settings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.npl.global.dao.settings.CompanyDao;
import com.npl.global.dao.settings.CompanyDaoExtend;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.settings.CompanyDto;
import com.npl.global.model.settings.CompanyModel;

public class CompanyServiceImpl implements CompanyService {
	
	@Autowired private CompanyDao dao;
	@Autowired private CompanyDaoExtend daoExtend;

	@Override
	public List<CompanyModel> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public CompanyModel findByComId(String comId) {
		// TODO Auto-generated method stub
		return dao.getByComId(comId);
	}

	@Override
	public ResultProcDto save(CompanyDto dto) {
		// TODO Auto-generated method stub
		return daoExtend.save(dto);
	}

	@Override
	public ResultProcDto del(String comId) throws Exception {
		// TODO Auto-generated method stub
		return daoExtend.del(comId);
	}

}
