package com.npl.global.dao.brand;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Brand;
import com.npl.global.model.brand.BrandModel;
import com.npl.global.model.category.CategoryModel;

public interface BrandDao extends PagingAndSortingRepository<Brand, String> {
	
	@Query(value=" SELECT brand_id  as brandId                            "
			+"      , name  as name                                       "
			+"      , file_name as fileName                               "
			+"      , file_name_org as fileNameOrg                        "
			+"      , file_path as filePath                               "
			+"      , enabled as  isEnabled                               "
			+"      , (select STRING_AGG(c.name, ', ') from categories c ,brands_categories bc where b.brand_id = bc.brand_id and c.cat_id = bc.cat_id ) as listCate "
			+"      , CREATED_TIME AS createdTime                         "
			+"      , UPDATE_TIME AS updatedTime                          "
			+"      , WORK_USER AS workUser                               "
			+"     FROM brands b                                          "
			+"     WHERE com_id = :comId                                  "
			, nativeQuery = true)
	List<BrandModel> findByComCd(String comId);
	
	@Query(value=" SELECT brand_id  as brandId                            "
			+"          , name  as name                                   "
			+"          , file_name as fileName                           "
			+"          , file_name_org as fileNameOrg                    "
			+"          , file_path as filePath                           "
			+"          , enabled as  isEnabled                           "
			+"          , CREATED_TIME AS createdTime                     "
			+"          , UPDATE_TIME AS updatedTime                      "
			+"          , WORK_USER AS workUser                           "
			+"       FROM brands                                          "
			+"      WHERE brand_id = :brandId                             "
			+"        AND com_id = :comId                                 "
			, nativeQuery = true)
	BrandModel findInfo(String brandId, String comId);
}
