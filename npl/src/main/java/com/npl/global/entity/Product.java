package com.npl.global.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ALIAS")
	private String alias;
	
	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;
	
	@Lob
	@Column(name = "FULL_DESCRIPTION")
	private String fullDescription;
	
	private boolean enabled;
	
	private boolean editYN;
	private boolean delYN;
	
	@Column(name = "in_stock")
	private boolean inStock;
	
	private float cost;
	
	private float price;
	
	@Column(name = "DISCOUNT_PERCENT")
	private float discountPercent;
	
	private float length;
	private float width;
	private float height;
	private float weight;
	
	@Column(name = "MAIN_IMAGE")
	private String mainImage;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductImage> images = new HashSet<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductDetail> details = new HashSet<>();

	public Product() {
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
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
