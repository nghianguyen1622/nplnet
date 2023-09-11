package com.npl.global.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_IMAGE")
public class ProductImage extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "FILE_NAME_ORG")
	private String fileNameOrg;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Column(name="IMAGE_KIND")
	private String imageKind;

	@Column(name="SORT_NO")
	private int sortNo;
	
	@ManyToOne
	@JoinColumn(name = "PDT_ID")
	private Product product;

	public ProductImage() {
	}

	public ProductImage(String fileName, String fileNameOrg, String filePath, Product product) {
		this.fileName = fileName;
		this.fileNameOrg = fileNameOrg;
		this.filePath = filePath;
		this.product = product;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public String getImageKind() {
		return imageKind;
	}

	public void setImageKind(String imageKind) {
		this.imageKind = imageKind;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}
