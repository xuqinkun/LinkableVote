package com.xqk.service.impl;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xqk.dao.AuthCodeDao;
import com.xqk.pojo.AuthCode;
import com.xqk.pojo.EmailMessage;
import com.xqk.service.AuthCodeService;
import com.xqk.util.EmailUtil;

@Component
public class AuthCodeServiceImpl implements AuthCodeService {
	private Log log = LogFactory.getLog(AuthCodeServiceImpl.class);
	private static final int AuthCodeSize = 6;
	
	@Autowired
	private AuthCodeDao authCodeDao;
	
	private String generateAuthCode() {
		String authCode = "";
		for (int i = 0; i < AuthCodeSize; i++) {
			long r = (long)(Math.random() * 10) % 10;
			authCode += r;
		}
		return authCode;
	}

	
	
	@Override
	public boolean sendAuthcode(String emailAddress) {
		log.info("========= sendAuthCode To " + emailAddress + " ==============");
		String code = generateAuthCode(); 
		log.info("************** AuthCode:" + code + " **************");
		String header = getHeader();
		String content = "<h3 class='title'>尊敬的用户,您好：</h3>\r\n" + 
				"&nbsp;&nbsp;<span>您的验证码为：<b class='important'>" + code + ".</b></span>\r\n" + 
				"<span>打死也不要告诉别人哦！</span>\r\n";
		String footer = getFooter();
		String html = header + content + footer;
		EmailMessage emailMessage = new EmailMessage(emailAddress, "投票系统注册验证码", html);
		if (EmailUtil.sendEmail(emailMessage)) {
			AuthCode authCode = new AuthCode(emailAddress, code, new Date());
			return authCodeDao.saveOrUpdate(authCode);
		}
		return false;
	}

	private String getFooter() {
		return "</body> </html>";
	}

	private String getHeader() {
		String header = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"utf-8\">\r\n" + 
				"<title>邮箱注册</title>\r\n" + 
				"<style type=\"text/css\">\r\n" + 
				".title {color:black;font-family:'宋体',sans-serif;}" +
				".important {color:green;}" +
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>";
		return header;
	}

	@Override
	public boolean isValid(AuthCode authCode) {
		return authCodeDao.isValid(authCode);
	}
	
	
}
