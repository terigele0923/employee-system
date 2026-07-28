package com.example.employee_system.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.repository.EmployeeRepository;
import com.example.employee_system.infrastructure.mapper.EmployeeMapper;
import com.example.employee_system.presentation.request.EmployeeCreateRequest;
import com.example.employee_system.presentation.request.EmployeeUpdateRequest;
import com.example.employee_system.presentation.response.EmployeeDetailResponse;
import com.example.employee_system.presentation.response.EmployeeSummaryResponse;
import com.example.employee_system.presentation.response.SalesUserOptionResponse;

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

        return employeeMapper.countWaitingEmployees(
                salesUserId);
    }
    
    @Override
	public long countActiveEmployees(Long salesUserId) {
		return employeeMapper.countActiveEmployees(salesUserId);
	}

    @Override
    public List<EmployeeSummaryResponse> findEmployees(
            String employmentStatus,
            String workStatus,
            Boolean contractEndingWithin30Days,
            Long salesUserId) {

        return employeeMapper.findEmployees(
                employmentStatus,
                workStatus,
                contractEndingWithin30Days,
                salesUserId);
    }

    @Override
    public EmployeeDetailResponse findEmployeeById(
            Long employeeId,
            Long salesUserId) {

        return employeeMapper.findEmployeeById(
                employeeId,
                salesUserId);
    }

    @Override
    public int updateEmployee(
            Long employeeId,
            EmployeeUpdateRequest request,
            Long salesUserId,
            Long updatedBy) {

        return employeeMapper.updateEmployee(
                employeeId,
                request,
                salesUserId,
                updatedBy);
    }

    @Override
    public int deleteEmployee(
            Long employeeId,
            Long updatedBy) {

        return employeeMapper.deleteEmployee(
                employeeId,
                updatedBy);
    }

    @Override
    public List<SalesUserOptionResponse> findSalesUsers() {
        return employeeMapper.findSalesUsers();
    }

    @Override
    public boolean existsByEmployeeNo(
            String employeeNo) {

        return employeeMapper.countByEmployeeNo(
                employeeNo) > 0;
    }

    @Override
    public int insertEmployee(
            EmployeeCreateRequest request,
            Long createdBy) {

        return employeeMapper.insertEmployee(
                request,
                createdBy);
    }

	@Override
	public long countEndinSoonEmployees(Long salesUserId) {
		
		return employeeMapper.countEndingSoonEmployees(salesUserId);
	}

}

