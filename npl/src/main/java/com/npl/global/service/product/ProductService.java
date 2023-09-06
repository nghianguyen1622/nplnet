package com.npl.global.service.product;

import java.util.List;

import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.product.PdtDetailDto;
import com.npl.global.dto.product.PdtDto;
import com.npl.global.dto.user.UserDto;
import com.npl.global.model.product.ProductModel;

public interface ProductService {

	public List<ProductModel> findAll(String comId);
	public List<ProductModel> findImgExtra(String pdtId);
	public List<ProductModel> findPdtDetail(String pdtId);
	public ProductModel findInfo(String pdtId, String comId);
	
	public ResultProcDto savePdt(PdtDto pdtDto) throws Exception;
	public ResultProcDto delPdt(String pdtId) throws Exception;
	
	public ResultProcDto savePdtImage(PdtDto pdtDto) throws Exception;
	public ResultProcDto savePdtDetail(PdtDetailDto pdtDetailDto) throws Exception;
}
