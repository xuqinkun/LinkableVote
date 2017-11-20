package com.xqk.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xqk.pojo.Admin;
import com.xqk.pojo.AuthCode;
import com.xqk.pojo.ErrorMessageWrapper;
import com.xqk.pojo.ValidationResponse;
import com.xqk.service.AdminService;
import com.xqk.service.AuthCodeService;
import com.xqk.util.FormValidator;

@Controller
@RequestMapping("admin")
public class AdminController {
	private static final String SUCCESS = "SUCCESS";
	private static final String FAILED = "FAILED";
	private Log log = LogFactory.getLog(AdminController.class);
	@Autowired
	private FormValidator formValidator;
	@Autowired
	private AuthCodeService emailService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private AuthCodeService authCodeService;

	@RequestMapping(value="", method=GET)
	public String home(Model model) {
		log.info("============== Home Page Called ===============");
		return "redirect:admin/login_register";
	}
	
	
//	@RequestMapping(value="admin_login", method=POST)
//	public String login() {
//		log.info("============== Post admin_login ===============");
//		return "login_register";
//	}
	
	@RequestMapping(value="login_register", method=GET)
	public ModelAndView register(Model model) {
		log.info("============== get admin_register ===============");
		return new ModelAndView("admin/login_register");
	}
	
	@RequestMapping(value="is_existed")
	public @ResponseBody ValidationResponse checkUser(@RequestParam Map<String, String> params) {
		ValidationResponse resp = new ValidationResponse();
		if (adminService.isExisted(params)) {
			resp.setStatus("FAIL");
		} else {
			resp.setStatus(SUCCESS);
		}
		return resp;
	}
	
	@RequestMapping(value="admin_register", method=POST)
	public @ResponseBody ValidationResponse processRegister(@RequestParam Map<String, String> params) {
		log.info("============== Post admin_register ===============");
		
		ValidationResponse resp = new ValidationResponse();
		ErrorMessageWrapper error = formValidator.validate(params, Admin.class);
		if (error.hasErrors()) {
			resp.setStatus("ERROR");
			resp.setErrorMessageList(error.getErrors());
			
		} else {
			if (!adminService.isExisted(params)) {
				AuthCode authCode = new AuthCode(params.get("email"), params.get("authCode"), new Date());
				if (!authCodeService.isValid(authCode)) {
					resp.setStatus(FAILED);
					resp.setMessage("验证码错误或超时");
				} else {
					if (adminService.save(params)) {
						resp.setStatus(SUCCESS);
					} else {
						resp.setStatus(FAILED);
						resp.setMessage("注册失败！");
					}
				}
			} else {
				resp.setStatus(FAILED);
				resp.setMessage("该用户已存在！");
				
			}
		}
		return resp;
	}
	
	@RequestMapping(value="sendAuthCode", method=POST) 
	public @ResponseBody Map<String, Boolean> sendAuthcode (@RequestParam("email") String emailAddress) {
		boolean result = emailService.sendAuthcode(emailAddress);
		Map<String, Boolean> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
}
