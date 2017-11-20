package com.xqk.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import com.xqk.BaseTest;
import com.xqk.pojo.AuthCode;


//@Rollback(true)
@Commit
public class AuthCodeDaoTest extends BaseTest {
	@Autowired
	AuthCodeDao emailDao;
	
	@Test
	public void testSaveAuthCode() {
//		Map<String, String> params = new HashMap<>();
//		params.put("email", email);
//		params.put("authCode", code);
		String email = "1394077607@qq.com";
		String code = "123456";
		AuthCode authCode = new AuthCode(email, code, null);
		emailDao.saveOrUpdate(authCode);
		AuthCode authCode2 = emailDao.queryByEmail(email);
		assertEquals(email, authCode2.getEmail());
		System.out.println(authCode2.getDate());
	}
	
	@Test
	public void testGenerateAuthCode() {
		for (int j = 0; j < 1000; j++) {
			String authCode = "";
			for (int i = 0; i < 6; i++) {
				long r = (long)(Math.random() * 10) % 10;
				authCode += r;
			}
			assertEquals(6, authCode.length());
		}
	}
}
