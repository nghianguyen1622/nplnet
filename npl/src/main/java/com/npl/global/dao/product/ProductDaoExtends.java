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
import com.npl.global.security.NplUserDetails;

@Repository
public class ProductDaoExtends {
	
	@Autowired
	private EntityManager entityManager;
	
public ResultProcDto callPdtBomSP(PdtDto pdtDto) throws Exception {
	
		
		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("product.create");
		
		query.registerStoredProcedureParameter("SP_Com_ID",                  String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_name",                    String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_alias",                   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_short_description",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_full_description",        String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_enabled",                 String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_edit_yn",                 String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_del_yn",                  String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_in_stock",                String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_cost",                BigDecimal.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_price",               BigDecimal.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_discount_percent",    BigDecimal.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_length",              BigDecimal.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_width",               BigDecimal.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_height",              BigDecimal.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_weight",              BigDecimal.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_main_image",              String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_category_id",             String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_brand_id",                String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("SP_work_User",               String.class,         ParameterMode.IN);
		
//		query.registerStoredProcedureParameter(Constant.SP_LOG_KIND,      String.class, ParameterMode.IN);
//		query.registerStoredProcedureParameter(Constant.SP_FORM_NO,       String.class, ParameterMode.IN);
//		query.registerStoredProcedureParameter(Constant.SP_BTN_NAME,      String.class, ParameterMode.IN);
//		query.registerStoredProcedureParameter(Constant.SP_PC_NAME,       String.class, ParameterMode.IN);
//		query.registerStoredProcedureParameter(Constant.SP_PC_USER,       String.class, ParameterMode.IN);
//		query.registerStoredProcedureParameter(Constant.SP_PC_KIND,       String.class, ParameterMode.IN);
//		query.registerStoredProcedureParameter(Constant.SP_IP_ADDR,       String.class, ParameterMode.IN);
//		query.registerStoredProcedureParameter(Constant.SP_MAC_ADDR,      String.class, ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
		
		
		query.setParameter("SP_Com_ID",                   pdtDto.getComId());
		query.setParameter("SP_name",                     pdtDto.getName());
		query.setParameter("SP_alias",                    pdtDto.getAlias());
		query.setParameter("SP_short_description",        pdtDto.getShortDes());
		query.setParameter("SP_full_description",         pdtDto.getFullDes());
		query.setParameter("SP_enabled",                  pdtDto.getEnabled());
		query.setParameter("SP_edit_yn",                  pdtDto.getEditYn());
		query.setParameter("SP_del_yn",                   pdtDto.getDelYn());
		query.setParameter("SP_in_stock",                 pdtDto.getInStock());
		query.setParameter("SP_cost",                     pdtDto.getCost());
		query.setParameter("SP_price",                    pdtDto.getPrice());
		query.setParameter("SP_discount_percent",         pdtDto.getDisPer());
		query.setParameter("SP_length",                   pdtDto.getLength());
		query.setParameter("SP_width",                    pdtDto.getWidth());
		query.setParameter("SP_height",                   pdtDto.getHeight());
		query.setParameter("SP_weight",                   pdtDto.getWeight());
		query.setParameter("SP_main_image",               pdtDto.getMainImage());
		query.setParameter("SP_category_id",              pdtDto.getCategoryId());
		query.setParameter("SP_brand_id",                 pdtDto.getBrandId());
		query.setParameter("SP_work_User",                pdtDto.getWorkUser());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
		
//		query.setParameter(Constant.SP_FORM_NO, "8020");
//		query.setParameter(Constant.SP_BTN_NAME, Constant.BTN_UPD);
//		query.setParameter(Constant.SP_PC_NAME, loggedUser.getBrowserName());
//		query.setParameter(Constant.SP_PC_USER, loggedUser.getBrowserVersion());
//		query.setParameter(Constant.SP_PC_KIND, loggedUser.getDeviceName());
//		query.setParameter(Constant.SP_IP_ADDR, loggedUser.getIpAddress());
//		query.setParameter(Constant.SP_MAC_ADDR, loggedUser.getMacAddress());	

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
