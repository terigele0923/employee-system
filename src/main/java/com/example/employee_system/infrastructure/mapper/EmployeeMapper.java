package com.example.employee_system.infrastructure.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper {
	
	long countWaitingEmployees (
			@Param("salesUserId")
			Long salesUserId
			);

}
