package com.bankingsystem.dto.responsedto;

import com.bankingsystem.enums.AccountStatus;
import com.bankingsystem.enums.AccountType;
import lombok.Data;

@Data
public class AccountResponseDTO {

    private String accountNumber;
    private AccountType accountType;
    private Double balance;
    private AccountStatus status;
}
