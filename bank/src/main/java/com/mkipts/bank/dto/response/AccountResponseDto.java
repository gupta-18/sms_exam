package com.mkipts.bank.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {
    private Integer id;
    private Integer userId;
    private String accType;
    private BigDecimal balance;
    private String accNo;
    private Double rateOfInterest;
    private Integer branchId;
    private LocalDate openingDate;
    private LocalDate closingDate;
}
