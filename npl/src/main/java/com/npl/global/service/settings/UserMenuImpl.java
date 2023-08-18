package com.npl.global.service.settings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npl.global.dao.settings.UserMenuDao;
import com.npl.global.entity.Company;
import com.npl.global.entity.User;
import com.npl.global.entity.UserMenu;

@Service
public class UserMenuImpl implements UserMenuService{

	@Autowired
	private UserMenuDao dao;
	
	@Override
	public List<UserMenu> listMenu(Company comId, User userId) {
		// TODO Auto-generated method stub
		return this.dao.getMenu(comId, userId);
	}

}
