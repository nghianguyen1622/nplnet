package com.npl.global.dto.user;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {
	private String userId;
	private String userName;
	private String passwd;
	private String checkPw;
	private MultipartFile fileImage;
	private String img;
	private String email;
	private String birtDay;
	private String addr;
	private String phone;
	private String idenCard;
	private String delYN;
	private String editYN;
	private Boolean enable;
	private String useYN;
	private String logTime;
	private String comId;
	private String workUser;
	private int roleId;
	
	private String kindCD;
	private String filePath;
	private String fileName;
	private String fileNameOrg;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getCheckPw() {
		return checkPw;
	}
	public void setCheckPw(String checkPw) {
		this.checkPw = checkPw;
	}
	
	public MultipartFile getFileImage() {
		return fileImage;
	}
	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirtDay() {
		return birtDay;
	}
	public void setBirtDay(String birtDay) {
		this.birtDay = birtDay;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdenCard() {
		return idenCard;
	}
	public void setIdenCard(String idenCard) {
		this.idenCard = idenCard;
	}
	public String getDelYN() {
		return delYN;
	}
	public void setDelYN(String delYN) {
		this.delYN = delYN;
	}
	public String getEditYN() {
		return editYN;
	}
	public void setEditYN(String editYN) {
		this.editYN = editYN;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public String getUseYN() {
		return useYN;
	}
	public void setUseYN(String useYN) {
		this.useYN = useYN;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getWorkUser() {
		return workUser;
	}
	public void setWorkUser(String workUser) {
		this.workUser = workUser;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getKindCD() {
		return kindCD;
	}
	public void setKindCD(String kindCD) {
		this.kindCD = kindCD;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	
}
