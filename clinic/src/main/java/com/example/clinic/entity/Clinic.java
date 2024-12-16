package com.example.clinic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinic")
public class Clinic {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name= "Clinic_Name")
    private String clinicName;

    @Column(name= "Doctor_Name")
    private String doctorName;

    @Column(name= "Clinic_Number")
    private Integer clinicNumber;

    @Column(name= "Location")
    private String location;

    @Column(name= "No_of_Patients")
    private Integer NumberOfPatient;

    @Column(name= "Revenue")
    private Double revenue;


}
