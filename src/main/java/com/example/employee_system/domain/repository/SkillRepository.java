package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.presentation.response.SkillOptionResponse;

public interface SkillRepository {

	List<SkillOptionResponse> findActiveSkills();
	
	boolean existsActiveById(Long skillId);
}
