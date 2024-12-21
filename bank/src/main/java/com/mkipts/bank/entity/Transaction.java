package com.mkipts.bank.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;



    @Column(name = "sender_id", nullable = false)
    private Integer senderId;

    @Column(name = "sender_acc_no")
    private String senderAccountNumber;

    @Column(name = "reciever_id", nullable = false)
    private Integer receiverId;

    @Column(name = "reciever_account_number")
    private String receiverAccountNumber;

    @Column(name = "transfer_amount", nullable = false)
    private BigDecimal transferAmount;

    @Column(name = "sender_balance", nullable = false)
    private BigDecimal senderBalance;

    @Column(name = "reciever_balance", nullable = false)
    private BigDecimal receiverBalance;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



}
