package com.bankingsystem.dto;


import com.bankingsystem.enum_pack.AccountType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class AccountCreateRequestDTO {

    @NotBlank(message = "name should be not null")
    private String name;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number should not be blank")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Account type is required")
    private AccountType accountType;

    @NotNull(message = "Initial balance is required")
    @PositiveOrZero(message = "Initial balance must be zero or positive")
    private Double initialBalance;
}
