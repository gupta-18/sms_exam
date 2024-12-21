package com.mkipts.bank.repository;


import com.mkipts.bank.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BranchRepository extends JpaRepository <Branch, Integer > {

    @Query("SELECT b.id FROM Branch b WHERE b.cityId = ?1" )
    Integer findIdByCityId(String cityId);


}