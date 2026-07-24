package com.example.employee_system.infrastructure.repository;

import org.springframework.stereotype.Repository;

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

    @Override
    public long countWaitingEmployees(
            Long salesUserId) {

        return employeeMapper
                .countWaitingEmployees(salesUserId);
    }
}