package com.bankingsystem.dto;

import com.bankingsystem.transaction.entity.Transaction;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TransactionHistoryRequestDTO {

    @NotBlank(message = "Account number is required")
    @Size(min = 10, max = 10, message = "Account number must be exactly 10 digits")
    @Pattern(regexp = "\\d+", message = "Account number must contain only digits")
    private String accountNumber;

    @NotNull(message = "Page number is required")
    @Min(value = 0, message = "Page number must be 0 or greater")
    private Integer pageNumber;

    @NotNull(message = "Size is required")
    @Min(value = 1, message = "Size must be at least 1")
    @Max(value = 100, message = "Size must not exceed 100")
    private Integer size;
}
