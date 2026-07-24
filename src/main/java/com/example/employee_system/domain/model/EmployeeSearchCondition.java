package com.example.employee_system.domain.model;

public class EmployeeSearchCondition {

    /** 社員番号 */
    private String employeeNo;

    /** 氏名 */
    private String employeeName;

    /** 稼働状況コード */
    private String workStatusCode;

    /** 営業担当者ID */
    private Long salesUserId;

    /** 最寄駅 */
    private String nearestStation;

    /** スキルID */
    private Long skillId;

    /** 最低経験月数 */
    private Integer minExperienceMonths;

    /** 指定日数以内に契約終了 */
    private Integer endWithinDays;

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
}