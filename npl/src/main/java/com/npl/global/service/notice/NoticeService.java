package com.npl.global.service.notice;

import java.util.List;

import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.notice.NoticeDto;
import com.npl.global.entity.Company;
import com.npl.global.model.notice.NoticeModel;

public interface NoticeService {
	
	public List<NoticeModel> findAll(String comId);
	public NoticeModel noticeInfo(String comId, String id);

	public ResultProcDto saveAddNotice(NoticeDto NoticeAddSave) throws Exception;
	public ResultProcDto delNotice(String id) throws Exception;
	
	

}
