package com.npl.global.service.settings;

import java.util.List;

import com.npl.global.entity.Company;
import com.npl.global.entity.User;
import com.npl.global.entity.UserMenu;

public interface UserMenuService {

	public List<UserMenu> listMenu(Company comId, User userId);
}
