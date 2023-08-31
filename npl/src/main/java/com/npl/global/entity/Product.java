package com.npl.global.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
public class Product extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "PDT_ID" , nullable = false)
	private String pdtId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ALIAS" , nullable = false)
	private String alias;
	
	@Column(name = "SHORT_DES")
	private String shortDes;
	
	@Column(name = "FULL_DES")
	private String fullDes;
	
	@Column(name = "ENABLED")
	private String enabled;
	
	@Column(name = "EDIT_YN")
	private String editYN;
	
	@Column(name = "DEL_YN")
	private String delYN;
	
	@Column(name = "USE_YN")
	private String useYN;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "IN_STOCK")
	private String inStock;
	
	private BigDecimal cost;
	
	private BigDecimal price;
	
	@Column(name = "QTY")
	private BigDecimal qty;
	
	@Column(name = "PDT_KIND")
	private String pdtKind;
	
	@Column(name = "DISCOUNT_PERCENT")
	private BigDecimal discountPercent;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "FILE_NAME_ORG")
	private String fileNameOrg;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Column(name = "CATEGORY_ID", nullable = false)
	private String category;
	
	@Column(name = "BRAND_ID", nullable = false)
	private String brand;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductImage> images = new HashSet<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductDetail> details = new HashSet<>();

	public Product() {
	}

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

	public String getEditYN() {
		return editYN;
	}

	public void setEditYN(String editYN) {
		this.editYN = editYN;
	}

	public String getDelYN() {
		return delYN;
	}

	public void setDelYN(String delYN) {
		this.delYN = delYN;
	}

	public String getUseYN() {
		return useYN;
	}

	public void setUseYN(String useYN) {
		this.useYN = useYN;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public String getPdtKind() {
		return pdtKind;
	}

	public void setPdtKind(String pdtKind) {
		this.pdtKind = pdtKind;
	}
	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Set<ProductImage> getImages() {
		return images;
	}

	public void setImages(Set<ProductImage> images) {
		this.images = images;
	}

	public Set<ProductDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<ProductDetail> details) {
		this.details = details;
	}
	
	
}
