package com.mkipts.bank.controller;

import com.mkipts.bank.dto.request.EmployeeRequestDto;
import com.mkipts.bank.dto.request.UserRequestDto;
import com.mkipts.bank.dto.response.AccountResponseDto;
import com.mkipts.bank.dto.response.EmployeeResponseDto;
import com.mkipts.bank.dto.response.UserResponseDto;
import com.mkipts.bank.service.IAccountService;
import com.mkipts.bank.service.IEmployeeService;
import com.mkipts.bank.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    IUserService iUserService;

    @Autowired
    IAccountService iAccountService;

    @Autowired
    IEmployeeService iEmployeeService;

//    //opens dashboard
//    @GetMapping("" +
//            "")
//    public String adminDashboard() {
//        return "/admin/index";
//    }

    //open user management
    @GetMapping("/admin/dashboard/usermanagement")
    public String adminUserManagement(Model model) {

        List<UserResponseDto> userResponseDtoList = iUserService.getAllUser();
        model.addAttribute("userManagement", userResponseDtoList);
        return "admin/userManagement";
    }


    //to open registration form to create user
    @GetMapping("/admin/dashboard/usermanagement/registrationform")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRequestDto", new UserRequestDto());
        return "user/registration";
    }


    //to create user
    @PostMapping("/admin/dashboard/usermanagement/register")
    public String registerUser(@ModelAttribute UserRequestDto userRequestDto, Model model) {
        UserResponseDto userResponseDto = iUserService.createUser(userRequestDto);
        model.addAttribute("user", userResponseDto);

        List<UserResponseDto> userResponseDtoList = iUserService.getAllUser();
        model.addAttribute("userManagement", userResponseDtoList);

        return "admin/userManagement"; // The view name of the success page
    }


    // to show useraccounts
    @GetMapping("/admin/dashboard/customerdetails/{userId}")
    public String adminCustomerAccounts(@PathVariable("userId") Integer userId, Model model) {
        UserResponseDto userDto = iUserService.getUserByIdUser(userId);
        //this is for showing name etc
        model.addAttribute("customerDetalis", userDto);

        List<AccountResponseDto> accountResponseDto = iAccountService.getAllUserAccounts(userId);
        //this is for showing accounts etc
        model.addAttribute("customerDetailsFromAccount", accountResponseDto);
        return "admin/customer-accounts";
    }

//
    @GetMapping("/admin/dashboard/usermanagement/addaccountregistration/{userId}")
    public String showAddAccountRegistrationForm(@PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("userRequestDto", new UserRequestDto());
        model.addAttribute("userId", userId);
        return "/admin/addaccountregistration";
    }

    @PostMapping("/admin/usermanagement/customerdetails/addaccountregistration")
    public String addAccountRegister(@RequestParam("userId") Integer userId, @ModelAttribute UserRequestDto userRequestDto, Model model) {
        userRequestDto.setUserId(userId);
        UserRequestDto userResponseDto = iAccountService.addAccount(userRequestDto);
        model.addAttribute("account", userResponseDto);

        UserResponseDto userDto = iUserService.getUserByIdUser(userId);

        List<AccountResponseDto> accountResponseDtoList = iAccountService.getAllUserAccounts(userId);
        model.addAttribute("customerDetailsFromAccount", accountResponseDtoList);
        model.addAttribute("customerDetalis", userDto);
        return "admin/customer-accounts";
    }





    //to show details of user from database to  update form
    @GetMapping("/admin/dashboard/usermanagment/updateForm/{userId}")
    public String userUpdateRegistrationForm(@PathVariable("userId") Integer userId, Model model) {

        //to get detail of particular id of user
        UserResponseDto userResponseDto = iUserService.getUserByIdUser(userId);

        model.addAttribute("customerDetalis", userResponseDto);

        return "/admin/updateUserRegistration";
    }


    // to post updation in the details of user
    @PostMapping("/admin/dashboard/usermanagement/update/{id}")
    public String updatePartialUser(@PathVariable("id") Integer id, @ModelAttribute UserRequestDto userRequestDto, Model model) {
        userRequestDto.setId(id);
        UserResponseDto userResponseDto = iUserService.updateUser(userRequestDto);
        List<UserResponseDto> users = iUserService.getAllUser();
        model.addAttribute("userManagement", users);
        return "/admin/userManagement";
    }


    //delete user
    @GetMapping("/admin/dashboard/customerdetails/delete/{accNo}")
    public String deleteAccount(@PathVariable("accNo") String accNo, Model model) {

        Integer userId = iAccountService.getUserIdByAccountNo(accNo);

        AccountResponseDto accountResponseDto = iAccountService.deleteAccount(accNo);


        UserResponseDto userDto = iUserService.getUserByIdUser(userId);
        List<AccountResponseDto> accountResponseDtoList = iAccountService.getAllUserAccounts(userId);

        model.addAttribute("customerDetalis", userDto);
        model.addAttribute("customerDetailsFromAccount", accountResponseDtoList);
        return "admin/customer-accounts";
    }


    //open employee management
    @GetMapping("/admin/dashboard/employeemanagement")
    public String adminEmployeeManagement(Model model) {
        List<EmployeeResponseDto> employeeGetResponseDtoList = iEmployeeService.getAllEmployees();
        model.addAttribute("employeeManagement", employeeGetResponseDtoList);
        return "/admin/employeeManagement";
    }

    //to show form for registration
    @GetMapping("/admin/employee/dashboard/employeemanagement/registrationform")
    public String showEmployeeRegistrationForm(Model model) {
        model.addAttribute("employeeRequestDto", new EmployeeRequestDto());
        return "/admin/employeeRegistration";
    }


    //to post and create employee
    @PostMapping("/admin/dashboard/employeemanagement/register")
    public String addregisterEmployee(@ModelAttribute EmployeeRequestDto employeeRequestDto, Model model) {
        EmployeeResponseDto employeeResponseDto = iEmployeeService.createEmployee(employeeRequestDto);
        List<EmployeeResponseDto> employeeGetResponseDtoList = iEmployeeService.getAllEmployees();
        model.addAttribute("employeeManagement", employeeGetResponseDtoList);
        return "admin/employeeManagement";
    }


}

