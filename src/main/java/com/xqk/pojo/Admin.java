package com.xqk.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.xqk.util.Constant;

public class Admin {

	private Integer id = 0;
	
	@NotNull(message="username.null")
	@Size(min=2, max=15, message="username.size")
	private String username;
	
	@NotNull(message="realname.null")
	@Size(min=2, max=10, message="realname.size")
	private String realname;
	
	@NotNull(message="password.null")
	@Size(min=6, max=12, message="password.size")
	private String password;
	
	@NotNull(message="password2.null")
	@Size(min=6, max=12, message="password2.size")
	private String password2;
	
	@NotNull(message="email.null")
	@Pattern(regexp=Constant.EMAIL_REGEXP, message="valid.email")	
	private String email;
	
	private Date createTime;

	public Admin() {}
	
	
	public Admin(String username, String realname, 
			String password, String email, Date createTime) {
		this.username = username;
		this.realname = realname;
		this.password = password;
		this.email = email;
		this.createTime = createTime;
	}



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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
