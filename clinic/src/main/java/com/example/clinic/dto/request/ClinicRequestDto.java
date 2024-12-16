package com.example.clinic.dto.request;

import jakarta.persistence.Column;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClinicRequestDto {

    private Long id;

    private String clinicName;

    private String doctorName;

    private Integer clinicNumber;

    private String location;

    private Integer numberOfPatient;

    private Double revenue;

}
