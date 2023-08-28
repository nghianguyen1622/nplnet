package com.npl.global.model.product;

import java.math.BigDecimal;

public interface ProductModel {
	public String getId();
	
	public String getName();
	public String getAlias();
	public String getShortDes();
	public String getFullDes();
	public boolean getIsEnabled();
	public float getCost();
	public String getPrice();
	public BigDecimal getDisPer();
	public BigDecimal getLength();
	public BigDecimal getWidth();
	public BigDecimal getHeight();
	public BigDecimal getWeight();
	public String getMainImage();
	public String getCategoryID();
	public String getBrandID();
	
	public String getCreatedTime();
	public String getUpdatedTime();
	public String getWorkUser();
}
