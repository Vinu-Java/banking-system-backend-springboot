package com.bankingsystem.dto;

import com.bankingsystem.enum_pack.AccountStatus;
import com.bankingsystem.enum_pack.AccountType;
import lombok.Data;

@Data
public class AccountResponseDTO {

    private String accountNumber;
    private AccountType accountType;
    private Double balance;
    private AccountStatus status;
}
