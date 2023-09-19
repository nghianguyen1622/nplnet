package com.npl.global.dao.settings;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Company;
import com.npl.global.entity.User;
import com.npl.global.entity.UserMenu;
import com.npl.global.model.settings.UserMenuModel;

public interface UserMenuDao extends PagingAndSortingRepository<UserMenu, String> {

	@Query("SELECT u FROM UserMenu u WHERE u.company = ?1 AND u.user =?2")
	public List<UserMenu> getMenu(Company comId, User userId);

	
	@Query(value=" SELECT A.MENU_ID  as prgCd                                  "
			+"          , A.DELETE_YN  as deleteYn                             "
			+"          , A.EXP_IMP_YN as expImpYn                             "
			+"          , A.INSERT_YN AS insertYn                              "
			+"          , A.PRINT_YN AS printYn                                "
			+"          , A.UPDATE_YN AS updateYn                              "
			+"       FROM USERMENU A                                           "
			+"       LEFT JOIN Program B ON A.PRG_CD = B.PRG_CD                "
			+"       WHERE A.USER_ID = :userId                                 "
			+"         AND B.USE_YN = 'Y'                                      "
			, nativeQuery = true)
	public List<UserMenuModel> findMenuByUser(String userId);

}
