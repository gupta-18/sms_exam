package com.mkipts.bank.repository;

import com.mkipts.bank.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    public Address findAddressByUserId(Integer userId);

    Address findAccountsByUserId(Integer id);
}
