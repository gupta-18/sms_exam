package com.mkipts.bank.repository;

import com.mkipts.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository  extends JpaRepository<User,Integer> {

}
