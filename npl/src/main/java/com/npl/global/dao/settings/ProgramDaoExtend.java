package com.npl.global.dao.settings;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.npl.global.common.Constant;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.settings.ProgramDto;

@Repository
public class ProgramDaoExtend {
	
	@Autowired
	private EntityManager entityManager;
	
	public ResultProcDto save(ProgramDto dto) {

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("\"PRC_PRG\"");
		
		query.registerStoredProcedureParameter("p_prg_cd",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_del_yn",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_exp_imp_yn",   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_menu_cd",      String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_menu_name",    String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_prg_kind",     String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_help_url",     String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_use_Yn",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_view_yn",      String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_lv",           BigDecimal.class,     ParameterMode.IN);
		query.registerStoredProcedureParameter("p_upd_yn",       String.class,         ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
		
		if (dto.getPrgCd() != null) {
			query.setParameter("p_prg_cd",                 dto.getPrgCd());
		} else {
			query.setParameter("p_prg_cd",                 "");
		}
		query.setParameter("p_del_yn",                         dto.getDeleteYn());
		query.setParameter("p_exp_imp_yn",                     dto.getExpImpYn());
		query.setParameter("p_menu_cd",                        dto.getMenuCd());
		query.setParameter("p_menu_name",                      dto.getMenuName());
		query.setParameter("p_prg_kind",                       dto.getPrgKind());
		query.setParameter("p_help_url",                       dto.getHelpUrl());
		query.setParameter("p_use_Yn",                         dto.getUseYn());
		query.setParameter("p_view_yn",                        dto.getViewYn());
		query.setParameter("p_lv",                             dto.getLv());
		query.setParameter("p_upd_yn",                         dto.getUpdateYn());
		
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

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("\"PRC_PRG_DEL\"");
		
		query.registerStoredProcedureParameter("p_prg_cd",    String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE, String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR, String.class, ParameterMode.OUT);
		
		query.setParameter("p_prg_cd", id);
		
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
