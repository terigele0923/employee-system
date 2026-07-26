package com.example.employee_system.presentation.response;

public class DashboardResponse {
    
    private long waitingEmployeeCount;
    
    public long getWaitingEmployeeCount(){
        return waitingEmployeeCount;
    }

    public void setWaitingEmployeeCount(
            long waitingEmployeeCount
    ){
        this.waitingEmployeeCount = waitingEmployeeCount;
    }
}
