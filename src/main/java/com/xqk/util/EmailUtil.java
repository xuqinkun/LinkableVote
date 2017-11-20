package com.xqk.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.xqk.pojo.EmailMessage;

public class EmailUtil {
	private static Log log = LogFactory.getLog(EmailUtil.class);
	
	private static final String POP = "pop."; 
	private static final String SMTP = "smtp.";
	private static final String Email_Type = "163";
	private static final String protocol = SMTP; 
	
	private static InputStream in = Thread.currentThread().
			getContextClassLoader().getResourceAsStream("properties/MailServer.properties");
	private static Properties prop = new Properties();
	static {
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean validateEmailFormat(String address) {
		return address.matches(Constant.EMAIL_REGEXP);
	}
	
	private static HtmlEmail getEmailer(String type) {
		HtmlEmail email = new HtmlEmail();
		String from = prop.getProperty(type + ".username");
		String password = prop.getProperty(type + ".password");
		email.setHostName(protocol + prop.getProperty(type + ".server"));
		email.setAuthentication(from, password);
		email.setCharset("UTF-8");
		try {
			email.setFrom(from + "@" + type + ".com");
		} catch (EmailException e) {
			log.info("Invalid email address!");
			e.printStackTrace();
		}
		return email;
	}
	
	public static boolean sendEmail(EmailMessage message) {
		NetState netState = new NetState();
		log.info("======= Current NetState: " + netState.isConnect());
		if (!netState.isConnect()) {
			return false;
		}
		log.info("SendEmail To " + message.getTo());
		if (!validateEmailFormat(message.getTo())) {
			log.info("Email address is not valid!");
			return false;
		}
		HtmlEmail email = getEmailer(Email_Type);
		try {
			email.setSSLOnConnect(true);
			email.addTo(message.getTo());
			email.setSubject(message.getSubject());
			email.setHtmlMsg(message.getContent());
			email.setDebug(true);
			String result = email.send();
			log.info("Send email Successed: " + result);
			return true;
		} catch (EmailException e) {
			log.info("Send email Failed!");
			e.printStackTrace();
		}
		return false;
	}
}
