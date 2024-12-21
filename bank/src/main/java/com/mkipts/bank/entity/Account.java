package com.mkipts.bank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "account_number")
    private String accNo;
    @Column(name = "rate_of_interest")
    private Double rateOfInterest;
    @Column(name = "branch_id")
    private Integer branchId;
    @Column(name = "opening_date")
    private LocalDate openingDate;
    @Column(name = "closing_date")
    private LocalDate closeingDate;
    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

//    public void setUser(User user) {
//    }
}
