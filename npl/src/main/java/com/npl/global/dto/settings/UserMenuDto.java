package com.npl.global.dto.settings;

import java.io.Serializable;

public class UserMenuDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String menuId;
	private String deleteYn;
	private String expImpYn;
	private String insertYn;
	private String printYn;
	private String updateYn;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getExpImpYn() {
		return expImpYn;
	}
	public void setExpImpYn(String expImpYn) {
		this.expImpYn = expImpYn;
	}
	public String getInsertYn() {
		return insertYn;
	}
	public void setInsertYn(String insertYn) {
		this.insertYn = insertYn;
	}
	public String getPrintYn() {
		return printYn;
	}
	public void setPrintYn(String printYn) {
		this.printYn = printYn;
	}
	public String getUpdateYn() {
		return updateYn;
	}
	public void setUpdateYn(String updateYn) {
		this.updateYn = updateYn;
	}
}
