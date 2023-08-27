package com.npl.global.dao.user;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.npl.global.common.Constant;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.user.UserDto;

@Repository
public class UserDaoExtend {
	@Autowired
	private EntityManager entityManager;
	
	public ResultProcDto callSaveUser(UserDto userDto){

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_user_callupin");
		
		query.registerStoredProcedureParameter("p_user_id",         String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_user_name",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_passwd",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_check_pass",      String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_email",           String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_birth_day",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_addr",            String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_phone",           String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_identity_card",   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_del_yn",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_edit_yn",         String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_enabled",         Boolean.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_use_yn",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_work_user",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_role",            Integer.class,         ParameterMode.IN);

		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_KEYVALUE,      String.class, ParameterMode.OUT);
	
		if (userDto.getUserId() != null) {
		    query.setParameter("p_user_id", userDto.getUserId());
		} else {
		    query.setParameter("p_user_id", "");
		}
		query.setParameter("p_user_name",                   userDto.getUserName());
		query.setParameter("p_passwd",                      userDto.getPasswd());
		query.setParameter("p_check_pass",                  userDto.getCheckPw());
		query.setParameter("p_email",                       userDto.getEmail());
		query.setParameter("p_birth_day",                   userDto.getBirtDay());
		query.setParameter("p_addr",                        userDto.getAddr());
		query.setParameter("p_phone",                       userDto.getPhone());
		query.setParameter("p_identity_card",               userDto.getIdenCard());
		query.setParameter("p_del_yn",                      userDto.getDelYN());
		query.setParameter("p_edit_yn",                     userDto.getEditYN());
		query.setParameter("p_enabled",                     userDto.getEnable());
		query.setParameter("p_use_yn",                      userDto.getUseYN());
		query.setParameter("p_com_id",                      userDto.getComId());
		query.setParameter("p_work_user",                   userDto.getWorkUser());
		query.setParameter("p_role",                        userDto.getRoleId());
		
		query.execute();
		
		ResultProcDto resultProcDto = new ResultProcDto();
		if(query.getOutputParameterValue(Constant.SP_RETCODE) != null) {
			resultProcDto.setRetCode(query.getOutputParameterValue(Constant.SP_RETCODE).toString());
		}
		if(query.getOutputParameterValue(Constant.SP_RETSTR) != null) {
			resultProcDto.setRetStr(query.getOutputParameterValue(Constant.SP_RETSTR).toString());
		}
		if (query.getOutputParameterValue(Constant.SP_KEYVALUE) != null) {
			resultProcDto.setKeyValue(query.getOutputParameterValue(Constant.SP_KEYVALUE).toString());
		}
		
		return resultProcDto;

	}
	
	public ResultProcDto callDellUser(String userId) throws Exception{

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_user_del");
		
		query.registerStoredProcedureParameter("p_user_id",    String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE, String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR, String.class, ParameterMode.OUT);
		
		query.setParameter("p_user_id", userId);
		
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
	
	public ResultProcDto callUserImage(UserDto userDto){
		
		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_user_image");
		
		query.registerStoredProcedureParameter("p_user_id",         String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_kind_cd",         String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_path",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name_org",   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_work_user",       String.class,         ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
		
		query.setParameter("p_user_id",                     userDto.getUserId());
		query.setParameter("p_com_id",                      userDto.getComId());
		query.setParameter("p_kind_cd",                     userDto.getKindCD());
		query.setParameter("p_file_path",                   userDto.getFilePath());
		query.setParameter("p_file_name",                   userDto.getFileName());
		query.setParameter("p_file_name_org",               userDto.getFileNameOrg());
		query.setParameter("p_work_user",                   userDto.getWorkUser());
		
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
