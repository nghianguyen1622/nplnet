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
import com.npl.global.dto.product.PdtDetailDto;
import com.npl.global.dto.product.PdtDto;
import com.npl.global.dto.user.UserDto;
import com.npl.global.security.NplUserDetails;

@Repository
public class ProductDaoExtends {
	
	@Autowired
	private EntityManager entityManager;
	
	public ResultProcDto callPdtSave(PdtDto pdtDto) throws Exception {
	
		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("\"PRC_PDT\"");
		
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
		query.registerStoredProcedureParameter("p_qty",                     BigDecimal.class,     ParameterMode.IN);
		query.registerStoredProcedureParameter("p_pdt_kind",                String.class,         ParameterMode.IN);
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
		query.setParameter("p_qty",                      pdtDto.getQty());
		query.setParameter("p_pdt_kind",                 pdtDto.getPdtKind());
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

		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("\"PRC_PDT_DEL\"");
		
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
		
		final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("\"PRC_PDT_IMG\"");
		
		query.registerStoredProcedureParameter("p_reg_no",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_pdt_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_com_id",          String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_path",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name",       String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_file_name_org",   String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_image_kind",      String.class,         ParameterMode.IN);
		query.registerStoredProcedureParameter("p_sort_no",         Integer.class,        ParameterMode.IN);
		query.registerStoredProcedureParameter("p_work_user",       String.class,         ParameterMode.IN);
		
		query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
		
		if(pdtDto.getRegNo() == null || pdtDto.getRegNo() == "" || pdtDto.getRegNo().isEmpty()) {
			pdtDto.setRegNo("0");
		}
		query.setParameter("p_reg_no",                      pdtDto.getRegNo());
		query.setParameter("p_pdt_id",                      pdtDto.getPdtId());
		query.setParameter("p_com_id",                      pdtDto.getComId());
		query.setParameter("p_file_path",                   pdtDto.getFilePath());
		query.setParameter("p_file_name",                   pdtDto.getFileName());
		query.setParameter("p_file_name_org",               pdtDto.getFileNameOrg());
		query.setParameter("p_image_kind",                  "LARGE");
		query.setParameter("p_sort_no",                     pdtDto.getSortNo());
		query.setParameter("p_work_user",                   pdtDto.getWorkUser());
		
		query.execute();
		
		ResultProcDto resultProcDto = new ResultProcDto();
		if(query.getOutputParameterValue(Constant.SP_RETCODE) != null) {
			resultProcDto.setRetCode(query.getOutputParameterValue(Constant.SP_RETCODE).toString());
		}
		if(query.getOutputParameterValue(Constant.SP_RETSTR) != null) {
			resultProcDto.setRetStr(query.getOutputParameterValue(Constant.SP_RETSTR).toString());
		}
		System.out.println("SP_RETCODE :"+ query.getOutputParameterValue(Constant.SP_RETCODE).toString());
		System.out.println("SP_RETSTR  :"+ query.getOutputParameterValue(Constant.SP_RETSTR).toString());
		
		return resultProcDto;
		
	}

public ResultProcDto callPdtDetail(PdtDetailDto detailDto){
	
	final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("\"PRC_PDT_DETAIL\"");
	
	query.registerStoredProcedureParameter("p_pdt_id",          String.class,         ParameterMode.IN);
	query.registerStoredProcedureParameter("p_com_id",          String.class,         ParameterMode.IN);
	query.registerStoredProcedureParameter("p_name",            String.class,         ParameterMode.IN);
	query.registerStoredProcedureParameter("p_value",           String.class,         ParameterMode.IN);
	query.registerStoredProcedureParameter("p_sort_no",         Integer.class,        ParameterMode.IN);
	query.registerStoredProcedureParameter("p_work_user",       String.class,         ParameterMode.IN);
	
	query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
	query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
	
	query.setParameter("p_pdt_id",                     detailDto.getPdtId());
	query.setParameter("p_com_id",                     detailDto.getComId());
	query.setParameter("p_name",                       detailDto.getName());
	query.setParameter("p_value",                      detailDto.getValue());
	query.setParameter("p_sort_no",                    detailDto.getSortNo());
	query.setParameter("p_work_user",                  detailDto.getWorkUser());
	
	query.execute();
	
	ResultProcDto resultProcDto = new ResultProcDto();
	if(query.getOutputParameterValue(Constant.SP_RETCODE) != null) {
		resultProcDto.setRetCode(query.getOutputParameterValue(Constant.SP_RETCODE).toString());
	}
	if(query.getOutputParameterValue(Constant.SP_RETSTR) != null) {
		resultProcDto.setRetStr(query.getOutputParameterValue(Constant.SP_RETSTR).toString());
	}
	System.out.println("SP_RETCODE :"+ query.getOutputParameterValue(Constant.SP_RETCODE).toString());
	System.out.println("SP_RETSTR  :"+ query.getOutputParameterValue(Constant.SP_RETSTR).toString());
	return resultProcDto;
	
}
public ResultProcDto callDelDetail(String pdtId, int sortNo, String comId){
	
	final StoredProcedureQuery query = entityManager.createStoredProcedureQuery("\"PRC_PDT_DETAIL_DEL\"");
	
	query.registerStoredProcedureParameter("p_pdt_id",          String.class,         ParameterMode.IN);
	query.registerStoredProcedureParameter("p_com_id",          String.class,         ParameterMode.IN);
	query.registerStoredProcedureParameter("p_sort_no",         Integer.class,        ParameterMode.IN);
	
	query.registerStoredProcedureParameter(Constant.SP_RETCODE,       String.class, ParameterMode.OUT);
	query.registerStoredProcedureParameter(Constant.SP_RETSTR,        String.class, ParameterMode.OUT);
	
	query.setParameter("p_pdt_id",                     pdtId);
	query.setParameter("p_com_id",                     comId);
	query.setParameter("p_sort_no",                    sortNo);
	
	query.execute();
	
	ResultProcDto resultProcDto = new ResultProcDto();
	if(query.getOutputParameterValue(Constant.SP_RETCODE) != null) {
		resultProcDto.setRetCode(query.getOutputParameterValue(Constant.SP_RETCODE).toString());
	}
	if(query.getOutputParameterValue(Constant.SP_RETSTR) != null) {
		resultProcDto.setRetStr(query.getOutputParameterValue(Constant.SP_RETSTR).toString());
	}
	System.out.println("SP_RETCODE :"+ query.getOutputParameterValue(Constant.SP_RETCODE).toString());
	System.out.println("SP_RETSTR  :"+ query.getOutputParameterValue(Constant.SP_RETSTR).toString());
	return resultProcDto;
	
}

}
