package com.bankingsystem.dto.requestdto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountNumberValidationRequestDTO {
    @NotBlank(message = "Account number must not be blank")
    private String accountNumber;
}
