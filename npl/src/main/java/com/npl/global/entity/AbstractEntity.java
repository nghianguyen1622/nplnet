package com.npl.global.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	/**
	 * Đây là cấu hình chung cho mọi Table. 
	 * Khi tạo mới Table vui lòng Extends Class này. 
	 * khi Extends vui lòng không tại @Id cho Table mới.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	protected String id;

	@Column(name = "CREATED_TIME")
	protected String createdTime;

	@Column(name = "UPDATE_TIME")
	protected String updatedTime;

	@Column(name = "WORK_USER")
	protected String workUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COM_ID")
	protected Company company;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
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