package com.mkipts.bank.repository;

import com.mkipts.bank.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {

        UserCredential findByUserName(String userName);


}
