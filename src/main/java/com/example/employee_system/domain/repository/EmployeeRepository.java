package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.presentation.response.EmployeeSummaryResponse;

public interface EmployeeRepository {

    long countWaitingEmployees(Long salesUserId);

    List<EmployeeSummaryResponse> findEmployees(
            String employmentStatus,
            String workStatus,
            Long salesUserId);
}
