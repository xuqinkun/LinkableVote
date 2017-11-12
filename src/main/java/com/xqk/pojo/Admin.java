package com.xqk.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Admin {
	private static final String REGEXP = "/^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$/";

	private Integer id;
	
	@NotNull(message="username.null")
	@Size(min=2, max=15, message="{username.size}")
	private String username;
	
	@NotNull(message="username.null")
	@Size(min=2, max=10, message="{realname.size}")
	private String realname;
	
	@NotNull(message="password.null")
	@Size(min=6, max=12, message="{password.size}")
	private String password;
	
	@NotNull(message="password2.null")
	@Size(min=6, max=12, message="{password2.size}")
	private String password2;
	
	@NotNull(message="email.null")
	@Pattern(regexp=REGEXP, message="邮箱格式不正确:[用户名]+@+[域名]，如xxx@163.com")	
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
