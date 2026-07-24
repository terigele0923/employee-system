package com.example.employee_system.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.model.User;
import com.example.employee_system.domain.repository.UserRepository;
import com.example.employee_system.infrastructure.mapper.UserMapper;

@Repository
public class MyBatisUserRepository implements UserRepository {

    private final UserMapper userMapper;

    public MyBatisUserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findActiveSalesUsers() {
        return userMapper.findActiveSalesUsers();
    }

    @Override
    public boolean existsActiveSalesUserById(Long userId) {
        return userMapper.countActiveSalesUserById(userId) > 0;
    }
}
