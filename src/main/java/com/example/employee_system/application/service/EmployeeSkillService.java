package com.example.employee_system.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee_system.domain.repository.EmployeeSkillRepository;
import com.example.employee_system.domain.repository.SkillRepository;
import com.example.employee_system.presentation.request.EmployeeSkillRequest;
import com.example.employee_system.presentation.response.EmployeeSkillSummaryResponse;
import com.example.employee_system.presentation.response.SkillOptionResponse;

@Service
public class EmployeeSkillService {
	
	private final EmployeeSkillRepository employeeSkillRepository;
	private final SkillRepository skillRepository;

	public EmployeeSkillService(
			EmployeeSkillRepository employeeSkillRepository,
			SkillRepository skillRepository) {
		this.employeeSkillRepository = employeeSkillRepository;
		this.skillRepository = skillRepository;
	}
	
	@Transactional
	public boolean updateEmployeeSkill(
	        Long employeeId,
	        Long employeeSkillId,
	        EmployeeSkillRequest request,
	        Long updatedBy) {

	    return employeeSkillRepository.updateByEmployeeSkillId(
	            employeeId,
	            employeeSkillId,
	            request,
	            updatedBy) == 1;
	}
	
	@Transactional
	public boolean deleteEmployeeSkill(
	        Long employeeId,
	        Long employeeSkillId,
	        Long updatedBy) {

	    return employeeSkillRepository.deleteByEmployeeSkillId(
	            employeeId,
	            employeeSkillId,
	            updatedBy) == 1;
	}
	
	public List<EmployeeSkillSummaryResponse> findSkillsByEmployeeId(
			Long employeeId) {
		
		return employeeSkillRepository.findByEmployeeId(employeeId);
	}
	
	
	public EmployeeSkillRequest findEmployeeSkillById(
			Long employeeId,
			Long employeeSkillId) {
		
		return employeeSkillRepository
				.findByEmployeeSkillId(employeeId, employeeSkillId);
		
	}
	
	public List<SkillOptionResponse> findActiveSkills(){
		return skillRepository.findActiveSkills();
	}
	
	public boolean existsActiveSkill(Long skillId) {
		return skillRepository.existsActiveById(skillId);
	}
	
	public boolean existsEmployeeSkill(
			Long employeeId,
			Long skillId) {
		return employeeSkillRepository
				.existsByEmployeeIdAndSkillId(employeeId, skillId);
	}
	
	@Transactional
	public boolean createEmployeeSkill(
			Long employeeId,
			EmployeeSkillRequest request,
			Long createdBy) {
		
		return employeeSkillRepository.insert(
				employeeId, request, createdBy) == 1;
	}
	

}
