package com.example.employee_system.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.employee_system.domain.model.ContractEndingEmployee;

public interface AssignmentRepository {

	long countActiveEmployees(Long salesUserId);
	
	long countEmployeesEndingBetween(
			
			LocalDate startDate,
			LocalDate endDate,
			Long salesUserId);
	
	List<ContractEndingEmployee>
		findContractEndingEmployees(
			
			LocalDate baseDate,
			Long salesUserId);

}