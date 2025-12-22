package com.bankingsystem.dto;

import com.bankingsystem.enum_pack.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponseDTO {


    private LocalDateTime timestamp;
    private TransactionType type; // DEPOSIT / WITHDRAW / TRANSFER
    private Double amount;
    private Double balanceAfter;
}
