package com.npl.global.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.notice.NoticeDao;
import com.npl.global.dao.product.ProductDao;
import com.npl.global.entity.Company;
import com.npl.global.model.product.ProductModel;

@Service
public class ProductServicelmpl implements ProductService {

	@Autowired
	private ProductDao dao;
	
	@Override
	public List<ProductModel> findAll(Company comId) {
		// TODO Auto-generated method stub
		return this.dao.findAll(comId);
	}

}
