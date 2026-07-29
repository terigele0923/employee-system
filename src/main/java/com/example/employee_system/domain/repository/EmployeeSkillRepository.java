package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.presentation.response.EmployeeSkillSummaryResponse;

public interface EmployeeSkillRepository {

	List<EmployeeSkillSummaryResponse> findByEmployeeId(Long employeeId);
}
