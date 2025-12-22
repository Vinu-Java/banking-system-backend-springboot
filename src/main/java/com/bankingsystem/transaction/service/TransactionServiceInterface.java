package com.bankingsystem.transaction.service;

import com.bankingsystem.dto.TransactionHistoryRequestDTO;
import com.bankingsystem.dto.TransactionResponseDTO;
import com.bankingsystem.enum_pack.TransactionType;
import org.springframework.data.domain.Page;

public interface TransactionServiceInterface {
    Page<TransactionResponseDTO> getTransactionsByAccountNumber(TransactionHistoryRequestDTO transactionHistoryRequestDTO);

    Page<TransactionResponseDTO> getTransactionsByRequiredType(TransactionHistoryRequestDTO transactionHistoryRequestDTO,
                                                               TransactionType transactionType);
}
