package com.npl.global.dao.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Company;
import com.npl.global.entity.Product;
import com.npl.global.model.ProductModel;

public interface ProductDao extends PagingAndSortingRepository<Product, Integer> {

	@Query(value=" SELECT id  as id                                           "
			+"      , NAME  as name                                      "
			+"      , ALIAS as alias                                              "
			+"      , SHORT_DESCRIPTION as shortDes                                              "
			+"      , FULL_DESCRIPTION as  fullDes                                       "
			+"      , enabled as  isEnabled                                               "
			+"      , cost as cost                                             "
			+"      , uf_currency(price) as price                                          "
			+"      , DISCOUNT_PERCENT as disPer                                          "
			+"      , length as length                                          "
			+"      , width as width                                          "
			+"      , height as height                                          "
			+"      , weight as weight                                          "
			+"      , MAIN_IMAGE as mainImage                                          "
			+"      , CATEGORY_ID as categoryID                                          "
			+"      , BRAND_ID as brandID                                          "
			+"      , CREATED_TIME as createdTime                                          "
			+"      , UPDATE_TIME as updatedTime                                          "
			+"      , WORK_USER as workUser                                          "
			+"     FROM products                                                       "
			+"     WHERE COM_ID = :comId                                        "
			, nativeQuery = true)
	List<ProductModel> findAll(Company comId);
}
