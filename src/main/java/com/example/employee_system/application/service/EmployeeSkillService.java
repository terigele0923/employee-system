package com.example.employee_system.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee_system.domain.repository.EmployeeSkillRepository;
import com.example.employee_system.presentation.response.EmployeeSkillSummaryResponse;

@Service
public class EmployeeSkillService {
	
	private final EmployeeSkillRepository employeeSkillRepository;

	public EmployeeSkillService(EmployeeSkillRepository employeeSkillRepository) {
		this.employeeSkillRepository = employeeSkillRepository;
	}
	
	public List<EmployeeSkillSummaryResponse> findSkillsByEmployeeId(
			Long employeeId) {
		
		return employeeSkillRepository.findByEmployeeId(employeeId);
	}
	
	
}
