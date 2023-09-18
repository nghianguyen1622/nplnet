package com.npl.global.dao.settings;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.npl.global.common.Constant;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.settings.CompanyDto;

@Repository
public class CompanyDaoExtend {
	
	@Autowired
	private EntityManager entityManager;
	
	public ResultProcDto save(CompanyDto dto) {

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("\"PRC_COMPANY\"");
		
		query.registerStoredProcedureParameter("p_com_id",             String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_cd",             String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_name",           String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_addr1",              String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_addr2",              String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_addr_map",           String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_bank_cd",            String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_bank_name",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_owner",              String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_tax_no",             String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_email",              String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_nameOrg",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_path",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_tel",                String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_work_user",          String.class,         ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
		
		if (dto.getComId() != null) {
			query.setParameter("p_com_id",                 dto.getComId());
		} else {
			query.setParameter("p_com_id",                 "");
		}
		query.setParameter("p_com_cd",                            dto.getComCd());
		query.setParameter("p_com_name",                          dto.getComName());
		query.setParameter("p_addr1",                             dto.getAddr1());
		query.setParameter("p_addr2",                             dto.getAddr2());
		query.setParameter("p_addr_map",                          dto.getAddrMap());
		query.setParameter("p_bank_cd",                           dto.getBankCd());
		query.setParameter("p_bank_name",                         dto.getBankName());
		query.setParameter("p_owner",                             dto.getOwner());
		query.setParameter("p_tax_no",                            dto.getTaxNo());
		query.setParameter("p_email",                             dto.getEmail());
		query.setParameter("p_file_name",                         dto.getFileName());
		query.setParameter("p_file_nameOrg",                      dto.getFileNameOrg());
		query.setParameter("p_file_path",                         dto.getFilePath());
		query.setParameter("p_tel",                               dto.getTel());
		query.setParameter("p_work_user",                         dto.getWorkUser());
		
		query.execute();
		
		ResultProcDto resultProcDto = new ResultProcDto();
		if(query.getOutputParameterValue(Constant.SP_RETCODE) != null) {
			resultProcDto.setRetCode(query.getOutputParameterValue(Constant.SP_RETCODE).toString());
		}
		if(query.getOutputParameterValue(Constant.SP_RETSTR) != null) {
			resultProcDto.setRetStr(query.getOutputParameterValue(Constant.SP_RETSTR).toString());
		}
		
		return resultProcDto;

	}
	
	public ResultProcDto del(String id) throws Exception{

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("\"PRC_COMPANY_DEL\"");
		
		query.registerStoredProcedureParameter("p_com_id",    String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE, String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR, String.class, ParameterMode.OUT);
		
		query.setParameter("p_com_id", id);
		
		query.execute();
		
		ResultProcDto resultProcDto = new ResultProcDto();
		if(query.getOutputParameterValue(Constant.SP_RETCODE) != null) {
			resultProcDto.setRetCode(query.getOutputParameterValue(Constant.SP_RETCODE).toString());
		}
		if(query.getOutputParameterValue(Constant.SP_RETSTR) != null) {
			resultProcDto.setRetStr(query.getOutputParameterValue(Constant.SP_RETSTR).toString());
		}
		
		return resultProcDto;
	}
	
}
