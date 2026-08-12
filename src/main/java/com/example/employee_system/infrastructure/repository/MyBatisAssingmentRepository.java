package com.example.employee_system.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.repository.AssignmentRepository;
import com.example.employee_system.infrastructure.mapper.AssignmentMapper;
import com.example.employee_system.presentation.response.EmployeeAssignmentSummaryResponse;

@Repository
public class MyBatisAssingmentRepository
		implements AssignmentRepository {
	
	private final AssignmentMapper assignmentMapper;
	
	public MyBatisAssingmentRepository(AssignmentMapper assignmentMapper) {
		this.assignmentMapper = assignmentMapper;
	}
	
	@Override
	    public List<EmployeeAssignmentSummaryResponse> findByEmployeeId(Long employeeId) {
		
		return assignmentMapper.findByEmployeeId(employeeId);
	    }
	
	
}
