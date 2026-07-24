package com.example.employee_system.presentation.response;

import java.util.ArrayList;
import java.util.List;

public class DashboardResponse {
	
	private long waitingCount;
	private long activeCount;
	private long endingSoonCount;
	private long openProjectCount;
	
	private List<ContractEndingEmployeeResponse>
			contractEndingEmployees = new ArrayList<>();
	
	private List<UpcomingInterviewResponse>
			upcomingInterviews = new ArrayList<>();

	public long getWaitingCount() {
		return waitingCount;
	}

	public void setWaitingCount(long waitingCount) {
		this.waitingCount = waitingCount;
	}

	public long getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(long activeCount) {
		this.activeCount = activeCount;
	}

	public long getEndingSoonCount() {
		return endingSoonCount;
	}

	public void setEndingSoonCount(long endingSoonCount) {
		this.endingSoonCount = endingSoonCount;
	}

	public long getOpenProjectCount() {
		return openProjectCount;
	}

	public void setOpenProjectCount(long openProjectCount) {
		this.openProjectCount = openProjectCount;
	}

	public List<ContractEndingEmployeeResponse> getContractEndingEmployees() {
		return contractEndingEmployees;
	}

	public void setContractEndingEmployees(List<ContractEndingEmployeeResponse> contractEndingEmployees) {
		this.contractEndingEmployees = contractEndingEmployees;
	}

	public List<UpcomingInterviewResponse> getUpcomingInterviews() {
		return upcomingInterviews;
	}

	public void setUpcomingInterviews(List<UpcomingInterviewResponse> upcomingInterviews) {
		this.upcomingInterviews = upcomingInterviews;
	}
	
	

}
