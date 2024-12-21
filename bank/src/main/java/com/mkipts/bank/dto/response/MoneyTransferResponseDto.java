package com.mkipts.bank.dto.response;

import com.mkipts.bank.entity.Transaction;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoneyTransferResponseDto {
    private Integer id;
    private String fullName;
    private int senderId;
    private String senderAccountNumber;
    private int receiverId;
    private String receiverAccountNumber;
    private BigDecimal transferredAmount;
    private BigDecimal senderBalance;
    private BigDecimal receiverBalance;


    private List<Transaction> transactions;
}
