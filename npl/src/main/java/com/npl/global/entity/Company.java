package com.npl.global.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Company")
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COM_ID")
	private String comId;

	@Column(name = "COM_CD")
	private String comCd;

	@Column(name = "COM_NAME")
	private String comName;
	
	@Column(name="BANK_CD")
	private String bankCd;
	
	@Column(name="BANK_NAME")
	private String bankName;

	@Column(name = "COM_OWNER")
	private String comOwner;

	@Column(name = "COM_TAX_NO")
	private String comTaxNo;

	@Column(name = "EMAIL")
	private String eMail;

	@Column(name = "LOGO")
	private String logo;

	@Column(name = "TEL")
	private String tel;

	@Column(name = "ADDR_MAP")
	private String addrMap;

	@Column(name = "ADDR_1")
	private String addr1;

	@Column(name = "ADDR_2")
	private String addr2;

	@Temporal(TemporalType.DATE)
	@Column(name = "WORK_DATE")
	private Date workDate;

	@Column(name = "WORK_USER")
	private String workUser;

	public Company() {
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getComCd() {
		return comCd;
	}

	public void setComCd(String comCd) {
		this.comCd = comCd;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComOwner() {
		return comOwner;
	}

	public void setComOwner(String comOwner) {
		this.comOwner = comOwner;
	}

	public String getComTaxNo() {
		return comTaxNo;
	}

	public void setComTaxNo(String comTaxNo) {
		this.comTaxNo = comTaxNo;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddrMap() {
		return addrMap;
	}

	public void setAddrMap(String addrMap) {
		this.addrMap = addrMap;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getWorkUser() {
		return workUser;
	}

	public void setWorkUser(String workUser) {
		this.workUser = workUser;
	}

}
