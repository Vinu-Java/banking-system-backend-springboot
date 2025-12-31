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

    /*{
    "accountNumber": "8220410562",
    "fromDate":"2025-12-01",
    "toDate":"2025-12-29",
    "pageNumber": 0,
    "size":5,
    "transactionType":"TRANSFER"
}
*/
    // Date + Type
    @PostMapping("/search")
    public ResponseEntity<Page<TransactionResponseDTO>> searchTransactionsByDateAndType(
            @Valid @RequestBody TransactionHistoryByDateRequestDTO dto) {

        return ResponseEntity.ok(service.getByRequiredDate(dto));

    }

    /*
    {
    "accountNumber": "8220410562",
    "pageNumber": 0,
    "size":5,
    "transactionType":"TRANSFER"
}
     */
    // Type only
    @PostMapping("/search/by-type")
    public ResponseEntity<Page<TransactionResponseDTO>> searchTransactionsByType(
            @Valid @RequestBody TransactionHistoryRequestDTO dto) {

        return ResponseEntity.ok(service.getaAllTransaction(dto));

    }


    /*
{
    "accountNumber": "8220410562",
    "fromDate":"2025-12-01",
    "toDate":"2025-12-29",
    "pageNumber": 0,
    "size":5
}
 */
    // Date only
    @PostMapping("/search/by-date")
    public ResponseEntity<Page<TransactionResponseDTO>> searchTransactionsByDate(
            @Valid @RequestBody AllTransactionHistoryByRequiredDateDTO dto) {

        return ResponseEntity.ok(service.allTransactionHistoryByRequiredDateDTO(dto));

    }

    /*
    {
    "accountNumber": "8220410562",
    "pageNumber": 0,
    "size":5
}
     */
    // All
    @PostMapping("/search/all")
    public ResponseEntity<Page<TransactionResponseDTO>> getAllTransactions(
            @Valid @RequestBody AllTransactionHistoryDTO dto) {

        return ResponseEntity.ok(service.allTransactionHistoryDTO(dto));

    }

}