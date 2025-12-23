package com.bankingsystem.transaction.controller;

import com.bankingsystem.dto.TransactionHistoryRequestDTO;
import com.bankingsystem.dto.TransactionResponseDTO;
import com.bankingsystem.enum_pack.TransactionType;
import com.bankingsystem.transaction.service.TransactionServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    TransactionServiceInterface service;

    @GetMapping("/all")
    private Page<TransactionResponseDTO> getTransactionsByAccountNumber(@RequestBody TransactionHistoryRequestDTO transactionHistoryRequestDTO ) {
        return service.getTransactionsByAccountNumber(transactionHistoryRequestDTO);
    }

    @GetMapping("/withdraw")
    private Page<TransactionResponseDTO> withdrawTransactions(@RequestBody TransactionHistoryRequestDTO transactionHistoryRequestDTO ){
        return service.getTransactionsByRequiredType(transactionHistoryRequestDTO, TransactionType.WITHDRAW);
    }

    @GetMapping("/deposit")
    private Page<TransactionResponseDTO> depositTransaction(@RequestBody TransactionHistoryRequestDTO transactionHistoryRequestDTO ){
        return service.getTransactionsByRequiredType(transactionHistoryRequestDTO, TransactionType.DEPOSIT);
    }

    @GetMapping("/transfer")
    private Page<TransactionResponseDTO> transferTransaction(
            @RequestBody TransactionHistoryRequestDTO transactionHistoryRequestDTO ){
        return service.getTransactionsByRequiredType( transactionHistoryRequestDTO, TransactionType.TRANSFER);
    }
}
