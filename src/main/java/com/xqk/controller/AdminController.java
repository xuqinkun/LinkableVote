package com.xqk.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xqk.pojo.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value="", method=GET)
	public String home(Model model) {
		model.addAttribute(new Admin());
		return "admin/admin_login";
	}
	
	@RequestMapping(value="/#register-form")
	public String ref() {
		return "admin/admin_login";
	}
	
	/*@RequestMapping(value="/admin_login", method=POST)
	public String login() {
		return "admin/admin_login";
	}*/
	
	@RequestMapping(value="/admin_register", method=GET)
	public ModelAndView register(Model model) {
		model.addAttribute(new Admin());
		return new ModelAndView("admin/admin_register");
	}
	
	@RequestMapping(value="/admin_register", method=POST)
	public ModelAndView processRegister(
			@Valid Admin admin, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/admin_register");
		}
		return new ModelAndView("admin/home");
	}
}
