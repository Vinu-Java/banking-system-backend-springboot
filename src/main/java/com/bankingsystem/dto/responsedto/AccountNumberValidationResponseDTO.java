package com.bankingsystem.dto.responsedto;

import com.bankingsystem.enums.AccountStatus;
import com.bankingsystem.enums.AccountType;
import lombok.Data;

@Data
public class AccountNumberValidationResponseDTO {

    private String accountNumber;
    private String name;
    private String email;
    private String phone;
    private AccountType type;
    private AccountStatus status;
    private Double balance;
}
