package com.npl.global.dao.category;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.npl.global.common.Constant;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.category.CategoryDto;

@Repository
public class CategoryDaoExtend {
	
	@Autowired
	private EntityManager entityManager;
	
	public ResultProcDto callCateSave(CategoryDto cateDto){

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_category_callupin");
		
		query.registerStoredProcedureParameter("p_cat_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_name",            String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_alias",           String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name_org",   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_path",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_parent_id",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_all_parent_id",   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_enabled",         String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_work_user",       String.class,         ParameterMode.IN);

		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
	
		if (cateDto.getCatId() != null) {
		    query.setParameter("p_cat_id", cateDto.getCatId());
		} else {
		    query.setParameter("p_cat_id", "");
		}
		query.setParameter("p_name",                         cateDto.getName());
		query.setParameter("p_alias",                        cateDto.getAlias());
		query.setParameter("p_file_name",                    cateDto.getFileName());
		query.setParameter("p_file_name_org",                cateDto.getFileNameOrg());
		query.setParameter("p_file_path",                    cateDto.getFilePath());
		query.setParameter("p_parent_id",                    cateDto.getParentId());
		query.setParameter("p_all_parent_id",                cateDto.getAllParentId());
		query.setParameter("p_enabled",                      cateDto.getEnable());
		query.setParameter("p_com_id",                       cateDto.getComId());
		query.setParameter("p_work_user",                    cateDto.getWorkUser());
		
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
	
	public ResultProcDto callCatDel(String id) throws Exception{

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_cat_del");
		
		query.registerStoredProcedureParameter("p_cat_id",    String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE, String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR, String.class, ParameterMode.OUT);
		
		query.setParameter("p_cat_id", id);
		
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
	
public ResultProcDto callCatImage(CategoryDto catDto){
		
		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_cat_image");
		
		query.registerStoredProcedureParameter("p_cat_id",         String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_kind_cd",         String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_path",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name_org",   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_work_user",       String.class,         ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
		
		query.setParameter("p_cat_id",                     catDto.getCatId());
		query.setParameter("p_com_id",                      catDto.getComId());
		query.setParameter("p_kind_cd",                     catDto.getKindCd());
		query.setParameter("p_file_path",                   catDto.getFilePath());
		query.setParameter("p_file_name",                   catDto.getFileName());
		query.setParameter("p_file_name_org",               catDto.getFileNameOrg());
		query.setParameter("p_work_user",                   catDto.getWorkUser());
		
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
