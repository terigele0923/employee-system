package com.example.employee_system.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_system.presentation.response.ProjectOptionResponse;

@Mapper
public interface ProjectMapper {
	
	long countOpenProjects(
			
			@Param("salesUserId") Long salesUserId);
	
	List<ProjectOptionResponse> findProjectOptions();
}
