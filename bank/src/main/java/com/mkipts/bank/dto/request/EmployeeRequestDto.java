package com.mkipts.bank.dto.request;

import lombok.*;

import java.time.LocalDate;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class EmployeeRequestDto {
        private Integer id;
        private String fullName;
        private String mobile;
        private String email;
        private String gender;
        private LocalDate dateOfBirth;
        private String aadharNo;
        private String address;
        private String state;
        private String city;
        private String username;
        private Integer pinCode;
        private String password;
    }

