package com.mkipts.bank.service;

import com.mkipts.bank.dto.request.UserRequestDto;
import com.mkipts.bank.dto.response.UserResponseDto;
import com.mkipts.bank.entity.UserCredential;

import java.util.List;

public interface IUserService {
    public List<UserResponseDto>  getAllUser();
    public UserResponseDto getUserByIdUser(Integer id);

    UserResponseDto getdetailsBybranchid(Integer id);

    public UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(UserRequestDto userRequestDto);

    UserCredential findUserByUsername(String username);

    boolean isPasswordValid(String rawPassword, String hashedPassword, String salt);
}
