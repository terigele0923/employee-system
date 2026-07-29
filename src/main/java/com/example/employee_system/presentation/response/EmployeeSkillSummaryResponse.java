package com.example.employee_system.presentation.response;

public class EmployeeSkillSummaryResponse {
	
	private Long employeeSkillId;
	private String categoryCode;
	private String categoryName;
	private String skillName;
	private Integer experienceMonths;
	private Integer skillLevel;
	private String lastUsedYm;
	private String remarks;
	public Long getEmployeeSkillId() {
		return employeeSkillId;
	}
	public void setEmployeeSkillId(Long employeeSkillId) {
		this.employeeSkillId = employeeSkillId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Integer getExperienceMonths() {
		return experienceMonths;
	}
	public void setExperienceMonths(Integer experienceMonths) {
		this.experienceMonths = experienceMonths;
	}
	public Integer getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(Integer skillLevel) {
		this.skillLevel = skillLevel;
	}
	public String getLastUsedYm() {
		return lastUsedYm;
	}
	public void setLastUsedYm(String lastUsedYm) {
		this.lastUsedYm = lastUsedYm;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
