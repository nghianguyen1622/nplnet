package com.npl.global.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BRANDS")
public class Brand extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BRAND_ID" , nullable = false)
	private String brandId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_NAME_ORG")
	private String fileNameOrg;

	@Column(name="FILE_PATH")
	private String filePath;

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
	
	@ManyToMany
	@JoinTable(name = "brands_categories",
				joinColumns = @JoinColumn(name = "BRAND_ID"),
				inverseJoinColumns = @JoinColumn(name = "CAT_ID"))
	private Set<Category> categories = new HashSet<>();
	
	@Column(name = "LENGHT")
	private int lengthcategory;

	public Brand() {
	}

	public Brand(String name) {
		this.name = name;
	}

	public Brand(String name, String logo) {
		this.name = name;
		this.filePath = "";
		this.fileName = "brand-logo.jpg";
		this.fileNameOrg = "brand-logo.jpg";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
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

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public int getLengthcategory() {
		return lengthcategory;
	}

	public void setLengthcategory(int lengthcategory) {
		this.lengthcategory = lengthcategory;
	}
	
}
