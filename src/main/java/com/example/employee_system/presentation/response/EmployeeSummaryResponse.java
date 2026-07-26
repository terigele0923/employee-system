package com.example.employee_system.presentation.response;

public class EmployeeSummaryResponse {

    private Long employeeId;
    private String employeeNo;
    private String employeeName;
    private String nearestStation;
    private String employmentStatus;
    private String employmentStatusName;
    private String workStatus;
    private String workStatusName;
    private String salesUserName;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

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

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getEmploymentStatusName() {
        return employmentStatusName;
    }

    public void setEmploymentStatusName(
            String employmentStatusName) {

        this.employmentStatusName =
                employmentStatusName;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkStatusName() {
        return workStatusName;
    }

    public void setWorkStatusName(
            String workStatusName) {

        this.workStatusName =
                workStatusName;
    }

    public String getSalesUserName() {
        return salesUserName;
    }

    public void setSalesUserName(String salesUserName) {
        this.salesUserName = salesUserName;
    }
}
