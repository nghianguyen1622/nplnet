package com.npl.global.dao.settings;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Company;

public interface CompanyDao extends PagingAndSortingRepository<Company, String>{

	Company findByComId(String comId);
}
