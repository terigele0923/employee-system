package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.domain.model.User;

public interface UserRepository {

    List<User> findActiveSalesUsers();

    boolean existsActiveSalesUserById(Long userId);
}
