package com.mkipts.bank.entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="city_id")
    private String cityId;
    @Column(name="name")
    private String branchName;
    @Column(name="code")
    private String BranchCode;
    @Column(name="ifsc_code")
    private String ifscCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name="updated_by")
    private Integer updatedBy;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}