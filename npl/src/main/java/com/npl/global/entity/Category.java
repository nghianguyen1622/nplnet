package com.npl.global.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIES")
public class Category extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CAT_ID" , nullable = false)
	private String catId;

	@Column(name = "NAME")
	private String name;
	
	@Column(name="ALIAS")
	private String alias;
	
	@Column(name = "LOGO")
	private String logo;

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
	
	@Column(name = "ALL_PARENT_IDS")
	private String allParentIds;
	
	@OneToOne
	@JoinColumn(name = "PARENT_ID")
	private Category parent;
	
	@OneToMany(mappedBy = "parent")
	@OrderBy("name asc")
	private Set<Category> children = new HashSet<>();

	public Category() {
	}

	public Category(String name) {
		this.name = name;
		this.alias = name;
		this.logo = "default.png";
	}

	public Category(String name, Category parent) {
		this.name = name;
		this.parent = parent;
	}

	public Category(String name, String alias) {
		this.name = name;
		this.alias = alias;
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

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
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

	public String getAllParentIds() {
		return allParentIds;
	}

	public void setAllParentIds(String allParentIds) {
		this.allParentIds = allParentIds;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}	
	

}
