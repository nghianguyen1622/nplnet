package com.npl.global.dao.brand;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.npl.global.common.Constant;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.brand.BrandDto;
import com.npl.global.dto.category.CategoryDto;

@Repository
public class BrandDaoExtend {
	
	@Autowired
	private EntityManager entityManager;
	
	public ResultProcDto callBrandSave(BrandDto brandDto){

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_brand_callupin");
		
		query.registerStoredProcedureParameter("p_brand_id",        String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_name",            String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name_org",   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_path",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_list_cate",       String[].class,       ParameterMode.IN);
		query.registerStoredProcedureParameter("p_enabled",         String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_work_user",       String.class,         ParameterMode.IN);

		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
		
		String[] categoryIds = brandDto.getListCateIds();
		if (brandDto.getBrandId() != null) {
			query.setParameter("p_brand_id",                 brandDto.getBrandId());
		} else {
			query.setParameter("p_brand_id",                 "");
		}
		query.setParameter("p_name",                         brandDto.getName());
		query.setParameter("p_file_name",                    brandDto.getFileName());
		query.setParameter("p_file_name_org",                brandDto.getFileNameOrg());
		query.setParameter("p_file_path",                    brandDto.getFilePath());
		query.setParameter("p_list_cate",                    Arrays.asList(categoryIds).toArray());
		query.setParameter("p_enabled",                      brandDto.getEnable());
		query.setParameter("p_com_id",                       brandDto.getComId());
		query.setParameter("p_work_user",                    brandDto.getWorkUser());
		
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
	
	public ResultProcDto callBrandDel(String id) throws Exception{

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_brand_del");
		
		query.registerStoredProcedureParameter("p_brand_id",    String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE, String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR, String.class, ParameterMode.OUT);
		
		query.setParameter("p_brand_id", id);
		
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
