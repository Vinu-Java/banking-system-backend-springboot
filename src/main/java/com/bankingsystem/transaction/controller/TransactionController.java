package com.bankingsystem.transaction.controller;

import com.bankingsystem.dto.requestdto.TransactionHistoryRequestDTO;
import com.bankingsystem.dto.responsedto.TransactionResponseDTO;
import com.bankingsystem.enums.TransactionType;
import com.bankingsystem.transaction.service.TransactionServiceInterface;

import jakarta.validation.Valid;
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
    private Page<TransactionResponseDTO> withdrawTransactions(@RequestBody @Valid TransactionHistoryRequestDTO transactionHistoryRequestDTO ){
        return service.getTransactionsByRequiredType(transactionHistoryRequestDTO, TransactionType.WITHDRAW);
    }

    @GetMapping("/deposit")
    private Page<TransactionResponseDTO> depositTransaction(@RequestBody @Valid TransactionHistoryRequestDTO transactionHistoryRequestDTO ){
        return service.getTransactionsByRequiredType(transactionHistoryRequestDTO, TransactionType.DEPOSIT);
    }

    @GetMapping("/transfer")
    private Page<TransactionResponseDTO> transferTransaction(
            @RequestBody TransactionHistoryRequestDTO transactionHistoryRequestDTO ){

        return service.getTransactionsByRequiredType( transactionHistoryRequestDTO, TransactionType.TRANSFER);
    }

}
