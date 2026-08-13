package com.example.employee_system.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee_system.domain.repository.CodeRepository;
import com.example.employee_system.presentation.response.CodeOptionResponse;

@Service
public class CodeService {

    public static final String EMPLOYMENT_STATUS =
            "EMPLOYMENT_STATUS";

    public static final String WORK_STATUS =
            "WORK_STATUS";
    
    public static final String  ASSIGNMENT_STATUS =
			"ASSIGNMENT_STATUS";

    private final CodeRepository codeRepository;

    public CodeService(
            CodeRepository codeRepository) {

        this.codeRepository = codeRepository;
    }

    public List<CodeOptionResponse> findByType(
            String codeType) {

        return codeRepository.findByType(
                codeType);
    }

    public boolean exists(
            String codeType,
            String codeValue) {

        return codeRepository.exists(
                codeType,
                codeValue);
    }
}
