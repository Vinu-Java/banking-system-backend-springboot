package com.bankingsystem.transaction.controller;

import com.bankingsystem.dto.requestdto.AllTransactionHistoryByRequiredDateDTO;
import com.bankingsystem.dto.requestdto.AllTransactionHistoryDTO;
import com.bankingsystem.dto.requestdto.TransactionHistoryByDateRequestDTO;
import com.bankingsystem.dto.requestdto.TransactionHistoryRequestDTO;
import com.bankingsystem.dto.responsedto.TransactionResponseDTO;
import com.bankingsystem.transaction.service.TransactionServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionServiceInterface service;

    @PostMapping("/search/by-type")
    public ResponseEntity<Page<TransactionResponseDTO>> searchTransactionsByType(
            @Valid @RequestBody TransactionHistoryRequestDTO dto) {

        return ResponseEntity.ok(service.getaAllTransaction(dto));

    }

    @PostMapping("/search/all")
    public ResponseEntity<Page<TransactionResponseDTO>> getAllTransactions(
            @Valid @RequestBody AllTransactionHistoryDTO dto) {

        return ResponseEntity.ok(service.allTransactionHistoryDTO(dto));

    }

}