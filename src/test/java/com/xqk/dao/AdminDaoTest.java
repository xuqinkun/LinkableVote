package com.xqk.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import com.xqk.BaseTest;
import com.xqk.pojo.Admin;

public class AdminDaoTest extends BaseTest {
	
	@Autowired
	private AdminDao adminDao;
	
	@Test
	@Commit
	public void testSave() {
		String email = "1394077607@qq.com";
		Admin admin = new Admin("xxx", "xqk", "111000", email,new Date());
		assertEquals(true, adminDao.save(admin));
		
		Map<String, String> params = new HashMap<>();
//		params.put("username", "xxx");
		params.put("email", email);
		assertEquals(1, adminDao.count(params));
		Admin admin2 = adminDao.queryOne(params);
		assertEquals("xxx", admin2.getUsername());
	}
	
	@Test
	public void testUpdate() {
		String email = "1394077607@qq.com";
		Admin admin = new Admin("xqk", "xqk", "111000", email,new Date());
		Map<String, String> params = new HashMap<>();
		params.put("username", "xxx");
		params.put("email", email);
		params.put("oldEmail", email);
		assertEquals(true, adminDao.update(admin));
		assertEquals(1, adminDao.count(params));
		
	}
}
