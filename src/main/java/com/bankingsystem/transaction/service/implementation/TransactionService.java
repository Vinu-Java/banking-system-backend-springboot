package com.bankingsystem.transaction.service.implementation;

import com.bankingsystem.account.entity.Account;
import com.bankingsystem.account.repository.AccountRepository;
import com.bankingsystem.dto.requestdto.AllTransactionHistoryByRequiredDateDTO;
import com.bankingsystem.dto.requestdto.AllTransactionHistoryDTO;
import com.bankingsystem.dto.requestdto.TransactionHistoryByDateRequestDTO;
import com.bankingsystem.dto.requestdto.TransactionHistoryRequestDTO;
import com.bankingsystem.dto.responsedto.TransactionResponseDTO;
import com.bankingsystem.exception.AccountNotFoundException;
import com.bankingsystem.transaction.entity.Transaction;
import com.bankingsystem.transaction.repository.TransactionRepository;
import com.bankingsystem.transaction.service.TransactionServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService implements TransactionServiceInterface {

   private final TransactionRepository repository;
   private final AccountRepository accountRepository;

    @Override
    public Page<TransactionResponseDTO> getByRequiredDate(TransactionHistoryByDateRequestDTO dto) {

        Account account = accountRepository.findByAccountNumber(dto
                .getAccountNumber()).orElseThrow(() -> new AccountNotFoundException("Account not found!"));

        Pageable pageable = PageRequest.of(dto.getPageNumber(),
                dto.getSize(), Sort.by("timestamp").descending());

        Page<Transaction> transactionPage = repository.findTransactionsByDateRange(
                account,
                dto.getTransactionType(),
                dto.getFromDate().atStartOfDay(),
                dto.getToDate().atTime(23, 59, 59),
                pageable
        );

        return transactionPage.map(this::mapToDTO);

    }

    @Override
    public Page<TransactionResponseDTO> getaAllTransaction(TransactionHistoryRequestDTO dto) {

        Account account = accountRepository.findByAccountNumber(dto
                .getAccountNumber()).orElseThrow(() -> new AccountNotFoundException("Account not found!"));

        Pageable pageable = PageRequest.of(dto.getPageNumber(),
                dto.getSize(), Sort.by("timestamp").descending());

        Page<Transaction> transactionPage = repository.findAllByAccountAndType(
                account,
                dto.getTransactionType(),
                pageable
        );

        return transactionPage.map(this::mapToDTO);

    }

    @Override
    public Page<TransactionResponseDTO> allTransactionHistoryByRequiredDateDTO(AllTransactionHistoryByRequiredDateDTO dto) {


        Account account = accountRepository.findByAccountNumber(dto
                .getAccountNumber()).orElseThrow(() -> new AccountNotFoundException("Account not found!"));

        Pageable pageable = PageRequest.of(dto.getPageNumber(),
                dto.getSize(), Sort.by("timestamp").descending());

        Page<Transaction> transactionPage = repository.findAllTransactionsByDateRange(
                account,
                dto.getFromDate().atStartOfDay(),
                dto.getToDate().atTime(23, 59, 59),
                pageable
        );

        return transactionPage.map(this::mapToDTO);

    }

    @Override
    public Page<TransactionResponseDTO> allTransactionHistoryDTO(AllTransactionHistoryDTO dto) {
        Account account = accountRepository.findByAccountNumber(dto
                .getAccountNumber()).orElseThrow(() -> new AccountNotFoundException("Account not found!"));

        Pageable pageable = PageRequest.of(dto.getPageNumber(),
                dto.getSize(), Sort.by("timestamp").descending());

        Page<Transaction> transactionPage = repository.findAllByAccount(
                account,
                pageable
        );

        return transactionPage.map(this::mapToDTO);
    }


    private TransactionResponseDTO mapToDTO(Transaction transaction) {
        TransactionResponseDTO dto = new TransactionResponseDTO();

        dto.setTransactionId(transaction.getTransactionId());
        dto.setAmount(transaction.getAmount());
        dto.setBalanceAfter(transaction.getBalanceAfter());
        dto.setTransactionDate(transaction.getTimestamp());
        dto.setType(transaction.getType());

        return dto;
    }


}
