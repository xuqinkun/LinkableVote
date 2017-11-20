package com.xqk.dao;

import com.xqk.pojo.AuthCode;

public interface AuthCodeDao {
	boolean saveOrUpdate(AuthCode authCode);
	
	AuthCode queryByEmail(String email);
	
	boolean isValid(AuthCode authCode);
}
