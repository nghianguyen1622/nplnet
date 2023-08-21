package com.npl.global.dao.notice;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.npl.global.common.Constant;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.notice.NoticeDto;

@Repository
public class NoticeDaoExtend {
	
	@Autowired
	private EntityManager entityManager;
	
	public ResultProcDto callNoticeAdd(NoticeDto noticeDto){

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_notice_callupin");
		
		query.registerStoredProcedureParameter("p_id",              String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_title",           String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_content",         String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_s_date",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_e_date",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_enabled",         String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_popup",           String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_work_user",       String.class,         ParameterMode.IN);

		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
	
		if (noticeDto.getNtId() != null) {
		    query.setParameter("p_id", noticeDto.getNtId());
		} else {
		    query.setParameter("p_id", "");
		}
		query.setParameter("p_com_id",                   noticeDto.getComId());
		query.setParameter("p_title",                    noticeDto.getTitle());
		query.setParameter("p_content",                  noticeDto.getContent());
		query.setParameter("p_s_date",                   noticeDto.getsDate());
		query.setParameter("p_e_date",                   noticeDto.geteDate());
		query.setParameter("p_enabled",                  "Y");
		query.setParameter("p_popup",                    "Y");
		query.setParameter("p_work_user",                noticeDto.getWokUser());
		
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
	
	public ResultProcDto callNoticeDel(String id) throws Exception{

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_notice_del");
		
		query.registerStoredProcedureParameter("p_id",    String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE, String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR, String.class, ParameterMode.OUT);
		
		query.setParameter("p_id", id);
		
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
