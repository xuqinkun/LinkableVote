package com.xqk.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xqk.pojo.Admin;
import com.xqk.pojo.ErrorMessage;
import com.xqk.pojo.ValidationResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private Log log = LogFactory.getLog(AdminController.class);
	
	@RequestMapping(value="", method=GET)
	public String home(Model model) {
		log.info("============== Home Page Called ===============");
		model.addAttribute("admin",new Admin());
		return "admin/login_register";
	}
	
	
	@RequestMapping(value="/admin_login", method=POST)
	public String login() {
		log.info("============== Post admin_login ===============");
		return "admin/login_register";
	}
	
	@RequestMapping(value="/admin_register", method=GET)
	public ModelAndView register(Model model) {
		log.info("============== get admin_register ===============");
		model.addAttribute(new Admin());
		return new ModelAndView("admin/login_register");
	}
	
	@RequestMapping(value="/admin_register", method=POST)
	public @ResponseBody ValidationResponse processRegister(
			@ModelAttribute("admin") @Valid Admin admin, BindingResult result) {
		log.info("============== Post admin_register ===============");
		ValidationResponse resp = new ValidationResponse();
		if (!result.hasErrors()) {
			resp.setStatus("SUCCESS");
		} else {
			resp.setStatus("ERROR");
			List<FieldError> fieldErrors = result.getFieldErrors();
			List<ErrorMessage> errorList = new ArrayList<ErrorMessage>();
			for (FieldError fieldError : fieldErrors) {
				String field = fieldError.getField();
				String defaultMessage = fieldError.getDefaultMessage();
				errorList.add(new ErrorMessage(field, field + " " + defaultMessage));
			}
			resp.setErrorMessageList(errorList);
		}
		return resp;
	}
}
