package com.example.employee_system.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee_system.domain.repository.EmployeeRepository;
import com.example.employee_system.presentation.response.EmployeeSummaryResponse;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeSummaryResponse> findEmployees(
            String employmentStatus,
            String workStatus,
            Long salesUserId) {

        return employeeRepository.findEmployees(
                employmentStatus,
                workStatus,
                salesUserId);
    }
}
