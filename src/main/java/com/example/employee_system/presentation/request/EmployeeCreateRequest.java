package com.example.employee_system.presentation.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class EmployeeCreateRequest {

    @NotBlank(message = "社員番号を入力してください。")
    @Size(min = 10, max = 10,
            message = "社員番号は10文字で入力してください。")
    @Pattern(regexp = "^E[A-Za-z0-9]{9}$",
            message = "社員番号は大文字Eから始まる半角英数字10文字で入力してください。")
    private String employeeNo;

    @NotBlank(message = "氏名を入力してください。")
    @Size(max = 100, message = "氏名は100文字以内で入力してください。")
    private String employeeName;

    @NotNull(message = "生年月日を入力してください。")
    @PastOrPresent(message = "生年月日は現在日以前の日付を入力してください。")
    private LocalDate birthDate;

    @NotBlank(message = "電話番号を入力してください。")
    @Size(max = 20, message = "電話番号は20文字以内で入力してください。")
    @Pattern(regexp = "^[0-9+\\-]+$",
            message = "電話番号は数字、ハイフン、+で入力してください。")
    private String phoneNo;

    @Size(max = 8, message = "郵便番号は8文字以内で入力してください。")
    @Pattern(regexp = "^$|^[0-9]{3}-?[0-9]{4}$",
            message = "郵便番号は123-4567または1234567の形式で入力してください。")
    private String postalCode;

    @NotBlank(message = "住所を入力してください。")
    @Size(max = 200, message = "住所は200文字以内で入力してください。")
    private String address;

    @NotBlank(message = "最寄駅を入力してください。")
    @Size(max = 100, message = "最寄駅は100文字以内で入力してください。")
    private String nearestStation;

    @NotNull(message = "入社日を入力してください。")
    private LocalDate joinDate;

    @NotBlank(message = "在籍状態を選択してください。")
    @Pattern(regexp = "^(01|02|09)$",
            message = "在籍状態が正しくありません。")
    private String employmentStatus = "01";

    @NotBlank(message = "稼働状態を選択してください。")
    @Pattern(regexp = "^(01|02|03|04|09)$",
            message = "稼働状態が正しくありません。")
    private String workStatus = "01";

    @NotNull(message = "担当営業を選択してください。")
    @Positive(message = "担当営業が正しくありません。")
    private Long salesUserId;

    @Size(max = 500, message = "備考は500文字以内で入力してください。")
    private String remarks;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public Long getSalesUserId() {
        return salesUserId;
    }

    public void setSalesUserId(Long salesUserId) {
        this.salesUserId = salesUserId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
