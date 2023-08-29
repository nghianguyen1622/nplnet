package com.npl.global.dao.product;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.npl.global.common.Constant;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.product.PdtDto;
import com.npl.global.dto.user.UserDto;
import com.npl.global.security.NplUserDetails;

@Repository
public class ProductDaoExtends {
	
	@Autowired
	private EntityManager entityManager;
	
	public ResultProcDto callPdtSave(PdtDto pdtDto) throws Exception {
	
		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("product.prc_pdt_allupin");
		
		query.registerStoredProcedureParameter("p_pdt_id",                  String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_id",                  String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_name",                    String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_alias",                   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_short_description",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_full_description",        String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_enabled",                 String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_edit_yn",                 String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_del_yn",                  String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_in_stock",                String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_cost",                    BigDecimal.class,     ParameterMode.IN);
		query.registerStoredProcedureParameter("p_price",                   BigDecimal.class,     ParameterMode.IN);
		query.registerStoredProcedureParameter("p_discount_percent",        BigDecimal.class,     ParameterMode.IN);
		query.registerStoredProcedureParameter("p_length",                  BigDecimal.class,     ParameterMode.IN);
		query.registerStoredProcedureParameter("p_width",                   BigDecimal.class,     ParameterMode.IN);
		query.registerStoredProcedureParameter("p_height",                  BigDecimal.class,     ParameterMode.IN);
		query.registerStoredProcedureParameter("p_weight",                  BigDecimal.class,     ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name",               String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name_org",           String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_path",               String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_category_id",             String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_brand_id",                String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_work_User",               String.class,         ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_KEYVALUE,      String.class, ParameterMode.OUT);
		
		if (pdtDto.getPdtId() != null) {
		    query.setParameter("p_pdt_id", pdtDto.getPdtId());
		} else {
		    query.setParameter("p_pdt_id", "");
		}
		query.setParameter("p_com_id",                   pdtDto.getComId());
		query.setParameter("p_name",                     pdtDto.getName());
		query.setParameter("p_alias",                    pdtDto.getAlias());
		query.setParameter("p_short_description",        pdtDto.getShortDes());
		query.setParameter("p_full_description",         pdtDto.getFullDes());
		query.setParameter("p_enabled",                  pdtDto.getEnabled());
		query.setParameter("p_edit_yn",                  pdtDto.getEditYn());
		query.setParameter("p_del_yn",                   pdtDto.getDelYn());
		query.setParameter("p_in_stock",                 pdtDto.getInStock());
		query.setParameter("p_cost",                     pdtDto.getCost());
		query.setParameter("p_price",                    pdtDto.getPrice());
		query.setParameter("p_discount_percent",         pdtDto.getDisPer());
		query.setParameter("p_length",                   pdtDto.getLength());
		query.setParameter("p_width",                    pdtDto.getWidth());
		query.setParameter("p_height",                   pdtDto.getHeight());
		query.setParameter("p_weight",                   pdtDto.getWeight());
		query.setParameter("p_file_name",                pdtDto.getFileName());
		query.setParameter("p_file_name_org",            pdtDto.getFileNameOrg());
		query.setParameter("p_file_path",                pdtDto.getFilePath());
		query.setParameter("p_category_id",              pdtDto.getCategoryId());
		query.setParameter("p_brand_id",                 pdtDto.getBrandId());
		query.setParameter("p_work_User",                pdtDto.getWorkUser());
		
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
	
	public ResultProcDto callPdtDel(String id) throws Exception{

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_pdt_del");
		
		query.registerStoredProcedureParameter("p_pdt_id",    String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE, String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR, String.class, ParameterMode.OUT);
		
		query.setParameter("p_pdt_id", id);
		
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
	
public ResultProcDto callPdtImage(PdtDto pdtDto){
		
		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("public.prc_pdt_image");
		
		query.registerStoredProcedureParameter("p_pdt_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_path",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name_org",   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_work_user",       String.class,         ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
		
		query.setParameter("p_pdt_id",                     pdtDto.getPdtId());
		query.setParameter("p_com_id",                      pdtDto.getComId());
		query.setParameter("p_file_path",                   pdtDto.getFileExtraPath());
		query.setParameter("p_file_name",                   pdtDto.getFileExtraName());
		query.setParameter("p_file_name_org",               pdtDto.getFileExtraNameOrg());
		query.setParameter("p_work_user",                   pdtDto.getWorkUser());
		
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
