package com.bankingsystem.transaction.controller;

import com.bankingsystem.dto.requestdto.TransactionHistoryRequestDTO;
import com.bankingsystem.dto.responsedto.TransactionResponseDTO;
import com.bankingsystem.enums.TransactionType;
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

    TransactionServiceInterface service;


    @GetMapping("/all")
    private ResponseEntity<Page<TransactionResponseDTO>> getTransactionsByAccountNumber(@RequestBody TransactionHistoryRequestDTO transactionHistoryRequestDTO ) {

        return  ResponseEntity.ok( service.getTransactionsByAccountNumber(transactionHistoryRequestDTO));
    }

    @GetMapping("/withdraw")
    private ResponseEntity<Page<TransactionResponseDTO>> withdrawTransactions(@RequestBody @Valid TransactionHistoryRequestDTO transactionHistoryRequestDTO ){
        return ResponseEntity.ok(service.getTransactionsByRequiredType(transactionHistoryRequestDTO, TransactionType.WITHDRAW));
    }

    @GetMapping("/deposit")
    private ResponseEntity<Page<TransactionResponseDTO>> depositTransaction(@RequestBody @Valid TransactionHistoryRequestDTO transactionHistoryRequestDTO ){
        return ResponseEntity.ok(service.getTransactionsByRequiredType(transactionHistoryRequestDTO, TransactionType.DEPOSIT));
    }

    @GetMapping("/transfer")
    private ResponseEntity<Page<TransactionResponseDTO>> transferTransaction(
            @RequestBody TransactionHistoryRequestDTO transactionHistoryRequestDTO ){

        return ResponseEntity.ok(service.getTransactionsByRequiredType( transactionHistoryRequestDTO, TransactionType.TRANSFER));
    }

}
