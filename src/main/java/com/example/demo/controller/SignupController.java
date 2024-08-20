package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.ErrorMessageConstant;
import com.example.demo.form.copy.copy.SignupForm;
import com.example.demo.service.SignupService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {
		
		private final SignupService service;
		
		private final MessageSource messageSource;
		
		@GetMapping("/signup")
		public String view(Model model,SignupForm form) {
			return "signup";
		}
		
		@PostMapping("/signup")
		public void signup(Model model,SignupForm form) {
			var userInfoOpt=service.resistUserInfo(form);
			if(userInfoOpt.isEmpty()) {
				var errorMsg=AppUtil.getMessage(messageSource,ErrorMessageConstant.WRONG2);
				model.addAttribute("errorMsg",errorMsg);
			}else {
				var errorMsg=AppUtil.getMessage(messageSource,ErrorMessageConstant.SUCCEED);
				model.addAttribute("errorMsg",errorMsg);
			}
		}
	}
