package com.example.employee_system.application.service;

import org.springframework.stereotype.Service;

import com.example.employee_system.domain.repository.EmployeeRepository;
import com.example.employee_system.presentation.response.DashboardResponse;

@Service
public class DashboardService {
    
    private final EmployeeRepository  employeeRepository;

    public DashboardService(
            EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public DashboardResponse getSummary(
            Long salesUserId
    ){
        long waitingEmployeeCount = employeeRepository.countWaitingEmployees(salesUserId);
        
        long activeEmployeeCount  = employeeRepository.countActiveEmployees(salesUserId);

        DashboardResponse response = new DashboardResponse();

        response.setWaitingEmployeeCount(waitingEmployeeCount);
        response.setActiveEmployeeCount(activeEmployeeCount);

        return response;
    }

}
