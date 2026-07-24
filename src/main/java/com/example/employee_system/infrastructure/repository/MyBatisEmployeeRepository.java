package com.example.employee_system.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.model.Employee;
import com.example.employee_system.domain.model.EmployeeSearchCondition;
import com.example.employee_system.domain.model.EmployeeSummary;
import com.example.employee_system.domain.repository.EmployeeRepository;
import com.example.employee_system.infrastructure.mapper.EmployeeMapper;

@Repository
public class MyBatisEmployeeRepository
        implements EmployeeRepository {

    private final EmployeeMapper employeeMapper;

    public MyBatisEmployeeRepository(
            EmployeeMapper employeeMapper) {

        this.employeeMapper = employeeMapper;
    }

    /**
     * 営業担当者が担当している待機中従業員数を取得する。
     */
    @Override
    public long countWaitingEmployees(
            Long salesUserId) {

        return employeeMapper
                .countWaitingEmployees(salesUserId);
    }

    /**
     * 検索条件に一致する従業員一覧を取得する。
     */
    @Override
    public List<EmployeeSummary> searchEmployees(
            EmployeeSearchCondition condition,
            int offset,
            int limit) {

        return employeeMapper.searchEmployees(
                condition,
                offset,
                limit);
    }

    /**
     * 検索条件に一致する従業員の総件数を取得する。
     */
    @Override
    public long countEmployees(
            EmployeeSearchCondition condition) {

        return employeeMapper
                .countEmployees(condition);
    }

    @Override
    public boolean existsByEmployeeNo(
            String employeeNo) {

        return employeeMapper
                .countByEmployeeNo(employeeNo) > 0;
    }

    @Override
    public Employee findById(Long employeeId) {
        return employeeMapper.findById(employeeId);
    }

    @Override
    public void insert(Employee employee) {
        employeeMapper.insert(employee);
    }

    @Override
    public int update(Employee employee) {
        return employeeMapper.update(employee);
    }

    @Override
    public int logicalDeleteInterviewQuestions(
            Long employeeId,
            Long loginUserId) {
        return employeeMapper.logicalDeleteInterviewQuestions(
                employeeId,
                loginUserId);
    }

    @Override
    public int logicalDeleteInterviews(
            Long employeeId,
            Long loginUserId) {
        return employeeMapper.logicalDeleteInterviews(
                employeeId,
                loginUserId);
    }

    @Override
    public int logicalDeleteMatchingResults(Long employeeId) {
        return employeeMapper.logicalDeleteMatchingResults(
                employeeId);
    }

    @Override
    public int logicalDeleteAssignments(
            Long employeeId,
            Long loginUserId) {
        return employeeMapper.logicalDeleteAssignments(
                employeeId,
                loginUserId);
    }

    @Override
    public int logicalDeleteEmployeeSkills(
            Long employeeId,
            Long loginUserId) {
        return employeeMapper.logicalDeleteEmployeeSkills(
                employeeId,
                loginUserId);
    }

    @Override
    public int logicalDeleteEmployee(
            Long employeeId,
            Long loginUserId) {
        return employeeMapper.logicalDeleteEmployee(
                employeeId,
                loginUserId);
    }
}
