package com.bankingsystem.transaction.controller;

import com.bankingsystem.dto.requestdto.AdminTransactionHistoryRequestDTO;
import com.bankingsystem.dto.responsedto.AdminTransactionResponseDTO;
import com.bankingsystem.enums.TransactionType;
import com.bankingsystem.transaction.service.AdminTransactionServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminTransactionController {

    AdminTransactionServiceInterface service;


    /* for get all transaction by required Transaction type
     {
        "fromDate": "2025-12-25",
            "toDate": "2025-12-25",
            "pageNumber": 0,
            "size": 20,
            "transactionType": "DEPOSIT"
    }
     */

    @GetMapping("/all")
    public ResponseEntity<Page<AdminTransactionResponseDTO>> getAllTransactionsForManager(
            @Valid @RequestBody AdminTransactionHistoryRequestDTO dto) {

        return ResponseEntity.ok(service.findAllAccountTransaction(dto));
    }


    /* for withdraw, deposit, transfer

    {
  "fromDate": "2025-12-01",
  "toDate": "2025-12-25",
  "pageNumber": 0,
  "size": 20

    }
     */

    @GetMapping("/withdraw")
    public ResponseEntity<Page<AdminTransactionResponseDTO>> getTransactionsByWithdraw(
            @Valid @RequestBody AdminTransactionHistoryRequestDTO dto) {

        return ResponseEntity.ok(
                service.getTransactionsByRequiredType(dto, TransactionType.WITHDRAW));

    }

    @GetMapping("/deposit")
    public ResponseEntity<Page<AdminTransactionResponseDTO>> getTransactionsByDeposit(
            @Valid @RequestBody AdminTransactionHistoryRequestDTO dto) {

        return ResponseEntity.ok(
                service.getTransactionsByRequiredType(dto, TransactionType.DEPOSIT));

    }

    @GetMapping("/transfer")
    public ResponseEntity<Page<AdminTransactionResponseDTO>> getTransactionsByTransfer(
            @Valid @RequestBody AdminTransactionHistoryRequestDTO dto) {

        return ResponseEntity.ok(
                service.getTransactionsByRequiredType(dto, TransactionType.TRANSFER));

    }


}
