package com.npl.global.dto.brand;

public class BrandDto {
	private String brandId;
	private String name;
	private String enable;
	private String comId;
	private String workUser;
	
	private String kindCd;
	private String filePath;
	private String fileName;
	private String fileNameOrg;
	private String[] listCateIds;
	
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getWorkUser() {
		return workUser;
	}
	public void setWorkUser(String workUser) {
		this.workUser = workUser;
	}
	public String getKindCd() {
		return kindCd;
	}
	public void setKindCd(String kindCd) {
		this.kindCd = kindCd;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public String[] getListCateIds() {
		return listCateIds;
	}
	public void setListCateIds(String[] listCateIds) {
		this.listCateIds = listCateIds;
	}
	
}
