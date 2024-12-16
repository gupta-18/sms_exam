package com.example.clinic.service.impl;

import com.example.clinic.dto.request.ClinicRequestDto;
import com.example.clinic.dto.response.ClinicResponseDto;
import com.example.clinic.entity.Clinic;
import com.example.clinic.repository.ClinicRepository;
import com.example.clinic.service.IClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicService implements IClinicService {

    @Autowired
    private ClinicRepository clinicRepository;
    @Override
    public List<ClinicResponseDto> getAllClinics() {
        List<Clinic> clinicList = clinicRepository.findAll();
        List<ClinicResponseDto> clinicResponseDtoList = new ArrayList<>();
        for (Clinic clinic : clinicList) {
            ClinicResponseDto clinicResponseDto = convertClinicToClinicResponseDto(clinic);
            clinicResponseDtoList.add(clinicResponseDto);
        }
        return clinicResponseDtoList;
    }

    private ClinicResponseDto convertClinicToClinicResponseDto(Clinic clinic) {
        return new ClinicResponseDto(
                clinic.getId(),
                clinic.getClinicName(),
                clinic.getDoctorName(),
                clinic.getClinicNumber(),
                clinic.getLocation(),
                clinic.getNumberOfPatient(),
                clinic.getRevenue());
    }

    private Clinic convertClinicResponseDtoToClinic(ClinicRequestDto clinicRequestDto ) {
        Clinic clinic = new Clinic();
        clinic.setId(clinicRequestDto.getId());
        clinic.setClinicName(clinicRequestDto.getClinicName());
        clinic.setDoctorName(clinicRequestDto.getDoctorName());
        clinic.setClinicNumber(clinicRequestDto.getClinicNumber());
        clinic.setLocation(clinicRequestDto.getLocation());
        clinic.setNumberOfPatient(clinicRequestDto.getNumberOfPatient());
        clinic.setRevenue(clinicRequestDto.getRevenue());
        return clinic;
    }

    @Override
    public void addClinic(ClinicRequestDto clinicRequestDto) {

        Clinic clinic = convertClinicResponseDtoToClinic(clinicRequestDto);

        clinicRepository.save(clinic);
    }


@Override
public ClinicResponseDto updateClinic(Long id, ClinicRequestDto clinicRequestDto) {
    Clinic clinic = clinicRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Clinic not found"));

    clinic.setClinicName(clinicRequestDto.getClinicName());
    clinic.setDoctorName(clinicRequestDto.getDoctorName());
    clinic.setClinicNumber(clinicRequestDto.getClinicNumber());
    clinic.setLocation(clinicRequestDto.getLocation());
    clinic.setNumberOfPatient(clinicRequestDto.getNumberOfPatient());
    clinic.setRevenue(clinicRequestDto.getRevenue());

    Clinic updatedClinic = clinicRepository.save(clinic);

    return convertClinicToClinicResponseDto(updatedClinic);
}


    @Override
    public ClinicResponseDto getClinicUsingClinicId(Long id) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(() -> new RuntimeException("clinics not found"));
        return convertClinicToClinicResponseDto(clinic);
    }

    // Delete a department
    @Override
    public void deleteClinic(Long id) {
        clinicRepository.deleteById(id);
    }

}
