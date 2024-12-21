package com.mkipts.bank.service.impl;


import com.google.common.hash.Hashing;
import com.mkipts.bank.dto.request.EmployeeRequestDto;
import com.mkipts.bank.dto.response.EmployeeResponseDto;
import com.mkipts.bank.entity.Employee;
import com.mkipts.bank.entity.EmployeeCredential;
import com.mkipts.bank.repository.EmployeeCredentialRepository;
import com.mkipts.bank.repository.EmployeeRepository;
import com.mkipts.bank.service.IEmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;
    @Autowired
    EmployeeCredentialRepository employeeCredentialRepository;


    @Override
    public List<EmployeeResponseDto> getAllEmployees() {

        List<Employee> employeeList = (List<Employee>) employeeRepo.findAll();
        List<EmployeeResponseDto> employeeDtoArrayList = new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeResponseDto getEmployeeDto = convertEmployeeModelToEmployeeDto(employee);
            employeeDtoArrayList.add(getEmployeeDto);
        }
        return employeeDtoArrayList;
    }


    @Transactional
    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = convertEmployeeRequestDtoToEmployee(employeeRequestDto);
        employee.setCreatedBy(1);
        employee.setCreatedAt(LocalDateTime.now());
        employee = employeeRepo.save(employee);

        EmployeeCredential employeeCredential = new EmployeeCredential();
        employeeCredential.setEmpId(employee.getId());
        employeeCredential.setUsername(employeeRequestDto.getUsername());


        String uuid = UUID.randomUUID().toString();
        final String computedPassword = Hashing.sha256()
                .hashString(employeeRequestDto.getPassword(), StandardCharsets.UTF_8).toString() + uuid;
        employeeCredential.setPassword(computedPassword);
        employeeCredential.setPasswordSalt(uuid);


        employeeCredential.setCreatedAt(LocalDateTime.now());
        employeeCredential.setCreatedBy(employee.getId());
        employeeCredentialRepository.save(employeeCredential);
        return convertEmployeeModelToEmployeeDto(employee);
    }

    private Employee convertEmployeeRequestDtoToEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = Employee.builder()
                .fullName(employeeRequestDto.getFullName())
                .email(employeeRequestDto.getEmail())
                .dateofbirth(employeeRequestDto.getDateOfBirth())
                .address(employeeRequestDto.getAddress() + employeeRequestDto.getPinCode())
                .city(employeeRequestDto.getCity())
                .state(employeeRequestDto.getState())
                .gender(employeeRequestDto.getGender())
                .mobile(employeeRequestDto.getMobile())
                .aadharNo(employeeRequestDto.getAadharNo())
                .build();
        return employee;
    }

    private EmployeeResponseDto convertEmployeeModelToEmployeeDto(Employee employee) {
        EmployeeResponseDto getEmployeeDto = EmployeeResponseDto.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .gender(employee.getGender())
                .mobile(employee.getMobile())
                .aadharNo(employee.getAadharNo())
                .dateofbirth(employee.getDateofbirth())
                .state(employee.getState())
                .city(employee.getCity())
                .address(employee.getAddress())
                .build();

        return getEmployeeDto;
    }


}
