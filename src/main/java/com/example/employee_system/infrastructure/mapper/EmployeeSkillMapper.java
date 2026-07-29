package com.example.employee_system.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_system.presentation.request.EmployeeSkillRequest;
import com.example.employee_system.presentation.response.EmployeeSkillSummaryResponse;

@Mapper
public interface EmployeeSkillMapper {
	
	List<EmployeeSkillSummaryResponse> findByEmployeeId(
			@Param("employeeId") Long employeeId);
	
	long countByEmployeeIdAndSkillId(
			@Param("employeeId") Long employeeId,
			@Param("skillId") Long skillId);
	
	int insert(
			@Param("employeeId") Long employeeId,
			@Param("request") EmployeeSkillRequest request,
			@Param("createdBy") Long createdBy);
			

}
