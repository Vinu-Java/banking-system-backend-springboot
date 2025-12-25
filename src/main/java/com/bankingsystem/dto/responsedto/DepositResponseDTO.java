package com.bankingsystem.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositResponseDTO {
    private String accountNumber;
    private Double depositedAmount;
    private Double currentBalance;
}
