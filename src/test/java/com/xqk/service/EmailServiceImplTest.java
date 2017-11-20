package com.xqk.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqk.BaseTest;

public class EmailServiceImplTest extends BaseTest {
	@Autowired
	AuthCodeService emailService;
	
	@Test
	public void testSendAuthCode() {
		String address = "1394077607@qq.com";
		assertEquals(true, emailService.sendAuthcode(address));
	}
}
