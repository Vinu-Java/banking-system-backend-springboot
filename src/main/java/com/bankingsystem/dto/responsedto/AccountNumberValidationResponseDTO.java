package com.bankingsystem.dto.responsedto;

import lombok.Data;

@Data
public class AccountNumberValidationResponseDTO {

    private String accountNumber;
    private String name;
    private String email;
    private String phone;
}
