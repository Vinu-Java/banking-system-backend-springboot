package com.bankingsystem.account.repository;

import com.bankingsystem.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByAccountId(Long accountId);


    @Query("SELECT COUNT(a) FROM Account a")
    long countTotalAccounts();

    @Query("SELECT COUNT(a) FROM Account a WHERE a.accountType = 'SAVINGS'")
    long countSavingsAccounts();

    @Query("SELECT COUNT(a) FROM Account a WHERE a.accountType = 'CURRENT'")
    long countCurrentAccounts();

    @Query("SELECT COUNT(a) FROM Account a WHERE a.status = 'ACTIVE'")
    long countActiveAccounts();

    @Query("SELECT COUNT(a) FROM Account a WHERE a.status = 'BLOCKED'")
    long countBlockedAccounts();

    @Query("SELECT COALESCE(SUM(a.balance), 0) FROM Account a WHERE a.status = 'ACTIVE'")
    double totalBalance();




}
