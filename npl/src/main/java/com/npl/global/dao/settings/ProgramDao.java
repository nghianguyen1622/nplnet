package com.npl.global.dao.settings;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.npl.global.entity.Program;
import com.npl.global.model.settings.ProgramModel;

public interface ProgramDao extends PagingAndSortingRepository<Program, String>{

	
	@Query("SELECT p FROM Program p WHERE p.prgCd = :prgcd")
	public Program getPrgByPrgCd(@Param("prgcd") Integer prgcd);

	
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
}
