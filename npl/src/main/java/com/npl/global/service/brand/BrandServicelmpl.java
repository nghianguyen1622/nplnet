package com.npl.global.service.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.npl.global.dao.brand.BrandDao;
import com.npl.global.dao.brand.BrandDaoExtend;
import com.npl.global.dao.category.CategoryDao;
import com.npl.global.dao.category.CategoryDaoExtend;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.brand.BrandDto;
import com.npl.global.dto.category.CategoryDto;
import com.npl.global.model.brand.BrandModel;
import com.npl.global.model.category.CategoryModel;
import com.npl.global.security.NplUserDetails;

@Service
public class BrandServicelmpl implements BrandService {
	
	@Autowired
	private BrandDao dao;
	
	@Autowired
	private BrandDaoExtend brandDaoExtends;

	@Override
	public List<BrandModel> findAll(String comId) {
		// TODO Auto-generated method stub
		return dao.findByComCd(comId);
	}
	
	@Override
	public BrandModel findInfo(String brandId, String comId) {
		// TODO Auto-generated method stub
		return dao.findInfo(brandId, comId);
	}

	@Override
	public ResultProcDto saveBrand(BrandDto brandDto) throws Exception {
		// TODO Auto-generated method stub
		return brandDaoExtends.callBrandSave(brandDto);
	}

	@Override
	public ResultProcDto delBrand(String brandId) throws Exception {
		// TODO Auto-generated method stub
		return brandDaoExtends.callBrandDel(brandId);
	}

}
