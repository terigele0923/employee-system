package com.example.employee_system.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.employee_system.domain.repository.CodeRepository;
import com.example.employee_system.infrastructure.mapper.CodeMapper;
import com.example.employee_system.presentation.response.CodeOptionResponse;

@Repository
public class MyBatisCodeRepository
        implements CodeRepository {

    private final CodeMapper codeMapper;

    public MyBatisCodeRepository(
            CodeMapper codeMapper) {

        this.codeMapper = codeMapper;
    }

    @Override
    public List<CodeOptionResponse> findByType(
            String codeType) {

        return codeMapper.findByType(
                codeType);
    }

    @Override
    public boolean exists(
            String codeType,
            String codeValue) {

        return codeMapper.countByTypeAndValue(
                codeType,
                codeValue) > 0;
    }
}
