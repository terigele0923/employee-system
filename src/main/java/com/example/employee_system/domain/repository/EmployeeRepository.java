package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.presentation.request.EmployeeCreateRequest;
import com.example.employee_system.presentation.request.EmployeeUpdateRequest;
import com.example.employee_system.presentation.response.EmployeeDetailResponse;
import com.example.employee_system.presentation.response.EmployeeSummaryResponse;
import com.example.employee_system.presentation.response.SalesUserOptionResponse;

public interface EmployeeRepository {

    long countWaitingEmployees(Long salesUserId);
    long countActiveEmployees(Long salesUserId);
    long countEndinSoonEmployees(Long salesUserId);

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

    List<SalesUserOptionResponse> findSalesUsers();

    boolean existsByEmployeeNo(
            String employeeNo);

    int insertEmployee(
            EmployeeCreateRequest request,
            Long createdBy);
}
