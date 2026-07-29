package com.example.employee_system.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.repository.EmployeeSkillRepository;
import com.example.employee_system.infrastructure.mapper.EmployeeSkillMapper;
import com.example.employee_system.presentation.response.EmployeeSkillSummaryResponse;

@Repository
public class MyBatisEmployeeSkillRepository
		implements EmployeeSkillRepository {
	
	private final EmployeeSkillMapper employeeSkillMapper;
	
	

	public MyBatisEmployeeSkillRepository(EmployeeSkillMapper employeeSkillMapper) {
		
		this.employeeSkillMapper = employeeSkillMapper;
	}



	@Override
	    public List<EmployeeSkillSummaryResponse> findByEmployeeId(Long employeeId) {
		
		return  employeeSkillMapper.findByEmployeeId(employeeId);
	    }

}
