package com.example.employee_system.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_system.presentation.request.EmployeeUpdateRequest;
import com.example.employee_system.presentation.response.EmployeeDetailResponse;
import com.example.employee_system.presentation.response.EmployeeSummaryResponse;

@Mapper
public interface EmployeeMapper {

    long countWaitingEmployees(
            @Param("salesUserId") Long salesUserId);

    List<EmployeeSummaryResponse> findEmployees(
            @Param("employmentStatus") String employmentStatus,
            @Param("workStatus") String workStatus,
            @Param("salesUserId") Long salesUserId);

    EmployeeDetailResponse findEmployeeById(
            @Param("employeeId") Long employeeId,
            @Param("salesUserId") Long salesUserId);

    int updateEmployee(
            @Param("employeeId") Long employeeId,
            @Param("request") EmployeeUpdateRequest request,
            @Param("salesUserId") Long salesUserId,
            @Param("updatedBy") Long updatedBy);

    int deleteEmployee(
            @Param("employeeId") Long employeeId,
            @Param("updatedBy") Long updatedBy);
}
