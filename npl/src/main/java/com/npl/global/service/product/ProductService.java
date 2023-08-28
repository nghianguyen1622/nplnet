package com.npl.global.service.product;

import java.util.List;

import com.npl.global.entity.Company;
import com.npl.global.model.product.ProductModel;

public interface ProductService {

	public List<ProductModel> findAll(Company comId);
}
