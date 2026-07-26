package com.example.employee_system.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_system.presentation.response.EmployeeSummaryResponse;

@Mapper
public interface EmployeeMapper {

    long countWaitingEmployees(
            @Param("salesUserId") Long salesUserId);

    List<EmployeeSummaryResponse> findEmployees(
            @Param("employmentStatus") String employmentStatus,
            @Param("workStatus") String workStatus,
            @Param("salesUserId") Long salesUserId);
}
