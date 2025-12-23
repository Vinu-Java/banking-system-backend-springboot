package com.bankingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AccountNumberAndPasswordDTO {

    @NotBlank(message = "Account number is required")
    @Size(min = 10, max = 10, message = "Account number must be exactly 10 digits")
    @Pattern(regexp = "\\d+", message = "Account number must contain only digits")
    private String accountNumber;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;



}
