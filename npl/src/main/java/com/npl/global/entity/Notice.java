package com.npl.global.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notice")
public class Notice extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "TITLE", length = 512, nullable = false)
	private String title;
	
	@Column(name = "CONTENT", columnDefinition = "TEXT")
	private String content;
	
	@Column(name="NUM_VIEW")
	private BigDecimal numViews;
	
	@Column(name = "ENABLED")
	private String enabled;
	
	@Column(name = "POPPUP")
	private String poppup;
	
	@Column(name = "S_DATE")
	private String startDate;

	@Column(name = "E_DATE")
	private String endDate;

	public Notice() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getNumViews() {
		return numViews;
	}

	public void setNumViews(BigDecimal numViews) {
		this.numViews = numViews;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getPoppup() {
		return poppup;
	}

	public void setPoppup(String poppup) {
		this.poppup = poppup;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
