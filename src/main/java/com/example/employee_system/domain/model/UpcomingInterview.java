package com.example.employee_system.domain.model;

import java.time.LocalDateTime;

public class UpcomingInterview {

    private Long interviewId;
    private LocalDateTime interviewDatetime;

    private Long employeeId;
    private String employeeName;

    private Long projectId;
    private String projectName;

    private String interviewTypeCode;
    private String interviewTypeName;

    private Long salesUserId;
    private String salesUserName;

    private String interviewStatusCode;
    private String interviewStatusName;

    public Long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public LocalDateTime getInterviewDatetime() {
        return interviewDatetime;
    }

    public void setInterviewDatetime(
            LocalDateTime interviewDatetime) {

        this.interviewDatetime = interviewDatetime;
    }

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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getInterviewTypeCode() {
        return interviewTypeCode;
    }

    public void setInterviewTypeCode(
            String interviewTypeCode) {

        this.interviewTypeCode = interviewTypeCode;
    }

    public String getInterviewTypeName() {
        return interviewTypeName;
    }

    public void setInterviewTypeName(
            String interviewTypeName) {

        this.interviewTypeName = interviewTypeName;
    }

    public Long getSalesUserId() {
        return salesUserId;
    }

    public void setSalesUserId(Long salesUserId) {
        this.salesUserId = salesUserId;
    }

    public String getSalesUserName() {
        return salesUserName;
    }

    public void setSalesUserName(
            String salesUserName) {

        this.salesUserName = salesUserName;
    }

    public String getInterviewStatusCode() {
        return interviewStatusCode;
    }

    public void setInterviewStatusCode(
            String interviewStatusCode) {

        this.interviewStatusCode =
                interviewStatusCode;
    }

    public String getInterviewStatusName() {
        return interviewStatusName;
    }

    public void setInterviewStatusName(
            String interviewStatusName) {

        this.interviewStatusName =
                interviewStatusName;
    }
}