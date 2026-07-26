package com.example.employee_system.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_system.presentation.response.CodeOptionResponse;

@Mapper
public interface CodeMapper {

    List<CodeOptionResponse> findByType(
            @Param("codeType") String codeType);

    long countByTypeAndValue(
            @Param("codeType") String codeType,
            @Param("codeValue") String codeValue);
}
