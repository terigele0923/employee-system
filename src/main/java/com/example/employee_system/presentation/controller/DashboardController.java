package com.example.employee_system.presentation.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.employee_system.infrastructure.security.LoginUserDetails;

@Controller
public class DashboardController {
	
	@GetMapping({"/","/dashboard"})
	public String dashboard(
			@AuthenticationPrincipal LoginUserDetails loginUser,
			Model model) {
		
		model.addAttribute("loginUser", loginUser.getUser());
		return "dashboard";
	}
		
}


