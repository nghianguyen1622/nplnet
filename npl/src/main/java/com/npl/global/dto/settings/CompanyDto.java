package com.npl.global.dto.settings;

import org.springframework.web.multipart.MultipartFile;

public class CompanyDto {
	private String comId;
	private String comCd;
	private String comName;
	private String addr1;
	private String addr2;
	private String addrMap;
	private String bankCd;
	private String bankName;
	private String owner;
	private String taxNo;
	private String email;
	private MultipartFile FileData;
	private String fileName;
	private String fileNameOrg;
	private String filePath;
	private String tel;
	private String workUser;
	
	public CompanyDto() {
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

	public String getAddrMap() {
		return addrMap;
	}

	public void setAddrMap(String addrMap) {
		this.addrMap = addrMap;
	}

	public String getBankCd() {
		return bankCd;
	}

	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MultipartFile getFileData() {
		return FileData;
	}

	public void setFileData(MultipartFile fileData) {
		FileData = fileData;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNameOrg() {
		return fileNameOrg;
	}

	public void setFileNameOrg(String fileNameOrg) {
		this.fileNameOrg = fileNameOrg;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWorkUser() {
		return workUser;
	}

	public void setWorkUser(String workUser) {
		this.workUser = workUser;
	}
	
}
