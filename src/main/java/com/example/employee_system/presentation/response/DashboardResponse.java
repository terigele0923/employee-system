package com.example.employee_system.presentation.response;

public class DashboardResponse {
    
    private long waitingEmployeeCount;
    private long activeEmployeeCount;
    private long endingSoonEmployeeCount;
    private long openProjectCount;
    
    public long getWaitingEmployeeCount(){
        return waitingEmployeeCount;
    }
    
    public long getActiveEmployeeCount() {
		return activeEmployeeCount;
	}
    
    public long getEndingSoonEmployeeCount() {
		return endingSoonEmployeeCount;
	}

	public long getOpenProjectCount() {
		return openProjectCount;
	}
	

	public void setWaitingEmployeeCount(
            long waitingEmployeeCount
    ){
        this.waitingEmployeeCount = waitingEmployeeCount;
    }

	public void setActiveEmployeeCount(long activeEmployeeCount) {
		this.activeEmployeeCount = activeEmployeeCount;
	}

	public void setEndingSoonEmployeeCount(long endingSoonEmployeeCount) {
		this.endingSoonEmployeeCount = endingSoonEmployeeCount;
	}
	
	public void setOpenProjectCount(long openProjectCount) {
		this.openProjectCount = openProjectCount;
	}

}
