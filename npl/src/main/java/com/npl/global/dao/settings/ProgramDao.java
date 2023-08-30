package com.npl.global.dao.settings;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.npl.global.entity.Program;
import com.npl.global.model.settings.ProgramModel;

public interface ProgramDao extends PagingAndSortingRepository<Program, String>{

	
	@Query("SELECT p FROM Program p WHERE p.prgCd = :prgcd")
	public Program getPrgByPrgCd(@Param("prgcd") String prgcd);

	
	@Query(value=" SELECT PRG_CD  as prgCd                                           "
			+"      , DELETE_YN  as deleteYn                                      "
			+"      , EXP_IMP_YN as expImpYn                                              "
			+"      , FORM_NAME as  formName                                       "
			+"      , FORM_NO as  formNo                                       "
			+"      , FORM_URL as  formUrl                                               "
			+"      , HELP_URL AS helpUrl                                             "
			+"      , INSERT_YN AS insertYn                                          "
			+"      , lv AS lv                                          "
			+"      , MENU_CD AS menuCd                                          "
			+"      , MENU_NAME AS menuName                                          "
			+"      , PRG_KIND AS prgKind                                          "
			+"      , PRINT_YN AS printYn                                          "
			+"      , UPDATE_YN AS updateYn                                          "
			+"      , USE_YN AS useYn                                          "
			+"      , VIEW_YN AS viewYn                                          "
			+"   FROM Program                                                       "
			+"  WHERE USE_YN = 'Y'                                        "
			, nativeQuery = true)
	public List<ProgramModel> getListPrograms();
	
	
	@Query(value=" SELECT A.PRG_CD  as prgCd                                   "
			+"          , A.DELETE_YN  as deleteYn                             "
			+"          , A.EXP_IMP_YN as expImpYn                             "
			+"          , A.FORM_NAME as  formName                             "
			+"          , A.FORM_NO as  formNo                                 "
			+"          , A.FORM_URL as  formUrl                               "
			+"          , A.HELP_URL AS helpUrl                                "
			+"          , A.INSERT_YN AS insertYn                              "
			+"          , A.lv AS lv                                           "
			+"          , A.MENU_CD AS menuCd                                  "
			+"          , A.MENU_NAME AS menuName                              "
			+"          , A.PRG_KIND AS prgKind                                "
			+"          , A.PRINT_YN AS printYn                                "
			+"          , A.UPDATE_YN AS updateYn                              "
			+"          , A.USE_YN AS useYn                                    "
			+"          , A.VIEW_YN AS viewYn                                  "
			+"       FROM Program A                                            "
			+"       LEFT JOIN USERMENU B ON A.PRG_CD = B.PRG_CD               "
			+"       WHERE B.USER_ID = :userId                                 "
			, nativeQuery = true)
	public List<ProgramModel> findMenuByUser(String userId);
}
