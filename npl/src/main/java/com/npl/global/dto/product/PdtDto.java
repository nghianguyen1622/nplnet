package com.npl.global.dto.product;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class PdtDto {
	private String pdtId;
	private String name;
	private String alias;
	private String shortDes;
	private String fullDes;
	private String enabled;
	private String editYn;
	private String delYn;
	private String inStock;
	private BigDecimal cost;
	private BigDecimal price;
	private BigDecimal disPer;
	private BigDecimal length;
	private BigDecimal width;
	private BigDecimal height;
	private BigDecimal weight;
	private String mainImage;
	private String categoryId;
	private String BrandId;
	private String comId;
	private String workUser;
	
	private String filePath;
	private String fileName;
	private String fileNameOrg;
	
	private String fileExtraPath;
	private String fileExtraName;
	private String fileExtraNameOrg;
	
	private MultipartFile fileMainImage;
	private MultipartFile[] fileExtraImage;
	
	public String getPdtId() {
		return pdtId;
	}
	public void setPdtId(String pdtId) {
		this.pdtId = pdtId;
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
	public String getShortDes() {
		return shortDes;
	}
	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}
	public String getFullDes() {
		return fullDes;
	}
	public void setFullDes(String fullDes) {
		this.fullDes = fullDes;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getEditYn() {
		return editYn;
	}
	public void setEditYn(String editYn) {
		this.editYn = editYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getInStock() {
		return inStock;
	}
	public void setInStock(String inStock) {
		this.inStock = inStock;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getDisPer() {
		return disPer;
	}
	public void setDisPer(BigDecimal disPer) {
		this.disPer = disPer;
	}
	public BigDecimal getLength() {
		return length;
	}
	public void setLength(BigDecimal length) {
		this.length = length;
	}
	public BigDecimal getWidth() {
		return width;
	}
	public void setWidth(BigDecimal width) {
		this.width = width;
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getBrandId() {
		return BrandId;
	}
	public void setBrandId(String brandId) {
		BrandId = brandId;
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
	
	public String getFileExtraPath() {
		return fileExtraPath;
	}
	public void setFileExtraPath(String fileExtraPath) {
		this.fileExtraPath = fileExtraPath;
	}
	public String getFileExtraName() {
		return fileExtraName;
	}
	public void setFileExtraName(String fileExtraName) {
		this.fileExtraName = fileExtraName;
	}
	public String getFileExtraNameOrg() {
		return fileExtraNameOrg;
	}
	public void setFileExtraNameOrg(String fileExtraNameOrg) {
		this.fileExtraNameOrg = fileExtraNameOrg;
	}
	public MultipartFile getFileMainImage() {
		return fileMainImage;
	}
	public void setFileMainImage(MultipartFile fileMainImage) {
		this.fileMainImage = fileMainImage;
	}
	public MultipartFile[] getFileExtraImage() {
		return fileExtraImage;
	}
	public void setFileExtraImage(MultipartFile[] fileExtraImage) {
		this.fileExtraImage = fileExtraImage;
	}
	

}
