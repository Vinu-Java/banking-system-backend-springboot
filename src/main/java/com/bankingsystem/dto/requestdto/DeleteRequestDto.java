package com.bankingsystem.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class DeleteRequestDto {

    @NotBlank(message = "Account number must not be blank")
    private String accountNumber;
}
