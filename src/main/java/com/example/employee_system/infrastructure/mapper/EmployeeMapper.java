package com.example.employee_system.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_system.domain.model.Employee;
import com.example.employee_system.domain.model.EmployeeSearchCondition;
import com.example.employee_system.domain.model.EmployeeSummary;

@Mapper
public interface EmployeeMapper {

    /**
     * 営業担当者が担当している待機中従業員数を取得する。
     *
     * @param salesUserId 営業担当者ID
     * @return 待機中従業員数
     */
    long countWaitingEmployees(
            @Param("salesUserId") Long salesUserId);

    /**
     * 検索条件に一致する従業員一覧を取得する。
     *
     * @param condition 検索条件
     * @param offset    取得開始位置
     * @param limit     取得件数
     * @return 従業員一覧
     */
    List<EmployeeSummary> searchEmployees(
            @Param("condition") EmployeeSearchCondition condition,
            @Param("offset") int offset,
            @Param("limit") int limit);

    /**
     * 検索条件に一致する従業員の総件数を取得する。
     *
     * @param condition 検索条件
     * @return 検索条件に一致した総件数
     */
    long countEmployees(
            @Param("condition") EmployeeSearchCondition condition);

    long countByEmployeeNo(
            @Param("employeeNo") String employeeNo);

    Employee findById(
            @Param("employeeId") Long employeeId);

    void insert(Employee employee);

    int update(Employee employee);

    int logicalDeleteInterviewQuestions(
            @Param("employeeId") Long employeeId,
            @Param("loginUserId") Long loginUserId);

    int logicalDeleteInterviews(
            @Param("employeeId") Long employeeId,
            @Param("loginUserId") Long loginUserId);

    int logicalDeleteMatchingResults(
            @Param("employeeId") Long employeeId);

    int logicalDeleteAssignments(
            @Param("employeeId") Long employeeId,
            @Param("loginUserId") Long loginUserId);

    int logicalDeleteEmployeeSkills(
            @Param("employeeId") Long employeeId,
            @Param("loginUserId") Long loginUserId);

    int logicalDeleteEmployee(
            @Param("employeeId") Long employeeId,
            @Param("loginUserId") Long loginUserId);
}
