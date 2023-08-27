package com.npl.global.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MEM_IMAGE database table.
 * 
 */
@Embeddable
public class UserImagePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "USER_ID",insertable=false, updatable=false)
	private String userid;
	
	@Column(name="KIND_CD", insertable=false, updatable=false)
	private String kindCd;

	public UserImagePK() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getKindCd() {
		return kindCd;
	}

	public void setKindCd(String kindCd) {
		this.kindCd = kindCd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserImagePK)) {
			return false;
		}
		UserImagePK castOther = (UserImagePK)other;
		return 
			this.userid.equals(castOther.userid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userid.hashCode();
		
		return hash;
	}
}
