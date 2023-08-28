package com.npl.global.model.category;

public interface CategoryModel {
	public String getCatId();
	public String getName();
	public String getAlias();
	public String getFileName();
	public String getFileNameOrg();
	public String getFilePath();
	public String getIsEnabled();
	public String getAllParentId();
	public String getParentId();
	
	
	public String getCreatedTime();
	public String getUpdatedTime();
	public String getWorkUser();
}
