package com.npl.global.dto;

public class ResultProcDto {

	private String retCode;
	private String retStr;
	private String keyValue;
	
	private int total;
	private int success;
	private int failed;
	
	private String status;	
	
	public ResultProcDto() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public ResultProcDto(String retCode, String retStr) {
		super();
		this.retCode = retCode;
		this.retStr = retStr;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public int getFailed() {
		return failed;
	}
	public void setFailed(int failed) {
		this.failed = failed;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetStr() {
		return retStr;
	}
	public void setRetStr(String retStr) {
		this.retStr = retStr;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

