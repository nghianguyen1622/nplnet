package com.npl.global.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.notice.NoticeDao;
import com.npl.global.dao.product.ProductDao;
import com.npl.global.dao.product.ProductDaoExtends;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.product.PdtDetailDto;
import com.npl.global.dto.product.PdtDto;
import com.npl.global.entity.Company;
import com.npl.global.model.product.ProductModel;

@Service
public class ProductServicelmpl implements ProductService {

	@Autowired
	private ProductDao dao;
	
	@Autowired
	private ProductDaoExtends daoExtends;
	
	@Override
	public List<ProductModel> findAll(String comId) {
		// TODO Auto-generated method stub
		return this.dao.findAll(comId);
	}

	@Override
	public ProductModel findInfo(String pdtId, String comId) {
		// TODO Auto-generated method stub
		return this.dao.findInfo(pdtId, comId);
	}

	@Override
	public ResultProcDto savePdt(PdtDto pdtDto) throws Exception {
		// TODO Auto-generated method stub
		return daoExtends.callPdtSave(pdtDto);
	}

	@Override
	public ResultProcDto delPdt(String pdtId) throws Exception {
		// TODO Auto-generated method stub
		return daoExtends.callPdtDel(pdtId);
	}

	@Override
	public ResultProcDto savePdtImage(PdtDto pdtDto) throws Exception {
		// TODO Auto-generated method stub
		return daoExtends.callPdtImage(pdtDto);
	}

	@Override
	public ResultProcDto savePdtDetail(PdtDetailDto pdtDetailDto) throws Exception {
		// TODO Auto-generated method stub
		return daoExtends.callPdtDetail(pdtDetailDto);
	}

	@Override
	public List<ProductModel> findImgExtra(String pdtId) {
		// TODO Auto-generated method stub
		return dao.findImgExtra(pdtId);
	}

	@Override
	public List<ProductModel> findPdtDetail(String pdtId) {
		// TODO Auto-generated method stub
		return dao.findPdtDetail(pdtId);
	}

}
