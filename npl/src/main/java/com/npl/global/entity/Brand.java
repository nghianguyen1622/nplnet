package com.npl.global.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="NAME")
	private String name;
	
	@Column(name="LOGO")
	private String logo;
	
	private boolean editYN;
	private boolean delYN;
	
	@ManyToMany
	@JoinTable(name = "brands_categories",
				joinColumns = @JoinColumn(name = "BRAND_ID"),
				inverseJoinColumns = @JoinColumn(name = "CAT_ID"))
	private Set<Category> categories = new HashSet<>();
	
	private int lengthcategory;

	public Brand() {
	}

	public Brand(String name) {
		this.name = name;
	}

	public Brand(String name, String logo) {
		this.name = name;
		this.logo = "brand-logo.jpg";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isEditYN() {
		return editYN;
	}

	public void setEditYN(boolean editYN) {
		this.editYN = editYN;
	}

	public boolean isDelYN() {
		return delYN;
	}

	public void setDelYN(boolean delYN) {
		this.delYN = delYN;
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
