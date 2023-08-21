package com.npl.global.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.notice.NoticeDao;
import com.npl.global.dao.notice.NoticeDaoExtend;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.notice.NoticeDto;
import com.npl.global.model.notice.NoticeModel;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao dao;
	
	@Autowired
	private NoticeDaoExtend noticeDaoExtends;

	@Override
	public List<NoticeModel> findAll(String comId) {
		// TODO Auto-generated method stub
		return this.dao.findByComCd(comId);
	}
	
	@Override
	public ResultProcDto saveAddNotice(NoticeDto NoticeAddSave) throws Exception {
		// TODO Auto-generated method stub
		return this.noticeDaoExtends.callNoticeAdd(NoticeAddSave);
	}

	@Override
	public ResultProcDto delNotice(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.noticeDaoExtends.callNoticeDel(id);
	}

	@Override
	public NoticeModel noticeInfo(String comId, String id) {
		// TODO Auto-generated method stub
		return dao.findById(comId, id);
	}

}
