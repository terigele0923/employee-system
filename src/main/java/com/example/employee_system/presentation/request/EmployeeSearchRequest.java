package com.example.employee_system.presentation.request;

public class EmployeeSearchRequest {

    private String employeeNo;
    private String employeeName;
    private String workStatusCode;
    private Long salesUserId;
    private String nearestStation;
    private Long skillId;
    private Integer minExperienceMonths;
    private Integer endWithinDays;
    private Integer page = 1;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
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

    public Long getSalesUserId() {
        return salesUserId;
    }

    public void setSalesUserId(Long salesUserId) {
        this.salesUserId = salesUserId;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Integer getMinExperienceMonths() {
        return minExperienceMonths;
    }

    public void setMinExperienceMonths(Integer minExperienceMonths) {
        this.minExperienceMonths = minExperienceMonths;
    }

    public Integer getEndWithinDays() {
        return endWithinDays;
    }

    public void setEndWithinDays(Integer endWithinDays) {
        this.endWithinDays = endWithinDays;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}