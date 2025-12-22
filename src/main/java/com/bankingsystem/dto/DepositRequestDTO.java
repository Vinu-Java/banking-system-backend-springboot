package com.bankingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DepositRequestDTO {

    @NotBlank(message = "Account number must not be blank")
    private String accountNumber;

    @NotNull(message = "Amount must not be null")
    @Positive(message = "Deposit amount must be greater than zero")
    private Double amount;
}
