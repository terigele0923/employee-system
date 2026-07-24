package com.example.employee_system.presentation.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.employee_system.application.service.DashboardService;
import com.example.employee_system.infrastructure.security.LoginUserDetails;
import com.example.employee_system.presentation.response.DashboardResponse;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;
    
    public DashboardController (
    		DashboardService dashboardService){
    	
    	this.dashboardService = dashboardService;
    }
    
    @GetMapping({"/","/dashboard"})
    public String dashboard(
    		@AuthenticationPrincipal
    		LoginUserDetails loginUser,
    		Model model){
    	
    	DashboardResponse dashboard = 
    			dashboardService.getSummary(
    					loginUser.getUser());
    	
    	model.addAttribute(
    			"loginUser",
    			loginUser.getUser());
    	
    	model.addAttribute(
    			"dashboard",
    			dashboard);
    	
    	return "dashboard";
    }
}