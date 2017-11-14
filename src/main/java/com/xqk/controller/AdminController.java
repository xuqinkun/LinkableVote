package com.xqk.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xqk.pojo.Admin;
import com.xqk.pojo.ErrorMessage;
import com.xqk.pojo.ErrorMessageWrapper;
import com.xqk.pojo.ValidationResponse;
import com.xqk.util.FormValidator;

@Controller
@RequestMapping("admin")
public class AdminController {
	private Log log = LogFactory.getLog(AdminController.class);
	private FormValidator formValidator;
	
	@Autowired
	public void setFormValidator(FormValidator formValidator) {
		this.formValidator = formValidator;
	}

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
	
	@RequestMapping(value="admin_register", method=POST)
	public @ResponseBody ValidationResponse processRegister(@RequestParam Map<String, String> params) {
		log.info("============== Post admin_register ===============");
		
		ValidationResponse resp = new ValidationResponse();
		ErrorMessageWrapper error = formValidator.validate(params, Admin.class);
		if (error.hasErrors()) {
			resp.setStatus("SUCCESS");
		} else {
			resp.setStatus("ERROR");
			resp.setErrorMessageList(error.getErrors());
		}
		return resp;
	}
}
