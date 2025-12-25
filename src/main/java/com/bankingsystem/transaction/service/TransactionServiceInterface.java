package com.bankingsystem.transaction.service;

import com.bankingsystem.dto.requestdto.TransactionHistoryRequestDTO;
import com.bankingsystem.dto.responsedto.TransactionResponseDTO;
import com.bankingsystem.enums.TransactionType;
import org.springframework.data.domain.Page;

public interface TransactionServiceInterface {
    Page<TransactionResponseDTO> getTransactionsByAccountNumber(TransactionHistoryRequestDTO transactionHistoryRequestDTO);

    Page<TransactionResponseDTO> getTransactionsByRequiredType(TransactionHistoryRequestDTO transactionHistoryRequestDTO,
                                                               TransactionType transactionType);
}
