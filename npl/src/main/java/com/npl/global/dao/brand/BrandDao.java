package com.npl.global.dao.brand;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Brand;
import com.npl.global.model.brand.BrandModel;

public interface BrandDao extends PagingAndSortingRepository<Brand, String> {
	
	@Query(value=" SELECT brand_id  as brandId                            "
			+"      , name  as name                                       "
			+"      , logo as logo                                        "
			+"      , enabled as  isEnabled                               "
			+"      , CREATED_TIME AS createdTime                         "
			+"      , UPDATE_TIME AS updatedTime                          "
			+"      , WORK_USER AS workUser                               "
			+"     FROM brands                                            "
			+"     WHERE com_id = :comId                                  "
			, nativeQuery = true)
	List<BrandModel> findByComCd(String comId);
}
