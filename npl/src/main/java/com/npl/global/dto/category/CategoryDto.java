package com.npl.global.dto.category;

import org.springframework.web.multipart.MultipartFile;

public class CategoryDto {
	private String catId;
	private String name;
	private String alias;
	private String logo;
	private String parentId;
	private String allParentId;
	private String enable;
	private String comId;
	private String workUser;
	
	private String kindCd;
	private String filePath;
	private String fileName;
	private String fileNameOrg;
	
	private MultipartFile fileData;
	
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getAllParentId() {
		return allParentId;
	}
	public void setAllParentId(String allParentId) {
		this.allParentId = allParentId;
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
	public MultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}
	
}
