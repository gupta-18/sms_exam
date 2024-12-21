package com.mkipts.bank.service.impl;

import com.google.common.hash.Hashing;
import com.mkipts.bank.dto.request.UserRequestDto;
import com.mkipts.bank.dto.response.UserResponseDto;
import com.mkipts.bank.entity.*;
import com.mkipts.bank.repository.*;
import com.mkipts.bank.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    BranchRepository branchRepository;

    Address address = new Address();

    @Override
    public List<UserResponseDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : userList) {
            UserResponseDto userResponseDto = convertUserToUserGetResponseDto(user);
            userResponseDtoList.add(userResponseDto);
        }

        return userResponseDtoList;
    }

    @Override
    public UserResponseDto getUserByIdUser(Integer id) {
        Optional<User> userModel = userRepository.findById(id);
        address = addressRepository.findAccountsByUserId(id);
        UserResponseDto userDto = new UserResponseDto();
        if (userModel.isPresent()) {
            userDto = convertUserToUserGetResponseDto(userModel.get());
        }
        return userDto;
    }

    @Override
    public UserResponseDto getdetailsBybranchid(Integer id) {

        Integer branchId = accountRepository.findBranchIdByUserId(id);

      Optional<Branch>  branchModel = branchRepository.findById(branchId);
        UserResponseDto userDto = new UserResponseDto();
        if (branchModel.isPresent()) {
            userDto = convertBranchToUserGetResponseDto(branchModel.get());
        }
        return userDto;
    }

    private UserResponseDto convertBranchToUserGetResponseDto(Branch branch) {
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .ifsc(branch.getIfscCode())
                .branchName(branch.getBranchName())
                .build();
   return userResponseDto;
    }

    private UserResponseDto convertUserToUserGetResponseDto(User user) {
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .cin(user.getCin())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .gender(user.getGender())
                .pincode(address.getPinCode())
                .address(address.getAddress())
                .adhaar(user.getAdhaar())
                .build();
        return userResponseDto;
    }


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        String cinNo = String.valueOf(LocalDateTime.now()).replaceAll("[^0-9]", "").substring(0, 17);

        User user = convertUserRequestDtoToUser(userRequestDto);
        user.setCin(cinNo);
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy(1);
        user = userRepository.save(user);

        Account account = new Account();
        account.setUserId(user.getId());
        account.setAccountType(userRequestDto.getAccountType());

        //generating account number here
        State state = stateRepository.findStateByName(userRequestDto.getState());
        District district = districtRepository.findDistrictByName(userRequestDto.getDistrict());
        City city = cityRepository.findCityByName(userRequestDto.getCity());

        //getting last account number for increasing new by one
        String lastAccountnumber = accountRepository.findLastAccountNumber();

//       String lastFourDigit =  lastAccountnumber.substring(lastAccountnumber.length()-4);
//       //start hi length-4= 10 number se hoga to last k 4 digit mil
//
//        Integer parseFourDigitToInt = Integer.valueOf(lastFourDigit)+1;
//        //parsing into integer and adding 1
//
//        String finalAccountNumber = String.format("%04d",parseFourDigitToInt);

        account.setAccNo(String.format("%02d", state.getId()) + String.format("%04d", district.getId()) + String.format("%04d", city.getId()) + String.format("%04d", Integer.valueOf(lastAccountnumber.substring(lastAccountnumber.length() - 4)) + 1));


        Integer branchId = branchRepository.findIdByCityId(String.format("%04d", city.getId()));

        account.setBranchId(branchId);
        account.setRateOfInterest(8.0);
        account.setOpeningDate(LocalDate.now());
        account.setBalance(BigDecimal.valueOf(0.0));
        account = accountRepository.save(account);


        UserCredential userCredential = new UserCredential();
        userCredential.setUserId(user.getId());
        userCredential.setUserName(userRequestDto.getUserName());

        String uuid = UUID.randomUUID().toString();
        final String computedPassword = Hashing.sha256()
                .hashString(userRequestDto.getPassword(), StandardCharsets.UTF_8).toString() + uuid;
        userCredential.setPassword(computedPassword);
        userCredential.setPasswordSalt(uuid);


