package com.npl.global.dao.settings;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Company;
import com.npl.global.entity.User;
import com.npl.global.entity.UserMenu;

public interface UserMenuDao extends PagingAndSortingRepository<UserMenu, String> {

	@Query("SELECT u FROM UserMenu u WHERE u.company = ?1 AND u.user =?2")
	public List<UserMenu> getMenu(Company comId, User userId);

}
