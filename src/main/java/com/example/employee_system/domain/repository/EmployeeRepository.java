package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.domain.model.Employee;
import com.example.employee_system.domain.model.EmployeeSearchCondition;
import com.example.employee_system.domain.model.EmployeeSummary;

public interface EmployeeRepository {

    /**
     * 営業担当者が担当している待機中従業員数を取得する。
     *
     * @param salesUserId 営業担当者ID
     * @return 待機中従業員数
     */
    long countWaitingEmployees(Long salesUserId);

    /**
     * 指定された検索条件に一致する従業員一覧を取得する。
     *
     * @param condition 検索条件
     * @param offset    取得開始位置
     * @param limit     取得件数
     * @return 従業員一覧
     */
    List<EmployeeSummary> searchEmployees(
            EmployeeSearchCondition condition,
            int offset,
            int limit);

    /**
     * 指定された検索条件に一致する従業員の総件数を取得する。
     *
     * @param condition 検索条件
     * @return 検索条件に一致した総件数
     */
    long countEmployees(EmployeeSearchCondition condition);

    boolean existsByEmployeeNo(String employeeNo);

    Employee findById(Long employeeId);

    void insert(Employee employee);

    int update(Employee employee);

    int logicalDeleteInterviewQuestions(
            Long employeeId,
            Long loginUserId);

    int logicalDeleteInterviews(
            Long employeeId,
            Long loginUserId);

    int logicalDeleteMatchingResults(Long employeeId);

    int logicalDeleteAssignments(
            Long employeeId,
            Long loginUserId);

    int logicalDeleteEmployeeSkills(
            Long employeeId,
            Long loginUserId);

    int logicalDeleteEmployee(
            Long employeeId,
            Long loginUserId);
}
