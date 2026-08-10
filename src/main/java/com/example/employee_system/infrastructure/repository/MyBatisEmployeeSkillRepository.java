package com.example.employee_system.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.repository.EmployeeSkillRepository;
import com.example.employee_system.infrastructure.mapper.EmployeeSkillMapper;
import com.example.employee_system.presentation.request.EmployeeSkillRequest;
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



	@Override
	public boolean existsByEmployeeIdAndSkillId(
			Long employeeId, 
			Long skillId) {
		
		return employeeSkillMapper
				.countByEmployeeIdAndSkillId(
						employeeId,
						skillId ) >0;
	}



	@Override
	public int insert(
			Long employeeId,
			EmployeeSkillRequest request, 
			Long createdBy) {
		
		return employeeSkillMapper.insert(employeeId, request, createdBy);
	}
	
	@Override
	public EmployeeSkillRequest findByEmployeeSkillId(
			Long employeeId, 
			Long employeeSkillId) {
		
		return employeeSkillMapper.findByEmployeeSkillId(employeeId, employeeSkillId);
	}



	@Override
	public int updateByEmployeeSkillId(Long employeeId, Long employeeSkillId,
			EmployeeSkillRequest request, Long updatedBy) {
		return employeeSkillMapper.updateByEmployeeSkillId(employeeId, employeeSkillId, request, updatedBy);
	}



	@Override
	public int deleteByEmployeeSkillId(Long employeeId, Long employeeSkillId, Long updatedBy) {
		
		return employeeSkillMapper.deleteByEmployeeSkillId(employeeId, employeeSkillId, updatedBy);
	}

}
