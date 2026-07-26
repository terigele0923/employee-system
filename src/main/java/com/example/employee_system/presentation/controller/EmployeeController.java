package com.example.employee_system.presentation.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.employee_system.application.service.EmployeeService;
import com.example.employee_system.infrastructure.security.LoginUserDetails;
import com.example.employee_system.presentation.request.EmployeeUpdateRequest;
import com.example.employee_system.presentation.response.EmployeeDetailResponse;
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

    @GetMapping("/employees/{employeeId}")
    public String employeeDetail(
            @PathVariable
            Long employeeId,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model) {

        Long salesUserId = null;

        if ("SALES".equals(
                loginUser.getUser().getRoleCode())) {

            salesUserId =
                    loginUser.getUser().getUserId();
        }

        EmployeeDetailResponse employee =
                employeeService.findEmployeeById(
                        employeeId,
                        salesUserId);

        if (employee == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        model.addAttribute(
                "loginUser",
                loginUser.getUser());

        model.addAttribute(
                "employee",
                employee);

        return "employee-detail";
    }

    @GetMapping("/employees/{employeeId}/edit")
    public String editEmployee(
            @PathVariable
            Long employeeId,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model) {

        Long salesUserId =
                resolveSalesUserId(loginUser);

        EmployeeDetailResponse employee =
                employeeService.findEmployeeById(
                        employeeId,
                        salesUserId);

        if (employee == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        EmployeeUpdateRequest request =
                createUpdateRequest(employee);

        model.addAttribute(
                "loginUser",
                loginUser.getUser());

        model.addAttribute(
                "employee",
                employee);

        model.addAttribute(
                "employeeUpdateRequest",
                request);

        return "employee-edit";
    }

    @PostMapping("/employees/{employeeId}/edit")
    public String updateEmployee(
            @PathVariable
            Long employeeId,
            @ModelAttribute
            EmployeeUpdateRequest employeeUpdateRequest,
            @AuthenticationPrincipal
            LoginUserDetails loginUser) {

        Long salesUserId =
                resolveSalesUserId(loginUser);

        boolean updated =
                employeeService.updateEmployee(
                        employeeId,
                        employeeUpdateRequest,
                        salesUserId,
                        loginUser.getUser().getUserId());

        if (!updated) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        return "redirect:/employees/" + employeeId;
    }

    @PostMapping("/employees/{employeeId}/delete")
    public String deleteEmployee(
            @PathVariable
            Long employeeId,
            @AuthenticationPrincipal
            LoginUserDetails loginUser) {

        if (!"ADMIN".equals(
                loginUser.getUser().getRoleCode())) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN);
        }

        boolean deleted =
                employeeService.deleteEmployee(
                        employeeId,
                        loginUser.getUser().getUserId());

        if (!deleted) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        return "redirect:/employees";
    }

    private Long resolveSalesUserId(
            LoginUserDetails loginUser) {

        if ("SALES".equals(
                loginUser.getUser().getRoleCode())) {

            return loginUser.getUser().getUserId();
        }

        return null;
    }

    private EmployeeUpdateRequest createUpdateRequest(
            EmployeeDetailResponse employee) {

        EmployeeUpdateRequest request =
                new EmployeeUpdateRequest();

        request.setEmployeeName(
                employee.getEmployeeName());
        request.setBirthDate(
                employee.getBirthDate());
        request.setPhoneNo(
                employee.getPhoneNo());
        request.setPostalCode(
                employee.getPostalCode());
        request.setAddress(
                employee.getAddress());
        request.setNearestStation(
                employee.getNearestStation());
        request.setJoinDate(
                employee.getJoinDate());
        request.setEmploymentStatus(
                employee.getEmploymentStatus());
        request.setWorkStatus(
                employee.getWorkStatus());
        request.setRemarks(
                employee.getRemarks());

        return request;
    }
}
