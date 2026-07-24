package com.example.employee_system.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_system.domain.model.User;

@Mapper

public interface UserMapper {
	
	User findByLoginId(String loginId);

    List<User> findActiveSalesUsers();

    long countActiveSalesUserById(
            @Param("userId") Long userId);
}
