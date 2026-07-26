package com.example.employee_system.domain.repository;

import java.util.List;

import com.example.employee_system.presentation.response.CodeOptionResponse;

public interface CodeRepository {

    List<CodeOptionResponse> findByType(
            String codeType);

    boolean exists(
            String codeType,
            String codeValue);
}
