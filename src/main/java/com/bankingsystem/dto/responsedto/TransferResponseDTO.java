package com.bankingsystem.dto.responsedto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponseDTO {
    private String fromAccountNumber;
    private String toAccountNumber;
    private Double transferredAmount;
    private Double fromAccountBalance;

}