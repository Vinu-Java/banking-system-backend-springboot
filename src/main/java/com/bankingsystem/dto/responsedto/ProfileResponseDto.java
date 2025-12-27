package com.bankingsystem.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto {

    private Long userId;
    private String name;
    private String email;
    private String phone;

    private String accountNumber;
    private String accountType;
    private Double balance;
    private String status;

}
