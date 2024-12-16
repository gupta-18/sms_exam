package com.example.clinic.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClinicResponseDto {

    private Long id;

    private String clinicName;

    private String doctorName;

    private Integer clinicNumber; // Changed to Integer

    private String location;

    private Integer numberOfPatient; // Fixed naming convention

    private Double revenue;
}
