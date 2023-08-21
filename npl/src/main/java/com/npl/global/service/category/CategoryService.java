package com.npl.global.service.category;

import java.util.List;

import com.npl.global.model.CategoryModel;

public interface CategoryService {

	public List<CategoryModel> findAll(String comId);

}
