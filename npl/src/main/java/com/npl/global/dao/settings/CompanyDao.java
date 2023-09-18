package com.npl.global.dao.settings;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Company;
import com.npl.global.model.settings.CompanyModel;
import com.npl.global.model.settings.ProgramModel;

public interface CompanyDao extends PagingAndSortingRepository<Company, String>{

	Company findByComId(String comId);
	
	@Query(value=" SELECT com_id         as comId                                 "
			+"          , com_cd         as comCd                                 "
			+"          , com_name       as comName                               "
			+"          , addr_1         as addr1                                 "
			+"          , addr_2         as addr2                                 "
			+"          , addr_map       as addrMap                               "
			+"          , bank_cd        as bankCd                                "
			+"          , bank_name      as bankName                              "
			+"          , com_owner      as owner                                 "
			+"          , com_tax_no     as taxNo                                 "
			+"          , email          as email                                 "
			+"          , file_name      as fileName                              "
			+"          , file_name_org  as fileNameOrg                           "
			+"          , file_path      as filePath                              "
			+"          , tel            as tel                                   "
			+"       FROM company                                                 "
			, nativeQuery = true)
	public List<CompanyModel> getAll();
	
	@Query(value=" SELECT com_id         as comId                                 "
			+"          , com_cd         as comCd                                 "
			+"          , com_name       as comName                               "
			+"          , addr_1         as addr1                                 "
			+"          , addr_2         as addr2                                 "
			+"          , addr_map       as addrMap                               "
			+"          , bank_cd        as bankCd                                "
			+"          , bank_name      as bankName                              "
			+"          , com_owner      as owner                                 "
			+"          , com_tax_no     as taxNo                                 "
			+"          , email          as email                                 "
			+"          , file_name      as fileName                              "
			+"          , file_name_org  as fileNameOrg                           "
			+"          , file_path      as filePath                              "
			+"          , tel            as tel                                   "
			+"       FROM company                                                 "
			+"      WHERE com_id = :comId                                         "
			, nativeQuery = true)
	public CompanyModel getByComId(String comId);
}
