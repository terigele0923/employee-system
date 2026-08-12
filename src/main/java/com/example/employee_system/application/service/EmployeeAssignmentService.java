package com.example.employee_system.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee_system.domain.repository.AssignmentRepository;
import com.example.employee_system.presentation.response.EmployeeAssignmentSummaryResponse;

@Service
public class EmployeeAssignmentService {

	private final AssignmentRepository assignmentRepository;

	public EmployeeAssignmentService(
			AssignmentRepository assignmentRepository) {
		this.assignmentRepository = assignmentRepository;
	}

	public List<EmployeeAssignmentSummaryResponse> findAssignmentsByEmployeeId(

			Long employeeId) {

		List<EmployeeAssignmentSummaryResponse> assignments = assignmentRepository.findByEmployeeId(employeeId);

		validateAssignmentStatuses(assignments);

		return assignments;
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