//        userCredential.setPassword(userRequestDto.getPassword());
        userCredential = userCredentialRepository.save(userCredential);

        Address address = new Address();
        address.setUserId(user.getId());
        address.setAddress(userRequestDto.getAddress());
        address.setPinCode(userRequestDto.getPincode());
        address = addressRepository.save(address);


        return convertUserToUserResponseDto(user, account, address, userCredential);
    }


    private User convertUserRequestDtoToUser(UserRequestDto userRequestDto) {
        return User.builder()
                .firstName(userRequestDto.getFirstName())
                .middleName(userRequestDto.getMiddleName())
                .lastName(userRequestDto.getLastName())
                .mobile(userRequestDto.getMobile())
                .email(userRequestDto.getEmail())
                .gender(userRequestDto.getGender())
                .dob(userRequestDto.getDateOfBirth())
                .adhaar(userRequestDto.getAdhaarCard())
                .build();
    }

    private UserResponseDto convertUserToUserResponseDto(User user, Account account, Address address, UserCredential userCredential) {
        return UserResponseDto.builder()
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .mobile(user.getMobile())
                .email(user.getEmail())
                .dob(user.getDob())
                .adhaar(user.getAdhaar())
                .cin(user.getCin())
                .userName(userCredential.getUserName())
                .address(address.getAddress())
                .accountType(account.getAccountType())
                .pincode(address.getPinCode())
                .build();
    }


    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findById(userRequestDto.getId());

        if (optionalUser.isEmpty()) {
            System.out.println("User data with id: " + userRequestDto.getId() + " not found");
        } else {
            User user = optionalUser.get();
            user.setFirstName(userRequestDto.getFirstName() != null && !userRequestDto.getFirstName()
                    .equals(user.getFirstName()) ? userRequestDto.getFirstName() : user.getFirstName());

            user.setMiddleName(userRequestDto.getMiddleName() != null && !userRequestDto.getMiddleName()
                    .equals(user.getMiddleName()) ? userRequestDto.getMiddleName() : user.getMiddleName());

            user.setLastName(userRequestDto.getLastName() != null && !userRequestDto.getLastName()
                    .equals(user.getLastName()) ? userRequestDto.getLastName() : user.getLastName());

            user.setGender(userRequestDto.getGender() != null && !userRequestDto.getGender()
                    .equals(user.getGender()) ? userRequestDto.getGender() : user.getGender());

            user.setEmail(userRequestDto.getEmail() != null && !userRequestDto.getEmail()
                    .equals(user.getEmail()) ? userRequestDto.getEmail() : user.getEmail());

            user.setMobile(userRequestDto.getMobile() != null && !userRequestDto.getMobile()
                    .equals(user.getMobile()) ? userRequestDto.getMobile() : user.getMobile());

            user.setDob(userRequestDto.getDateOfBirth() != null && !userRequestDto.getDateOfBirth()
                    .equals(user.getDob()) ? userRequestDto.getDateOfBirth() : user.getDob());


            user.setUpdatedBy(2);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return convertUserToUserResponseDto2(user);
        }


        return UserResponseDto.builder().build();
    }

    private UserResponseDto convertUserToUserResponseDto2(User user) {
        return UserResponseDto.builder()
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .mobile(user.getMobile())
                .email(user.getEmail())
                .dob(user.getDob())
                .adhaar(user.getAdhaar())
                .build();
    }






 @Override
    public UserCredential findUserByUsername(String username) {
        return userCredentialRepository.findByUserName(username);
    }

    @Override
    public boolean isPasswordValid(String rawPassword, String hashedPassword, String salt) {
        String computedHash = Hashing.sha256()
                .hashString(rawPassword, StandardCharsets.UTF_8)
                .toString() + salt;
        return hashedPassword.equals(computedHash);
    }



}

