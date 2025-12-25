package com.bankingsystem.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawResponseDTO {
    private String accountNumber;
    private Double withdrawnAmount;
    private Double currentBalance;
}
