package com.xqk.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqk.BaseTest;
import com.xqk.pojo.Admin;
import com.xqk.pojo.ErrorMessageWrapper;

public class FormValidatorTest extends BaseTest {
	@Autowired
	FormValidator validator;
	
	@Test
	public void testValidate() {
		String email = "1394077607@qq.com";
		Admin admin = new Admin("xxx", "xxx", "111000", email, new Date());
		Map<String, String> params = new HashMap<>();
		params.put("username", "xxx");
		params.put("email", email);
		params.put("realname", "xxx");
		params.put("password", "111000");
		params.put("password2", "111000");
		ErrorMessageWrapper messageWrapper = validator.validate(params , Admin.class);
		System.out.println(messageWrapper.getErrors());
	}
}
