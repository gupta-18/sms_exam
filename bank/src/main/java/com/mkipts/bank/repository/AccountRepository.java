package com.mkipts.bank.repository;

import com.mkipts.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findAccountsByUserId(Integer userId);

    @Query(value = "SELECT a.account_number FROM Account a ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    String findLastAccountNumber();

    @Query("SELECT a.id FROM Account a WHERE a.accNo = ?1")
    Integer findIdByAccountNumber(String accNo);

    @Query("SELECT a.userId FROM Account a WHERE a.accNo = ?1")
    Integer findUserIdByAccountNumber(String accNo);

    @Query("SELECT DISTINCT a.branchId FROM Account a WHERE a.userId = ?1")
    Integer indBranchIdByUserId(Integer userId);


    //tracsfer money
    Optional<Account> findByAccNo(String accNo);


    Integer findBranchIdByUserId(Integer id);
}
