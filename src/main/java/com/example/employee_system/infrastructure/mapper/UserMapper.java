package com.example.employee_system.infrastructure.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.employee_system.domain.model.User;

@Mapper
public interface  UserMapper {
	
	User findByLoginId(String loginId);
	
}
