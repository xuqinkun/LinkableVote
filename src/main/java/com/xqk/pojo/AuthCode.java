package com.xqk.pojo;

import java.util.Date;

public class AuthCode {
	private Integer id;
	private String email;
	private String authCode;
	private Date date;
	
	public AuthCode() {}
	
	public AuthCode(String email, String authCode, Date date) {
		this.email = email;
		this.authCode = authCode;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
