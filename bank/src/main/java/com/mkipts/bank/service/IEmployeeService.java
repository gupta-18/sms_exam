package com.mkipts.bank.service;

import com.mkipts.bank.dto.request.EmployeeRequestDto;
import com.mkipts.bank.dto.response.EmployeeResponseDto;

import java.util.List;

public interface IEmployeeService {
    public List<EmployeeResponseDto> getAllEmployees();
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
}
