package com.example.employee_system.infrastructure.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.model.ContractEndingEmployee;
import com.example.employee_system.domain.repository.AssignmentRepository;
import com.example.employee_system.infrastructure.mapper.AssignmentMapper;

@Repository
public class MyBatisAssignmentRepository
        implements AssignmentRepository {

    private final AssignmentMapper assignmentMapper;

    public MyBatisAssignmentRepository(
            AssignmentMapper assignmentMapper) {

        this.assignmentMapper = assignmentMapper;
    }

    @Override
    public long countActiveEmployees(
            Long salesUserId) {

        return assignmentMapper
                .countActiveEmployees(salesUserId);
    }

    @Override
    public long countEmployeesEndingBetween(
            LocalDate startDate,
            LocalDate endDate,
            Long salesUserId) {

        return assignmentMapper
                .countEmployeesEndingBetween(
                        startDate,
                        endDate,
                        salesUserId);
    }

    @Override
    public List<ContractEndingEmployee>
            findContractEndingEmployees(
                    LocalDate baseDate,
                    Long salesUserId) {

        return assignmentMapper
                .findContractEndingEmployees(
                        baseDate,
                        salesUserId);
    }
}