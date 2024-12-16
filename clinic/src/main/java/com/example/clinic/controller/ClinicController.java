package com.example.clinic.controller;

import com.example.clinic.dto.request.ClinicRequestDto;
import com.example.clinic.dto.response.ClinicResponseDto;
import com.example.clinic.repository.ClinicRepository;
import com.example.clinic.service.IClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/clinics")
public class ClinicController {
    @Autowired
    private IClinicService iClinicService;

    @Autowired
    private ClinicRepository clinicRepository;

    @GetMapping
    public String getAllEmployees(Model model) {
        model.addAttribute("clinics", iClinicService.getAllClinics());
        return "index";
    }


    // Show the add employee form
    @GetMapping("/add")
    public String showAddClinicForm(Model model) {
        model.addAttribute("clinic", new ClinicResponseDto());
        return "addform";
    }


    // Handle the form submission for adding a new employee
    @PostMapping
    public String addClinic(@ModelAttribute("clinic") ClinicRequestDto clinicRequestDto) {
        iClinicService.addClinic( clinicRequestDto);
        return "redirect:/clinics";
    }


    // Show update clinic form
    @GetMapping("/edit/{id}")
    public String showUpdateClinicForm(@PathVariable Long id, Model model) {
        ClinicResponseDto clinicResponseDto =  iClinicService.getClinicUsingClinicId(id);
        model.addAttribute("clinic", clinicResponseDto);
        return "updateform";
    }

    // Handle update clinic
    @PostMapping("/update")
    public String updateClinic( @ModelAttribute("clinic") ClinicRequestDto clinicRequestDto) {
        iClinicService.updateClinic(  clinicRequestDto.getId(), clinicRequestDto);
        return "redirect:/clinics";
    }

    // Delete clinic
    @GetMapping("/delete/{id}")
    public String deleteClinic(@PathVariable Long id) {
        iClinicService.deleteClinic(id);
        return "redirect:/clinics";
    }






}
