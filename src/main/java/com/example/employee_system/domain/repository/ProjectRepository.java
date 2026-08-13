package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.presentation.response.ProjectOptionResponse;

public interface ProjectRepository {
	
	 long countOpenProjects(Long salesUserId);
	 List<ProjectOptionResponse> findProjectOptions();

}
