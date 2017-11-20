package com.xqk.pojo;

import java.util.List;

public class ValidationResponse {
	private String status;
	private String message;
	private List<ErrorMessage> errorMessageList;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ErrorMessage> getErrorMessageList() {
		return this.errorMessageList;
	}
	public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
