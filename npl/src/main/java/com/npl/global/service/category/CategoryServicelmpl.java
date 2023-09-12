package com.npl.global.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.npl.global.dao.category.CategoryDao;
import com.npl.global.dao.category.CategoryDaoExtend;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.category.CategoryDto;
import com.npl.global.model.category.CategoryModel;
import com.npl.global.security.NplUserDetails;

@Service
public class CategoryServicelmpl implements CategoryService {
	
	@Autowired
	private CategoryDao dao;
	
	@Autowired
	private CategoryDaoExtend cateDaoExtends;

	@Override
	public List<CategoryModel> findAll(String comId) {
		// TODO Auto-generated method stub
		return dao.findByComCd(comId);
	}
	
	@Override
	public CategoryModel findInfo(String catId, String comId) {
		// TODO Auto-generated method stub
		return dao.findInfo(catId, comId);
	}

	@Override
	public ResultProcDto saveCate(CategoryDto cateDto) throws Exception {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
		String comId = loggedUser.getUser().getCompany().getComId();
		
		CategoryModel cat = dao.findInfo(cateDto.getParentId(), comId);
		if (cateDto.getParentId() != null && cat != null) {
			String allParentIds = (cat.getAllParentId() == null ? "-" :cat.getAllParentId());
			allParentIds += String.valueOf(cat.getCatId()) + "-";
			cateDto.setAllParentId(allParentIds);
		}else {
			cateDto.setAllParentId("");
		}
		return cateDaoExtends.callCateSave(cateDto);
	}

	@Override
	public ResultProcDto delCat(String catId) throws Exception {
		// TODO Auto-generated method stub
		return cateDaoExtends.callCatDel(catId);
	}

	@Override
	public ResultProcDto saveCatImage(CategoryDto catDto) throws Exception {
		// TODO Auto-generated method stub
		return cateDaoExtends.callCatImage(catDto);
	}

	@Override
	public List<CategoryModel> findByBrand(String comId, String brandId) {
		// TODO Auto-generated method stub
		return dao.findByBrand(comId, brandId);
	}

	@Override
	public List<CategoryModel> listCate(String comId) {
		// TODO Auto-generated method stub
		return dao.listCate(comId);
	}

}
