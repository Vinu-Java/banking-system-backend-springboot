package com.bankingsystem.transaction.service;

import com.bankingsystem.dto.requestdto.AdminTransactionHistoryRequestDTO;
import com.bankingsystem.dto.responsedto.AdminTransactionResponseDTO;
import com.bankingsystem.dto.responsedto.TransactionResponseDTO;
import com.bankingsystem.enums.TransactionType;
import org.springframework.data.domain.Page;

public interface AdminTransactionServiceInterface  {

    Page<AdminTransactionResponseDTO> findAllAccountTransaction(
            AdminTransactionHistoryRequestDTO dto);

    Page<AdminTransactionResponseDTO> getTransactionsByRequiredType(
            AdminTransactionHistoryRequestDTO dto, TransactionType transactionType);
}
