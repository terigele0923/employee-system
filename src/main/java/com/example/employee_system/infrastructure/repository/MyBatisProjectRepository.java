package com.example.employee_system.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.repository.ProjectRepository;
import com.example.employee_system.infrastructure.mapper.ProjectMapper;

@Repository
public class MyBatisProjectRepository 
implements ProjectRepository{
	
	private final ProjectMapper projectMapper;
	
	public MyBatisProjectRepository(
			ProjectMapper projectMapper) {
		
		this.projectMapper = projectMapper;
	}

	@Override
	public long countOpenProjects(Long salesUserId) {
		// TODO 自動生成されたメソッド・スタブ
		return projectMapper.countOpenProjects(salesUserId);
		
	}

	
}
