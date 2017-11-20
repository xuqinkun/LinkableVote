package com.xqk.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xqk.dao.AuthCodeDao;
import com.xqk.pojo.AuthCode;

@Component
public class AuthCodeDaoImpl implements AuthCodeDao {
	
	private static final int TIME_OUT = 1000 * 60 * 3;	// Time-out interval for authcode.

	private static final String prefix = "com.xqk.dao.AuthCodeDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private int count(String email) {
		return sqlSessionTemplate.selectOne(prefix + "count", email);
	}
	
	@Override
	public boolean saveOrUpdate(AuthCode authCode) {
		int count = 0;
		if (count(authCode.getEmail()) == 0) {
			count = sqlSessionTemplate.insert(prefix + "save", authCode);
		} else {
			count = sqlSessionTemplate.update(prefix + "update", authCode);
		}
		return count == 1;
	}

	@Override
	public AuthCode queryByEmail(String email) {
		return sqlSessionTemplate.selectOne(prefix + "queryByEmail", email);
	}

	
	@Override
	public boolean isValid(AuthCode authCode) {
		AuthCode authCode2 = queryByEmail(authCode.getEmail());
		long interval = Math.abs(authCode2.getDate().getTime() - authCode.getDate().getTime());
		return interval <= TIME_OUT;
	}
	
}
