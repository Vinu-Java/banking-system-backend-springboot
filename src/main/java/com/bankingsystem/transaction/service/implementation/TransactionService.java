package com.bankingsystem.transaction.service.implementation;

import com.bankingsystem.account.entity.Account;
import com.bankingsystem.account.repository.AccountRepository;
import com.bankingsystem.dto.TransactionHistoryRequestDTO;
import com.bankingsystem.dto.TransactionResponseDTO;
import com.bankingsystem.enum_pack.TransactionType;
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

    TransactionRepository transactionRepository;
    AccountRepository accountRepository;

    @Override
    public Page<TransactionResponseDTO> getTransactionsByAccountNumber(
            TransactionHistoryRequestDTO transactionHistoryRequestDTO) {

        Account account = accountRepository.findByAccountNumber(transactionHistoryRequestDTO
                .getAccountNumber()).orElseThrow(()-> new AccountNotFoundException("Account not found!"));

        Pageable pageable = PageRequest.of(transactionHistoryRequestDTO.getPageNumber(),
                transactionHistoryRequestDTO.getSize(), Sort.by("timestamp").descending());

        Page<Transaction> transactionPage = transactionRepository.findAllByAccount(account, pageable);

        return transactionPage.map(this::mapToDTO);

    }

    @Override
    public Page<TransactionResponseDTO> getTransactionsByRequiredType(TransactionHistoryRequestDTO transactionHistoryRequestDTO,
                                                                      TransactionType transactionType) {

        Account account = accountRepository.findByAccountNumber(transactionHistoryRequestDTO
                .getAccountNumber()).orElseThrow(()-> new AccountNotFoundException("Account not found!"));

        Pageable pageable = PageRequest.of(transactionHistoryRequestDTO.getPageNumber(),
                transactionHistoryRequestDTO.getSize(), Sort.by("timestamp").descending());

        Page<Transaction> transactionPage = transactionRepository
                .findAllByAccountAndType(account,transactionType, pageable);

        return transactionPage.map(this::mapToDTO);
    }

    private TransactionResponseDTO mapToDTO(Transaction transaction) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setAmount(transaction.getAmount());
        dto.setBalanceAfter(transaction.getBalanceAfter());
        dto.setTimestamp(transaction.getTimestamp());
        dto.setType(transaction.getType());
        return dto;
    }

}
