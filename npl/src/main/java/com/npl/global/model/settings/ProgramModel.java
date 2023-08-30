package com.npl.global.model.settings;

import java.util.List;

import com.npl.global.entity.UserMenu;

public interface ProgramModel {
	
	public String getPrgCd();
	public String getDeleteYn();
	public String getExpImpYn();
	public String getFormName();
	public String getFormNo();
	public String getFormUrl();
	public String getHelpUrl();
	public String getInsertYn();
	public String getLv();
	public String getMenuCd();
	public String getMenuName();
	public String getPrgKind();
	public String getPrintYn();
	public String getUpdateYn();
	public String getUseYn();
	public String getViewYn();
	public String getUserId();
	public List<UserMenu> getUserMenus();
}
