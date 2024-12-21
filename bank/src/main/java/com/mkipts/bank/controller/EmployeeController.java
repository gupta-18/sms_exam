package com.mkipts.bank.controller;

import com.mkipts.bank.dto.request.UserRequestDto;
import com.mkipts.bank.dto.response.AccountResponseDto;
import com.mkipts.bank.dto.response.UserResponseDto;
import com.mkipts.bank.service.IAccountService;
import com.mkipts.bank.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {


    @Autowired
    IUserService iUserService;

    @Autowired
    IAccountService iAccountService;

    @GetMapping("/employee/dashboard")
    public  String employeeDashboard(){

        return "/employee/index";
    }

    @GetMapping("/employee/dashboard/usermanagement")
    public  String adminUserManagement(Model model){

        List<UserResponseDto> userResponseDtoList =  iUserService.getAllUser();
        model.addAttribute("userManagement", userResponseDtoList);
        return "employee/userManagement";
    }

    @PostMapping("/employee/dashboard/employeemanagement/register")
    public String registerUser(@ModelAttribute UserRequestDto userRequestDto, Model model) {
        UserResponseDto userResponseDto = iUserService.createUser(userRequestDto);
        model.addAttribute("user", userResponseDto);

        List<UserResponseDto> userResponseDtoList =  iUserService.getAllUser();
        model.addAttribute("userManagement", userResponseDtoList);

        return "employee/userManagement";
    }

    @GetMapping("/employee/dashboard/customerdetails/{userId}")
    public String adminCustomerAccounts(@PathVariable("userId") Integer userId, Model model) {
        UserResponseDto userDto = iUserService.getUserByIdUser(userId);
        List  <AccountResponseDto> accountResponceDto=iAccountService.getAllUserAccounts(userId);
        model.addAttribute("customerDetalis",userDto);
        model.addAttribute("customerDetailsFromAccount",accountResponceDto);
        return "employee/customer-accounts";
    }

    @GetMapping("/employee/dashboard/employeemanagement/registrationform")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRequestDto", new UserRequestDto());
        return "employee/registration";
    }

}
