package com.bankingsystem.transaction.repository;

import com.bankingsystem.account.entity.Account;
import com.bankingsystem.enums.TransactionType;
import com.bankingsystem.transaction.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAllByAccount(Account accountId, Pageable pageable);

    Page<Transaction> findAllByAccountAndType(
            Account account,
            TransactionType type,
            Pageable pageable
    );

    Page<Transaction> findAllByTimestampBetween(
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );

    @Query("""
                SELECT t FROM Transaction t
                WHERE t.timestamp BETWEEN :start AND :end
                AND (:type IS NULL OR t.type = :type)
            """)
    Page<Transaction> findAllForManager(
            LocalDateTime start,
            LocalDateTime end,
            TransactionType type,
            Pageable pageable
    );

    @Query("""
            SELECT COALESCE(SUM(t.amount), 0)
            FROM Transaction t
            WHERE t.type = 'DEPOSIT'
            AND t.timestamp BETWEEN :start AND :end
            """)
    double todayDeposits(LocalDateTime start, LocalDateTime end);

    @Query("""
            SELECT COALESCE(SUM(t.amount), 0)
            FROM Transaction t
            WHERE t.type = 'WITHDRAW'
            AND t.timestamp BETWEEN :start AND :end
            """)
    double todayWithdrawals(LocalDateTime start, LocalDateTime end);

    @Query("""
            SELECT COUNT(t)
            FROM Transaction t
            WHERE t.timestamp BETWEEN :start AND :end
            """)
    long todayTransactions(LocalDateTime start, LocalDateTime end);


}
