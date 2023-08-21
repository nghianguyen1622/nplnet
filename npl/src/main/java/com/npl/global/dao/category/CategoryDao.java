package com.npl.global.dao.category;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Category;
import com.npl.global.model.CategoryModel;

public interface CategoryDao extends PagingAndSortingRepository<Category, String> {
	@Query(value=" SELECT cat_id  as id                                   "
			+"      , name  as name                                       "
			+"      , alias as alias                                      "
			+"      , logo as logo                                        "
			+"      , enabled as  isEnabled                               "
			+"      , CREATED_TIME AS createdTime                         "
			+"      , UPDATE_TIME AS updatedTime                          "
			+"      , WORK_USER AS workUser                               "
			+"   FROM categories                                          "
			+"  WHERE COM_ID = :comId                                     "
			, nativeQuery = true)
	List<CategoryModel> findByComCd(String comId);
}
