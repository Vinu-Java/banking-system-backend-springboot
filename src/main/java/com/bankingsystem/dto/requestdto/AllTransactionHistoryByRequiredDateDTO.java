package com.bankingsystem.dto.requestdto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AllTransactionHistoryByRequiredDateDTO {

    @NotBlank(message = "Account number is required")
    @Size(min = 10, max = 10, message = "Account number must be exactly 10 digits")
    @Pattern(regexp = "\\d+", message = "Account number must contain only digits")
    private String accountNumber;

    @NotNull(message = "From date is required")
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    private LocalDate toDate;

    @Min(value = 0, message = "Page number must be 0 or greater")
    private int pageNumber = 0;

    @Min(value = 1, message = "Size must be at least 1")
    @Max(value = 100, message = "Size must not exceed 100")
    private int size = 10;

}
