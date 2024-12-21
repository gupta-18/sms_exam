package com.mkipts.bank.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private  Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobile;
    private String email;
    private String gender;
    private String dateOfBirth;
    private String adhaarCard;
    private String address;
    private String pincode;
    private String userName;
    private String accountType;
    private String password;
    private String passwordSalt;
    private String city;
    private String district;
    private String state;

    private String branchName;
    private String BranchCode;
    private String ifscCode;
    private Integer userId;
    private BigDecimal balance;

}
