package com.npl.global.dao.notice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.npl.global.entity.Company;
import com.npl.global.entity.Notice;
import com.npl.global.model.notice.NoticeModel;

public interface NoticeDao extends PagingAndSortingRepository<Notice, Integer> {

	
	@Query(value=" SELECT id  as id                                       "
			+"      , TITLE  as title                                     "
			+"      , CONTENT as content                                  "
			+"      , NUM_VIEW as numViews                                "
			+"      , enabled as  isEnabled                               "
			+"      , poppup as  isPoppup                                 "
			+"      , S_DATE AS startDate                                 "
			+"      , E_DATE AS endDate                                   "
			+"      , CREATED_TIME AS createdTime                         "
			+"      , UPDATE_TIME AS updatedTime                          "
			+"      , WORK_USER AS workUser                               "
			+"   FROM notice                                              "
			+"  WHERE COM_ID = :comId                                     "
			, nativeQuery = true)
	List<NoticeModel> findByComCd(String comId);
	
	@Query(value=" SELECT TITLE  as title                                "
			+"      , CONTENT as content                                 "
			+"      , NUM_VIEW as numViews                               "
			+"      , enabled as  isEnabled                              "
			+"      , poppup as  isPoppup                                "
			+"      , S_DATE AS startDate                                "
			+"      , E_DATE AS endDate                                  "
			+"      , CREATED_TIME AS createdTime                        "
			+"      , UPDATE_TIME AS updatedTime                         "
			+"      , WORK_USER AS workUser                              "
			+"   FROM notice                                             "
			+"  WHERE COM_ID = :comId                                    "
			+"        ID = :id                                        "
			, nativeQuery = true)
	NoticeModel findById(String comId, String id);


}
