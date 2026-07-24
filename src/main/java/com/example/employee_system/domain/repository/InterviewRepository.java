package com.example.employee_system.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.example.employee_system.domain.model.UpcomingInterview;

public interface InterviewRepository {
	
	List<UpcomingInterview> findUpcomingInterviews(
			
			LocalDateTime baseDatetime,
			Long salesUserId);


}
