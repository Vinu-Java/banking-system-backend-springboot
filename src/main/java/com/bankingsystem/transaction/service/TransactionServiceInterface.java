package com.bankingsystem.transaction.service;

import com.bankingsystem.dto.requestdto.AllTransactionHistoryByRequiredDateDTO;
import com.bankingsystem.dto.requestdto.AllTransactionHistoryDTO;
import com.bankingsystem.dto.requestdto.TransactionHistoryByDateRequestDTO;
import com.bankingsystem.dto.requestdto.TransactionHistoryRequestDTO;
import com.bankingsystem.dto.responsedto.TransactionResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface TransactionServiceInterface 
{
    Page<TransactionResponseDTO> getByRequiredDate(@Valid TransactionHistoryByDateRequestDTO dto);

    Page<TransactionResponseDTO> getaAllTransaction(@Valid TransactionHistoryRequestDTO dto);

    Page<TransactionResponseDTO> allTransactionHistoryByRequiredDateDTO(@Valid AllTransactionHistoryByRequiredDateDTO dto);

    Page<TransactionResponseDTO> allTransactionHistoryDTO(@Valid AllTransactionHistoryDTO dto);
}
