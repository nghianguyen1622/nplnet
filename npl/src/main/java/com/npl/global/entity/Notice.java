package com.npl.global.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NOTICE")
public class Notice extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "NT_ID" , nullable = false)
	private String ntId;
	
	@Column(name = "TITLE", length = 512)
	private String title;
	
	@Column(name = "CONTENT", columnDefinition = "TEXT")
	private String content;
	
	@Column(name="NUM_VIEW")
	private BigDecimal numViews;
	
	@Column(name = "POPPUP")
	private String poppup;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "S_DATE")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "E_DATE")
	private Date endDate;
	
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

	public String getNtId() {
		return ntId;
	}

	public void setNtId(String ntId) {
		this.ntId = ntId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

}
