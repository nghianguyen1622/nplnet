package com.npl.global.service.settings;

import java.util.List;

import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.settings.CompanyDto;
import com.npl.global.model.settings.CompanyModel;

public interface CompanyService {
	
	public List<CompanyModel> getAll();
	
	public CompanyModel findByComId(String comId);
	
	public ResultProcDto save(CompanyDto dto);
	
	public ResultProcDto del(String comId) throws Exception;
}
