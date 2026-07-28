package com.example.employee_system.presentation.response;

public class DashboardResponse {
    
    private long waitingEmployeeCount;
    private long activeEmployeeCount;
    private long endingSoonEmployeeCount;
    
    public long getEndingSoonEmployeeCount() {
		return endingSoonEmployeeCount;
	}

	public void setEndingSoonCount(long endingSoonEmployeeCount) {
		this.endingSoonEmployeeCount = endingSoonEmployeeCount;
	}

	public long getWaitingEmployeeCount(){
        return waitingEmployeeCount;
    }

    public void setWaitingEmployeeCount(
            long waitingEmployeeCount
    ){
        this.waitingEmployeeCount = waitingEmployeeCount;
    }

	public long getActiveEmployeeCount() {
		return activeEmployeeCount;
	}

	public void setActiveEmployeeCount(long activeEmployeeCount) {
		this.activeEmployeeCount = activeEmployeeCount;
	}
}
