package com.example.employee_system.presentation.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.employee_system.application.service.EmployeeService;
import com.example.employee_system.infrastructure.security.LoginUserDetails;
import com.example.employee_system.presentation.response.EmployeeSummaryResponse;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(
            EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String employees(
            @RequestParam(required = false)
            String employmentStatus,
            @RequestParam(required = false)
            String workStatus,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model) {

        Long salesUserId = null;

        if ("SALES".equals(
                loginUser.getUser().getRoleCode())) {

            salesUserId =
                    loginUser.getUser().getUserId();
        }

        List<EmployeeSummaryResponse> employees =
                employeeService.findEmployees(
                        employmentStatus,
                        workStatus,
                        salesUserId);

        model.addAttribute(
                "loginUser",
                loginUser.getUser());

        model.addAttribute(
                "employees",
                employees);

        model.addAttribute(
                "employmentStatus",
                employmentStatus);

        model.addAttribute(
                "workStatus",
                workStatus);

        return "employees";
    }
}
