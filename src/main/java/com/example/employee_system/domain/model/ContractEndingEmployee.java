package com.example.employee_system.domain.model;

import java.time.LocalDate;

public class ContractEndingEmployee {
	
	private Long employeeId;
	private String employeeName;
	
	private String workStatusCode;
	private String workStatusName;
	
	private String projectName;
	private LocalDate contractEndDate;
	private Integer remainingDays;
	
	private String salesUserName;
	private String warningLevelCode;
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getWorkStatusCode() {
		return workStatusCode;
	}
	public void setWorkStatusCode(String workStatusCode) {
		this.workStatusCode = workStatusCode;
	}
	public String getWorkStatusName() {
		return workStatusName;
	}
	public void setWorkStatusName(String workStatusName) {
		this.workStatusName = workStatusName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public LocalDate getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(LocalDate contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public Integer getRemainingDays() {
		return remainingDays;
	}
	public void setRemainingDays(Integer remainingDays) {
		this.remainingDays = remainingDays;
	}
	public String getSalesUserName() {
		return salesUserName;
	}
	public void setSalesUserName(String salesUserName) {
		this.salesUserName = salesUserName;
	}
	public String getWarningLevelCode() {
		return warningLevelCode;
	}
	public void setWarningLevelCode(String warningLevelCode) {
		this.warningLevelCode = warningLevelCode;
	}
	
	

}
