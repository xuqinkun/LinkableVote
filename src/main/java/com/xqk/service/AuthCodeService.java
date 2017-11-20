package com.xqk.service;

import com.xqk.pojo.AuthCode;

public interface AuthCodeService {

	boolean sendAuthcode(String emailAddress);
	
	boolean isValid(AuthCode authCode);
}
