package com.example.employee_system.presentation.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeAssignmentRequest {
	
	@NotNull(message = "案件を選択してください")
	private Long projectId;
	
	@NotNull(message = "契約開始日を選択してください")
	private LocalDate contractStartDate;
	
	private LocalDate contractEndDate;
	
	@NotBlank(message = "参加状態を選択してください")
	@Size(max = 2, message = "参加状態を確認してください")
	private String assignmentStatus;
	
	@Size(max = 100, message = "役割は100文字以内で入力してください")
	private String roleName;
	
	@Size(max = 200, message = "担当工程は200文字以内で入力してください")
	private String processes;
	
	@Size(max = 500, message = "備考は500文字以内で入力してください")
	private String remarks;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public LocalDate getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(LocalDate contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public LocalDate getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(LocalDate contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getAssignmentStatus() {
		return assignmentStatus;
	}

	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getProcesses() {
		return processes;
	}

	public void setProcesses(String processes) {
		this.processes = processes;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
