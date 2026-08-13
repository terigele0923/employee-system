package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.presentation.request.EmployeeAssignmentRequest;
import com.example.employee_system.presentation.response.EmployeeAssignmentSummaryResponse;

public interface AssignmentRepository {
	
	List<EmployeeAssignmentSummaryResponse> findByEmployeeId(Long employeeId);

	int insert(Long employeeId, EmployeeAssignmentRequest request, Long createdBy);

}
