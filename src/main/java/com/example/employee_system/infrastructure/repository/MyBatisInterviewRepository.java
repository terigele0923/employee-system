package com.example.employee_system.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.model.UpcomingInterview;
import com.example.employee_system.domain.repository.InterviewRepository;
import com.example.employee_system.infrastructure.mapper.InterviewMapper;

@Repository
public class MyBatisInterviewRepository
		implements InterviewRepository {

	private final InterviewMapper interviewMapper;
	
	public MyBatisInterviewRepository (
			
			InterviewMapper interviewMapper){
		
		this.interviewMapper = interviewMapper;
	}

	@Override
	public List<UpcomingInterview> findUpcomingInterviews(LocalDateTime baseDatetime, Long salesUserId) {
		// TODO 自動生成されたメソッド・スタブ
		return interviewMapper
				.findUpcomingInterviews(
						baseDatetime, 
						salesUserId);
	}
	
	
}
