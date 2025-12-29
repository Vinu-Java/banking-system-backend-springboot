package com.bankingsystem.dto.responsedto;

import com.bankingsystem.enums.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponseDTO {

    private Long transactionId;
    private LocalDateTime transactionDate;
    private TransactionType type;
    private Double amount;
    private Double balanceAfter;
}

