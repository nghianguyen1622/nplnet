package com.npl.global.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.category.CategoryDao;
import com.npl.global.dao.category.CategoryDaoExtend;
import com.npl.global.model.CategoryModel;

@Service
public class CategoryServicelmpl implements CategoryService {
	
	@Autowired
	private CategoryDao dao;
	
	@Autowired
	private CategoryDaoExtend categoryDaoExtends;

	@Override
	public List<CategoryModel> findAll(String comId) {
		// TODO Auto-generated method stub
		return dao.findByComCd(comId);
	}

}
