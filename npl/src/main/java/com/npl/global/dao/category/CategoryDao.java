package com.npl.global.dao.category;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Category;
import com.npl.global.model.category.CategoryModel;

public interface CategoryDao extends PagingAndSortingRepository<Category, String> {
	@Query(value=" SELECT cat_id  as catId                                "
			+"      , name  as name                                       "
			+"      , alias as alias                                      "
			+"      , file_name as fileName                               "
			+"      , file_name_org as fileNameOrg                        "
			+"      , file_path as filePath                               "
			+"      , enabled as  isEnabled                               "
			+"      , CREATED_TIME AS createdTime                         "
			+"      , UPDATE_TIME AS updatedTime                          "
			+"      , WORK_USER AS workUser                               "
			+"   FROM categories                                          "
			+"  WHERE COM_ID = :comId                                     "
			, nativeQuery = true)
	List<CategoryModel> findByComCd(String comId);
	
	@Query(value=" SELECT cat_id  as catId                                "
			+"      , name  as name                                       "
			+"      , alias as alias                                      "
			+"      , file_name as fileName                               "
			+"      , file_name_org as fileNameOrg                        "
			+"      , file_path as filePath                               "
			+"      , enabled as  isEnabled                               "
			+"      , all_parent_ids  as allParentId                      "
			+"      , parent_id as parentId                               "
			+"      , CREATED_TIME AS createdTime                         "
			+"      , UPDATE_TIME AS updatedTime                          "
			+"      , WORK_USER AS workUser                               "
			+"   FROM categories                                          "
			+"  WHERE cat_id = :catId                                     "
			+"    AND com_id = :comId                                     "
			, nativeQuery = true)
	CategoryModel findInfo(String catId, String comId);
	
	
}
