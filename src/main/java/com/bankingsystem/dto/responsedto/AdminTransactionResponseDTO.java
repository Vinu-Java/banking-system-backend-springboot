package com.bankingsystem.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTransactionResponseDTO {

    private Long transactionId;

    private String transactionType;

    private Double amount;

    private Double balanceAfter;

    private LocalDateTime timestamp;

    private String accountNumber;

    private String accountType;

    private Long userId;

    private String userName;

    private String userEmail;
}
