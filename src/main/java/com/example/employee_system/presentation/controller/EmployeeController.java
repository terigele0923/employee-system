package com.example.employee_system.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
	
	@GetMapping("/employees")
	public String employees() {
		return "employees";
	}

}
