package com.xqk.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqk.dao.AdminDao;
import com.xqk.pojo.Admin;
import com.xqk.service.AdminService;
import com.xqk.util.BeanFactory;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public boolean isExisted(Map<String, String> params) {
		return adminDao.isExisted(params);
	}
	
	@Override
	public boolean save(Map<String, String> params) {
		if (!isExisted(params)) {
			Admin admin = BeanFactory.getBean(params, Admin.class);
			if (admin.getCreateTime() == null) {
				admin.setCreateTime(new Date());
			}
			return adminDao.save(admin);
		}
		return false;
	}

	@Override
	public boolean update(Admin admin) {
		return false;
	}

	@Override
	public Admin find(Map<String, String> params) {
		return null;
	}

}
