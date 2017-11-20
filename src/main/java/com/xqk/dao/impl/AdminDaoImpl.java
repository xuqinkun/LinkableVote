package com.xqk.dao.impl;

import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xqk.dao.AdminDao;
import com.xqk.pojo.Admin;
import com.xqk.pojo.AuthCode;

@Component
public class AdminDaoImpl implements AdminDao {
	
	private static final String prefix = "com.xqk.dao.AdminDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int count(Map<String, String> params) {
		return sqlSessionTemplate.selectOne(prefix + "count", params);
	}
	
	@Override
	public boolean isExisted(Map<String, String> params) {
		return count(params) > 0;
	}
	
	@Override
	public boolean save(Admin admin) {
		return sqlSessionTemplate.insert(prefix + "save", admin) > 0;
	}
	
	@Override
	public boolean update(Map<String, String> params) {
		return sqlSessionTemplate.update(prefix + "update", params) > 0;
	}
	
	@Override
	public boolean update(Admin admin) {
		return sqlSessionTemplate.update(prefix + "update", admin) > 0;
	}

	@Override
	public Admin queryOne(Map<String, String> params) {
		return sqlSessionTemplate.selectOne(prefix + "queryOne", params);
	}

	
	
}
