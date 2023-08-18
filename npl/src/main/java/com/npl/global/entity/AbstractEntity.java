package com.npl.global.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	/**
	 * Đây là cấu hình chung cho mọi Table. 
	 * Khi tạo mới Table vui lòng Extends Class này. 
	 * khi Extends vui lòng tại @Id cho Table mới.
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_TIME")
	protected Date createdTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_TIME")
	protected Date updatedTime;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "WORK_DATE")
	protected Date workDate;

	@Column(name = "WORK_USER")
	protected String workUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COM_ID")
	protected Company company;

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getWorkUser() {
		return workUser;
	}

	public void setWorkUser(String workUser) {
		this.workUser = workUser;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}