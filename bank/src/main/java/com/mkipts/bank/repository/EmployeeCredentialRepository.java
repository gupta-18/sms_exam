package com.mkipts.bank.repository;

import com.mkipts.bank.entity.EmployeeCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCredentialRepository extends JpaRepository<EmployeeCredential, Integer> {
}
