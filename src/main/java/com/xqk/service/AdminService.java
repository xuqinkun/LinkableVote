package com.xqk.service;

import java.util.Map;

import com.xqk.pojo.Admin;

public interface AdminService {
	boolean save(Map<String, String> params);
	boolean update(Admin admin);
	boolean isExisted(Map<String, String> params);
	Admin find(Map<String, String> params);
	
}
