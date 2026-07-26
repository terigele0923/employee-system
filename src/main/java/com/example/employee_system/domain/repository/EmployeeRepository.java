package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.presentation.request.EmployeeUpdateRequest;
import com.example.employee_system.presentation.response.EmployeeDetailResponse;
import com.example.employee_system.presentation.response.EmployeeSummaryResponse;

public interface EmployeeRepository {

    long countWaitingEmployees(Long salesUserId);

    List<EmployeeSummaryResponse> findEmployees(
            String employmentStatus,
            String workStatus,
            Long salesUserId);

    EmployeeDetailResponse findEmployeeById(
            Long employeeId,
            Long salesUserId);

    int updateEmployee(
            Long employeeId,
            EmployeeUpdateRequest request,
            Long salesUserId,
            Long updatedBy);

    int deleteEmployee(
            Long employeeId,
            Long updatedBy);
}
