package com.example.employee_system.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee_system.domain.repository.AssignmentRepository;
import com.example.employee_system.domain.repository.ProjectRepository;
import com.example.employee_system.presentation.request.EmployeeAssignmentRequest;
import com.example.employee_system.presentation.response.EmployeeAssignmentSummaryResponse;
import com.example.employee_system.presentation.response.ProjectOptionResponse;

@Service
public class EmployeeAssignmentService {

	private final AssignmentRepository assignmentRepository;
	private final ProjectRepository projectRepository;

	public EmployeeAssignmentService(
			AssignmentRepository assignmentRepository
			, ProjectRepository projectRepository) {
		this.assignmentRepository = assignmentRepository;
		this.projectRepository = projectRepository;
	}

	public List<EmployeeAssignmentSummaryResponse> findAssignmentsByEmployeeId(

			Long employeeId) {

		List<EmployeeAssignmentSummaryResponse> assignments = assignmentRepository.findByEmployeeId(employeeId);

		validateAssignmentStatuses(assignments);

		return assignments;
	}
	
	public List<ProjectOptionResponse> findProjectOptions() {
		return projectRepository.findProjectOptions();
	}
	
	@Transactional
	public boolean createEmployeeAssignment(
			Long employeeId,
			EmployeeAssignmentRequest request,
			Long createdBy) {
		
		return assignmentRepository.insert(employeeId, request, createdBy) ==1;
	}

	private void validateAssignmentStatuses(
			List<EmployeeAssignmentSummaryResponse> assignments) {

		for (EmployeeAssignmentSummaryResponse assignment : assignments) {

			if (assignment.getAssignmentStatusName() == null) {
				throw new IllegalStateException(
						"参加状態マスタが存在しません。"
								+ "assignmentId="
								+ assignment.getAssignmentId()
								+ ",assignmentStatus="
								+ assignment.getAssignmentStatus());
			}
		}
	}

}