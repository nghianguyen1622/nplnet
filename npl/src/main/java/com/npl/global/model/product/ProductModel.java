package com.npl.global.model.product;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public interface ProductModel {
	public String getPdtId();
	public String getRegNo();
	
	public String getName();
	public String getAlias();
	public String getShortDes();
	public String getFullDes();
	public String getIsEnabled();
	public BigDecimal getCost();
	public String getPriceText();
	public BigDecimal getPrice();
	public BigDecimal getDisPer();
	public BigDecimal getQty();
	public String getPdtKind();
	public String getCategoryId();
	public String getBrandId();
	public String getFilePath();
	public String getFileName();
	public String getFileNameOrg();
	public String getSortNo();
	public String getImageKind();
	
	public MultipartFile getFileMainImage();
	
	
	public String getDetailName();
	public String getDetailValue();
	
	public String getCreatedTime();
	public String getUpdatedTime();
	public String getWorkUser();
}
