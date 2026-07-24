package com.example.employee_system.infrastructure.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_system.domain.model.ContractEndingEmployee;

@Mapper
public interface AssignmentMapper {
	
	long countActiveEmployees(
			@Param("salesUserId")
			Long salesUserId);
	
	long countEmployeesEndingBetween(
			@Param("startDate")
			LocalDate startDate,
			
			@Param("endDate")
			LocalDate endDate,
			
			@Param("salesUserId")
			Long salesUserId);
	
	List<ContractEndingEmployee>
			findContractEndingEmployees(
					@Param("baseDate")
					LocalDate baseDate,
					
					@Param("salesUserId")
					Long salesUserId);

}
