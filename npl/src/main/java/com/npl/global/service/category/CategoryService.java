package com.npl.global.service.category;

import java.util.List;

import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.category.CategoryDto;
import com.npl.global.model.category.CategoryModel;

public interface CategoryService {

	public List<CategoryModel> findAll(String comId);
	public CategoryModel findInfo(String catId, String comId);
	
	public ResultProcDto saveCate(CategoryDto CateSave) throws Exception;
	public ResultProcDto delCat(String catId) throws Exception;
	public ResultProcDto saveCatImage(CategoryDto catDto) throws Exception;

}
