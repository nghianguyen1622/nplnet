package com.npl.global.model.product;

import java.math.BigDecimal;

public interface ProductModel {
	public String getPdtId();
	
	public String getName();
	public String getAlias();
	public String getShortDes();
	public String getFullDes();
	public String getIsEnabled();
	public BigDecimal getCost();
	public String getPrice();
	public BigDecimal getDisPer();
	public BigDecimal getQty();
	public String getPdtKind();
	public String getMainImage();
	public String getCategoryID();
	public String getBrandID();
	public String getFilePath();
	public String getFileName();
	public String getFileNameOrg();
	
	public String getCreatedTime();
	public String getUpdatedTime();
	public String getWorkUser();
}
