package com.npl.global.service.settings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.settings.UserMenuDao;
import com.npl.global.dto.settings.UserMenuDto;
import com.npl.global.model.settings.UserMenuModel;

@Service
public class UserMenuImpl implements UserMenuService {

	@Autowired
	private UserMenuDao dao;

	@Override
	public List<UserMenuDto> listMenu(String userId) {
		List<UserMenuModel> prgList = this.dao.findMenuByUser(userId);
		
		List<UserMenuDto> menuDtos = new ArrayList<>();
		
		for (UserMenuModel info : prgList) {
			UserMenuDto menu = new UserMenuDto();

			menu.setMenuId(info.getMenuId());
			menu.setDeleteYn(info.getDeleteYn());
			menu.setExpImpYn(info.getExpImpYn());
			menu.setInsertYn(info.getInsertYn());
			menu.setUpdateYn(info.getUpdateYn());
			menu.setPrintYn(info.getPrintYn());

			menuDtos.add(menu);
		}

		return menuDtos;
	}

}
