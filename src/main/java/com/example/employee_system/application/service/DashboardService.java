package com.example.employee_system.application.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee_system.domain.model.ContractEndingEmployee;
import com.example.employee_system.domain.model.UpcomingInterview;
import com.example.employee_system.domain.model.User;
import com.example.employee_system.domain.repository.AssignmentRepository;
import com.example.employee_system.domain.repository.EmployeeRepository;
import com.example.employee_system.domain.repository.InterviewRepository;
import com.example.employee_system.domain.repository.ProjectRepository;
import com.example.employee_system.presentation.response.ContractEndingEmployeeResponse;
import com.example.employee_system.presentation.response.DashboardResponse;
import com.example.employee_system.presentation.response.UpcomingInterviewResponse;

@Service
public class DashboardService {

    private final EmployeeRepository employeeRepository;
    private final AssignmentRepository assignmentRepository;
    private final ProjectRepository projectRepository;
    private final InterviewRepository interviewRepository;

    public DashboardService(
            EmployeeRepository employeeRepository,
            AssignmentRepository assignmentRepository,
            ProjectRepository projectRepository,
            InterviewRepository interviewRepository) {

        this.employeeRepository = employeeRepository;
        this.assignmentRepository = assignmentRepository;
        this.projectRepository = projectRepository;
        this.interviewRepository = interviewRepository;
    }

    public DashboardResponse getSummary(User loginUser) {

        LocalDateTime baseDatetime =
                LocalDateTime.now();

        LocalDate baseDate =
                baseDatetime.toLocalDate();

        LocalDate endDate =
                baseDate.plusDays(30);

        Long salesUserId =
                resolveSalesUserId(loginUser);

        DashboardResponse response =
                new DashboardResponse();

        response.setWaitingCount(
                employeeRepository
                        .countWaitingEmployees(
                                salesUserId));

        response.setActiveCount(
                assignmentRepository
                        .countActiveEmployees(
                                salesUserId));

        response.setEndingSoonCount(
                assignmentRepository
                        .countEmployeesEndingBetween(
                                baseDate,
                                endDate,
                                salesUserId));

        response.setOpenProjectCount(
                projectRepository
                        .countOpenProjects(
                                salesUserId));

        List<ContractEndingEmployee>
                contractEndingEmployees =
                        assignmentRepository
                                .findContractEndingEmployees(
                                        baseDate,
                                        salesUserId);

        response.setContractEndingEmployees(
                contractEndingEmployees.stream()
                        .map(this::toContractEndingResponse)
                        .toList());

        List<UpcomingInterview>
                upcomingInterviews =
                        interviewRepository
                                .findUpcomingInterviews(
                                        baseDatetime,
                                        salesUserId);

        response.setUpcomingInterviews(
                upcomingInterviews.stream()
                        .map(this::toUpcomingInterviewResponse)
                        .toList());

        return response;
    }

    private Long resolveSalesUserId(User loginUser) {

        if ("SALES".equals(loginUser.getRoleCode())) {
            return loginUser.getUserId();
        }

        return null;
    }

    private ContractEndingEmployeeResponse
            toContractEndingResponse(
                    ContractEndingEmployee employee) {

        ContractEndingEmployeeResponse response =
                new ContractEndingEmployeeResponse();

        response.setEmployeeId(
                employee.getEmployeeId());

        response.setEmployeeName(
                employee.getEmployeeName());

        response.setWorkStatusCode(
                employee.getWorkStatusCode());

        response.setWorkStatusName(
                employee.getWorkStatusName());

        response.setProjectName(
                employee.getProjectName());

        response.setContractEndDate(
                employee.getContractEndDate());

        response.setRemainingDays(
                employee.getRemainingDays());

        response.setSalesUserName(
                employee.getSalesUserName());

        response.setWarningLevelCode(
                employee.getWarningLevelCode());

        return response;
    }

    private UpcomingInterviewResponse
            toUpcomingInterviewResponse(
                    UpcomingInterview interview) {

        UpcomingInterviewResponse response =
                new UpcomingInterviewResponse();

        response.setInterviewId(
                interview.getInterviewId());

        response.setInterviewDatetime(
        		
                interview.getInterviewDatetime());

        response.setEmployeeId(
                interview.getEmployeeId());

        response.setEmployeeName(
                interview.getEmployeeName());

        response.setProjectId(
                interview.getProjectId());

        response.setProjectName(
                interview.getProjectName());

        response.setInterviewTypeCode(
                interview.getInterviewTypeCode());

        response.setInterviewTypeName(
                interview.getInterviewTypeName());

        response.setSalesUserId(
                interview.getSalesUserId());

        response.setSalesUserName(
                interview.getSalesUserName());

        response.setInterviewStatusCode(
                interview.getInterviewStatusCode());

        response.setInterviewStatusName(
                interview.getInterviewStatusName());

        return response;
    }
}