package com.example.employee_system.infrastructure.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProjectMapper {
	
	long countOpenProjects(
			
			@Param("salesUserId")
			Long salesUserId);

}
