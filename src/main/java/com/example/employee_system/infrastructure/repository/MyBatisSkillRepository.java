package com.example.employee_system.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.repository.SkillRepository;
import com.example.employee_system.infrastructure.mapper.SkillMapper;
import com.example.employee_system.presentation.response.SkillOptionResponse;

@Repository
public class MyBatisSkillRepository
		implements SkillRepository{
	private final SkillMapper skillMapper;
	
	

	public MyBatisSkillRepository(SkillMapper skillMapper) {
		this.skillMapper = skillMapper;
	}

	@Override
	    public List<SkillOptionResponse> findActiveSkills() {
		
		return skillMapper.findActiveSkills();
	    }

	    @Override
	    public boolean existsActiveById(Long skillId) {
		
		return skillMapper.countActiveById(skillId) > 0;
	    }

}
