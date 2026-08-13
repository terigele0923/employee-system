package com.example.employee_system.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.repository.ProjectRepository;
import com.example.employee_system.infrastructure.mapper.ProjectMapper;
import com.example.employee_system.presentation.response.ProjectOptionResponse;

@Repository
public class MyBatisProjectRepository
		implements ProjectRepository {
	
	private final ProjectMapper projectMapper;
	
	public MyBatisProjectRepository(
			ProjectMapper projectMapper){
		
		this.projectMapper = projectMapper;
	}

	@Override
	    public long countOpenProjects(Long salesUserId) {
		
			return projectMapper.countOpenProjects(salesUserId);
	    }

	@Override
	public List<ProjectOptionResponse> findProjectOptions() {
		
		return projectMapper.findProjectOptions();
	}

}
