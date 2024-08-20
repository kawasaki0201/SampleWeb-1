package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.ErrorMessageConstant;
import com.example.demo.form.copy.copy.LoginForm;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService service;
	private final PasswordEncoder passwordEncoder;
	
	private final MessageSource messageSource;
	
	@GetMapping("/login")
	public String view(Model model, LoginForm form) {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model model,LoginForm form) {
		var userInfo=service.searchUserById(form.getLoginId());
		var is=userInfo.isPresent() && 
				passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
		if(is) {
			return "redirect:/menu";
		}else {
			var errorMsg=AppUtil.getMessage(messageSource,ErrorMessageConstant.WRONG);
			model.addAttribute("errorMsg",errorMsg);
			return "login";
		}
	}
}
