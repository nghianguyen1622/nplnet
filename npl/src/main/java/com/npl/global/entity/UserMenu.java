package com.npl.global.entity;

import javax.persistence.*;

@Entity
@Table(name = "USERMENU")
public class UserMenu extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MENU_ID" , nullable = false)
	private String menuId;

	@Column(name = "DELETE_YN")
	private String deleteYn;

	@Column(name = "EXP_IMP_YN")
	private String expImpYn;

	@Column(name = "INSERT_YN")
	private String insertYn;

	@Column(name = "PRINT_YN")
	private String printYn;

	@Column(name = "UPDATE_YN")
	private String updateYn;

	@ManyToOne
	@JoinColumn(name = "PRG_CD")
	private Program program;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public UserMenu() {
	}

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

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
