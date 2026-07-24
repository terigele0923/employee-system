package com.example.employee_system.infrastructure.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_system.domain.model.UpcomingInterview;

@Mapper
public interface InterviewMapper {
	
	List<UpcomingInterview> findUpcomingInterviews(
			@Param("baseDatetime")
			LocalDateTime baseDatetime,
			
			@Param("salesUserId")
			Long salesUserId);
	

}
