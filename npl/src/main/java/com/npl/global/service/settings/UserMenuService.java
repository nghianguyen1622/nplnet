package com.npl.global.service.settings;

import java.util.List;

import com.npl.global.dto.settings.UserMenuDto;

public interface UserMenuService {

	public List<UserMenuDto> listMenu(String userId);
}
