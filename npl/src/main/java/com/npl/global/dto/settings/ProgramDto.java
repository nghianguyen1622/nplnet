package com.npl.global.dto.settings;

import java.io.Serializable;
import java.math.BigDecimal;

import com.npl.global.common.YesNoStatus;

public class ProgramDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prgCd;
	private String deleteYn;
	private String expImpYn;
	private String formName;
	private String formNo;
	private String formUrl;
	private String helpUrl;
	private String insertYn;
	private BigDecimal lv;
	private String menuCd;
	private String menuName;
	private String prgKind;
	private String printYn;
	private String updateYn;
	private String useYn;
	private String viewYn;
	private String pCd;
	private String userid;
	private String icon;
	private String sortNo;
	
	private YesNoStatus useYnStatus;
	private YesNoStatus printYnStatus;
	private YesNoStatus updateYnStatus;
	private YesNoStatus insertYnStatus;
	private YesNoStatus viewYnStatus;

	public ProgramDto() {
	}

	public ProgramDto(YesNoStatus useYnStatus, YesNoStatus printYnStatus, YesNoStatus updateYnStatus,
			YesNoStatus insertYnStatus, YesNoStatus viewYnStatus) {
		this.useYnStatus = useYnStatus;
		this.printYnStatus = printYnStatus;
		this.updateYnStatus = updateYnStatus;
		this.insertYnStatus = insertYnStatus;
		this.viewYnStatus = viewYnStatus;
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

	public String getpCd() {
		return pCd;
	}

	public void setpCd(String pCd) {
		this.pCd = pCd;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	public YesNoStatus getUseYnStatus() {
		return useYnStatus;
	}

	public void setUseYnStatus(YesNoStatus useYnStatus) {
		this.useYnStatus = useYnStatus;
	}

	public YesNoStatus getPrintYnStatus() {
		return printYnStatus;
	}

	public void setPrintYnStatus(YesNoStatus printYnStatus) {
		this.printYnStatus = printYnStatus;
	}

	public YesNoStatus getUpdateYnStatus() {
		return updateYnStatus;
	}

	public void setUpdateYnStatus(YesNoStatus updateYnStatus) {
		this.updateYnStatus = updateYnStatus;
	}

	public YesNoStatus getInsertYnStatus() {
		return insertYnStatus;
	}

	public void setInsertYnStatus(YesNoStatus insertYnStatus) {
		this.insertYnStatus = insertYnStatus;
	}

	public YesNoStatus getViewYnStatus() {
		return viewYnStatus;
	}

	public void setViewYnStatus(YesNoStatus viewYnStatus) {
		this.viewYnStatus = viewYnStatus;
	}

}
