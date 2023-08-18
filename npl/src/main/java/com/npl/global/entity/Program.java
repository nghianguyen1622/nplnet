package com.npl.global.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Program")
public class Program implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRG_CD")
	private String prgCd;
	
	@Column(name = "DELETE_YN")
	private String deleteYn;

	@Column(name = "EXP_IMP_YN")
	private String expImpYn;
	
	@Column(name = "FORM_NAME")
	private String formName;
	
	@Column(name = "FORM_NO")
	private String formNo;
	
	@Column(name = "FORM_URL")
	private String formUrl;

	@Column(name = "HELP_URL")
	private String helpUrl;

	@Column(name = "INSERT_YN")
	private String insertYn;

	private BigDecimal lv;
	
	@Column(name = "MENU_CD")
	private String menuCd;

	@Column(name = "MENU_NAME")
	private String menuName;

	@Column(name = "PRG_KIND")
	private String prgKind;

	@Column(name = "PRINT_YN")
	private String printYn;

	@Column(name = "UPDATE_YN")
	private String updateYn;

	@Column(name = "USE_YN")
	private String useYn;

	@Column(name = "VIEW_YN")
	private String viewYn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "P_CD")
	private Program program;

	@OneToMany(mappedBy = "program", fetch = FetchType.LAZY)
	private List<Program> programs;

	@OneToMany(mappedBy = "program", fetch = FetchType.LAZY)
	private List<UserMenu> userMenus;

	public Program() {
	}

	public String getPrgCd() {
		return prgCd;
	}

	public void setPrgCd(String prgCd) {
		this.prgCd = prgCd;
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

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

	public String getHelpUrl() {
		return helpUrl;
	}

	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}

	public String getInsertYn() {
		return insertYn;
	}

	public void setInsertYn(String insertYn) {
		this.insertYn = insertYn;
	}

	public BigDecimal getLv() {
		return lv;
	}

	public void setLv(BigDecimal lv) {
		this.lv = lv;
	}

	public String getMenuCd() {
		return menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPrgKind() {
		return prgKind;
	}

	public void setPrgKind(String prgKind) {
		this.prgKind = prgKind;
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

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getViewYn() {
		return viewYn;
	}

	public void setViewYn(String viewYn) {
		this.viewYn = viewYn;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public List<UserMenu> getUserMenus() {
		return userMenus;
	}

	public void setUserMenus(List<UserMenu> userMenus) {
		this.userMenus = userMenus;
	}
}
