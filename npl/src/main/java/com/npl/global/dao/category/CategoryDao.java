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
	
		@Query(value="   with recursive cte as (                                     "
				+"        select cat_id as catId                                     "
				+"           , CAST ( name AS text )                                 "
				+"           , parent_id                                             "
				+"           , 1 as level                                            "
				+"           , unnest(array[cat_id]) as path_info                    "
				+"        from categories                                            "
				+"        where parent_id  is null and com_id = :comId               "
				+"        union all                                                  "
				+"        select c.cat_id                                            "
				+"           , rpad('*', p.level * 2,'*') || c.name                  "
				+"           , c.parent_id                                           "
				+"           , p.level + 1                                           "
				+"           , p.path_info||c.cat_id                                 "
				+"        from categories c                                          "
				+"          join cte p on c.parent_id  = p.catId                     "
				+"     )                                                             "
				+"     select *                                                      "
				+"     from cte                                                      "
				+"     order by path_info;                                           "
				, nativeQuery = true)
		List<CategoryModel> listCate(String comId);
	
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
	
	@Query(value=" SELECT c.cat_id as catId         "
			+ "         , c.name as name            "
			+"       FROM brands_categories bc      "
			+"          , categories c              "
			+"      WHERE bc.brand_id = :brandId    "
			+"        AND c.cat_id = bc.cat_id      "
			+"        AND c.com_id = :comId         "
			, nativeQuery = true)
	List<CategoryModel> findByBrand(String comId, String brandId);
}
