package com.example.employee_system.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee_system.domain.repository.EmployeeRepository;
import com.example.employee_system.presentation.request.EmployeeCreateRequest;
import com.example.employee_system.presentation.request.EmployeeUpdateRequest;
import com.example.employee_system.presentation.response.EmployeeDetailResponse;
import com.example.employee_system.presentation.response.EmployeeSummaryResponse;
import com.example.employee_system.presentation.response.SalesUserOptionResponse;

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

    public EmployeeDetailResponse findEmployeeById(
            Long employeeId,
            Long salesUserId) {

        return employeeRepository.findEmployeeById(
                employeeId,
                salesUserId);
    }

    @Transactional
    public boolean updateEmployee(
            Long employeeId,
            EmployeeUpdateRequest request,
            Long salesUserId,
            Long updatedBy) {

        return employeeRepository.updateEmployee(
                employeeId,
                request,
                salesUserId,
                updatedBy) == 1;
    }

    @Transactional
    public boolean deleteEmployee(
            Long employeeId,
            Long updatedBy) {

        return employeeRepository.deleteEmployee(
                employeeId,
                updatedBy) == 1;
    }

    public List<SalesUserOptionResponse> findSalesUsers() {
        return employeeRepository.findSalesUsers();
    }

    public boolean existsByEmployeeNo(
            String employeeNo) {

        return employeeRepository.existsByEmployeeNo(
                employeeNo);
    }

    @Transactional
    public Long createEmployee(
            EmployeeCreateRequest request,
            Long createdBy) {

        int inserted =
                employeeRepository.insertEmployee(
                        request,
                        createdBy);

        if (inserted != 1) {
            throw new IllegalStateException();
        }

        return request.getEmployeeId();
    }
}
