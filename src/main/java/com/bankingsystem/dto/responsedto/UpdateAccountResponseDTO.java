package com.bankingsystem.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountResponseDTO {

    private String accountNumber;
    private String name;
    private String email;
    private String phone;
}