package com.xqk.dao;

import java.util.Map;

import com.xqk.pojo.Admin;

public interface AdminDao {
	
	boolean save(Admin admin);
	
	boolean update(Map<String, String> params);
	
	boolean update(Admin admin);
	
	boolean isExisted(Map<String, String> params);
	
	int count(Map<String, String> params);
	
	Admin queryOne(Map<String, String> params);
}
