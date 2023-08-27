package com.npl.global.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_IMAGE")
public class UserImage extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UserImagePK id;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_NAME_ORG")
	private String fileNameOrg;

	@Column(name="FILE_PATH")
	private String filePath;

	public UserImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserImagePK getId() {
		return id;
	}

	public void setId(UserImagePK id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
