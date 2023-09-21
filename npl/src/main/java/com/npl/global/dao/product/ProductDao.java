package com.npl.global.dao.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Company;
import com.npl.global.entity.Product;
import com.npl.global.model.product.ProductModel;

public interface ProductDao extends PagingAndSortingRepository<Product, Integer> {

	@Query(value = " SELECT pdt_id  as pdtId                              "
			+ "      , NAME  as name                                      "
			+ "      , ALIAS as alias                                     "
			+ "      , SHORT_DES as shortDes                              "
			+ "      , FULL_DES as  fullDes                               "
			+ "      , enabled as  isEnabled                              "
			+ "      , cost as cost                                       "
			+ "      , uf_currency(price, 'VN') as priceText              "
			+ "      , DISCOUNT_PERCENT as disPer                         "
			+ "      , qty as qty                                         "
			+ "      , pdt_kind as pdtKind                                "
			+ "      , file_path as filePath                              "
			+ "      , file_name as fileName                              "
			+ "      , file_name_org as fileNameOrg                       "
			+ "      , CATEGORY_ID as categoryId                          "
			+ "      , BRAND_ID as brandId                                "
			+ "      , CREATED_TIME as createdTime                        "
			+ "      , UPDATE_TIME as updatedTime                         "
			+ "      , WORK_USER as workUser                              "
			+ "     FROM products                                         "
			+ "     WHERE COM_ID = :comId                                 "
			+ "     OR :comId = 'All'                                     "
			, nativeQuery = true)
	List<ProductModel> findAll(String comId);
	
	@Query(value = " SELECT pdt_id as pdtId                               "
			+ "      , NAME as name                                       "
			+ "      , ALIAS as alias                                     "
			+ "      , SHORT_DES as shortDes                              "
			+ "      , FULL_DES as fullDes                                "
			+ "      , enabled as isEnabled                               "
			+ "      , cost as cost                                       "
			+ "      , price as price                                     "
			+ "      , DISCOUNT_PERCENT as disPer                         "
			+ "      , qty as qty                                         "
			+ "      , pdt_kind as pdtKind                                "
			+ "      , file_path as filePath                              "
			+ "      , file_name as fileName                              "
			+ "      , file_name_org as fileNameOrg                       "
			+ "      , CATEGORY_ID as categoryID                          "
			+ "      , BRAND_ID as brandID                                "
			+ "      , CREATED_TIME as createdTime                        "
			+ "      , UPDATE_TIME as updatedTime                         "
			+ "      , WORK_USER as workUser                              "
			+ "     FROM products                                         "
			+ "     WHERE  pdt_id = :pdtId                                "
			+ "      AND COM_ID = :comId                                  "
			, nativeQuery = true)
	ProductModel findInfo(String pdtId, String comId);
	
	@Query(value = " SELECT id as regNo                                   "
			+ "      , pdt_id  as pdtId                                   "
			+ "      , file_path as filePath                              "
			+ "      , file_name as fileName                              "
			+ "      , file_name_org as fileNameOrg                       "
			+ "      , sort_no as sortNo                                  "
			+ "      , image_kind as imageKind                            "
			+ "      , CREATED_TIME as createdTime                        "
			+ "      , UPDATE_TIME as updatedTime                         "
			+ "      , WORK_USER as workUser                              "
			+ "        FROM product_image                                 "
			+ "       WHERE pdt_id = :pdtId                               "
			+ "         AND com_id = :comId                               "
			+ "       ORDER BY sort_no                                   "
			, nativeQuery = true)
	List<ProductModel> findImgExtra(String pdtId, String comId);
	
	@Query(value = " SELECT id as regNo                                   "
			+ "      , pdt_id  as pdtId                                   "
			+ "      , file_path as filePath                              "
			+ "      , file_name as fileName                              "
			+ "      , file_name_org as fileNameOrg                       "
			+ "      , sort_no as sortNo                                  "
			+ "      , image_kind as imageKind                            "
			+ "      , CREATED_TIME as createdTime                        "
			+ "      , UPDATE_TIME as updatedTime                         "
			+ "      , WORK_USER as workUser                              "
			+ "        FROM product_image                                 "
			+ "       WHERE pdt_id = :pdtId                               "
			+ "         AND sort_no = :sortNo                             "
			+ "         AND com_id = :comId                               "
			, nativeQuery = true)
	ProductModel findImgExtra1(String pdtId, int sortNo, String comId);
	
	@Query(value = " SELECT product_id  as pdtId                          "
			+ "      , name as detailName                                 "
			+ "      , value as detailValue                               "
			+ "      , sort_no as sortNo                                  "
			+ "      , CREATED_TIME as createdTime                        "
			+ "      , UPDATE_TIME as updatedTime                         "
			+ "      , WORK_USER as workUser                              "
			+ "     FROM product_details                                  "
			+ "     WHERE product_id = :pdtId                             "
			, nativeQuery = true)
	List<ProductModel> findPdtDetail(String pdtId);
	
}
