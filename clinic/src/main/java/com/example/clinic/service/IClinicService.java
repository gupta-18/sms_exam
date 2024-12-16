package com.example.clinic.service;

import com.example.clinic.dto.request.ClinicRequestDto;
import com.example.clinic.dto.response.ClinicResponseDto;

import java.util.List;

public interface IClinicService {
    List<ClinicResponseDto> getAllClinics();

    void addClinic(ClinicRequestDto clinicRequestDto);

    ClinicResponseDto updateClinic(Long id, ClinicRequestDto clinicRequestDto);

    ClinicResponseDto getClinicUsingClinicId(Long id);
    void deleteClinic(Long id);
}
