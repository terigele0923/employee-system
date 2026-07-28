package com.example.employee_system.application.service;

import org.springframework.stereotype.Service;

import com.example.employee_system.domain.repository.EmployeeRepository;
import com.example.employee_system.domain.repository.ProjectRepository;
import com.example.employee_system.presentation.response.DashboardResponse;

@Service
public class DashboardService {
    
    private final EmployeeRepository  employeeRepository;
    private final ProjectRepository projectRepository;

    public DashboardService(
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository){
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        
    }

    public DashboardResponse getSummary(
            Long salesUserId
    ){
        long waitingEmployeeCount = employeeRepository.countWaitingEmployees(salesUserId);
        
        long activeEmployeeCount  = employeeRepository.countActiveEmployees(salesUserId);
        
        long endingSoonEmployeeCount = employeeRepository.countEndinSoonEmployees(salesUserId);
        
        long projectCount = projectRepository.countOpenProjects(salesUserId);

        DashboardResponse response = new DashboardResponse();

        response.setWaitingEmployeeCount(waitingEmployeeCount);
        response.setActiveEmployeeCount(activeEmployeeCount);
        response.setEndingSoonEmployeeCount(endingSoonEmployeeCount);
        response.setOpenProjectCount(projectCount);

        return response;
    }

}
