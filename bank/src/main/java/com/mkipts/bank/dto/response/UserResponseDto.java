package com.mkipts.bank.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserResponseDto {


    private Integer id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String gender;

    private String mobile;

    private String email;

    private String dob;

    private String cin;

    private String adhaar;
    private String address;

    private String pincode;
    private String userName;
    private String accountType;
    private  String ifsc;
    private  String branchName;
}
