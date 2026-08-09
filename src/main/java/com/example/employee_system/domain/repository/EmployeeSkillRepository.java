package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.presentation.request.EmployeeSkillRequest;
import com.example.employee_system.presentation.response.EmployeeSkillSummaryResponse;

public interface EmployeeSkillRepository {

	List<EmployeeSkillSummaryResponse> findByEmployeeId(Long employeeId);
	
	boolean existsByEmployeeIdAndSkillId (Long employeeId,Long skillId);
	
	int insert(
			Long employeeId,
			EmployeeSkillRequest request,
			Long createdBy);
	
	EmployeeSkillRequest findByEmployeeSkillId(
			Long employeeId,
			Long employeeSkillId);
	
	int updateByEmployeeSkillId(
			Long employeeId,
			Long employeeSkillId,
			EmployeeSkillRequest request,
			Long updatedBy);
}
