package com.bankingsystem.transaction.repository;

import com.bankingsystem.account.entity.Account;
import com.bankingsystem.dto.TransactionResponseDTO;
import com.bankingsystem.enum_pack.TransactionType;
import com.bankingsystem.transaction.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {


    Page<Transaction> findAllByAccount(Account accountId,
                                       Pageable pageable);


    Page<Transaction> findAllByAccountAndType(
            Account account,
            TransactionType type,
            Pageable pageable
    );



}
