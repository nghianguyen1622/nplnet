package com.npl.global.service.brand;

import java.util.List;

import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.brand.BrandDto;
import com.npl.global.dto.category.CategoryDto;
import com.npl.global.model.brand.BrandModel;
import com.npl.global.model.category.CategoryModel;

public interface BrandService {

	public List<BrandModel> findAll(String comId);
	public BrandModel findInfo(String brandId, String comId);
	
	public ResultProcDto saveBrand(BrandDto brandDto) throws Exception;
	public ResultProcDto delBrand(String brandId) throws Exception;

}
