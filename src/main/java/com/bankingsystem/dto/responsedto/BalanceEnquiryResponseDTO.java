package com.bankingsystem.dto.responsedto;

import com.bankingsystem.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceEnquiryResponseDTO {

    private String accountNumber;
    private Double balance;
    private AccountStatus status;
}