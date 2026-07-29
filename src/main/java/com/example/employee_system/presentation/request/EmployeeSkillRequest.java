package com.example.employee_system.presentation.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeSkillRequest {

    @NotNull(
            message = "スキルを選択してください。")
    private Long skillId;

    @NotNull(
            message = "経験月数を入力してください。")
    @Min(
            value = 0,
            message = "経験月数は0以上で入力してください。")
    @Max(
            value = 9999,
            message = "経験月数は9999以下で入力してください。")
    private Integer experienceMonths;

    @NotNull(
            message = "スキルレベルを選択してください。")
    @Min(
            value = 1,
            message = "スキルレベルは1以上で入力してください。")
    @Max(
            value = 5,
            message = "スキルレベルは5以下で入力してください。")
    private Integer skillLevel;

    @Pattern(
            regexp = "^$|\\d{6}$",
            message = "最終使用年月はYYYYMM形式で入力してください。")
    private String lastUsedYm;

    @Size(
            max = 500,
            message = "備考は500文字以内で入力してください。")
    private String remarks;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(
            Long skillId) {

        this.skillId = skillId;
    }

    public Integer getExperienceMonths() {
        return experienceMonths;
    }

    public void setExperienceMonths(
            Integer experienceMonths) {

        this.experienceMonths =
                experienceMonths;
    }

    public Integer getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(
            Integer skillLevel) {

        this.skillLevel =
                skillLevel;
    }

    public String getLastUsedYm() {
        return lastUsedYm;
    }

    public void setLastUsedYm(
            String lastUsedYm) {

        this.lastUsedYm =
                lastUsedYm;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(
            String remarks) {

        this.remarks = remarks;
    }
}