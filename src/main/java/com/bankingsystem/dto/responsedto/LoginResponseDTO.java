package com.bankingsystem.dto.responsedto;

import com.bankingsystem.enums.AccountStatus;
import com.bankingsystem.enums.AccountType;
import com.bankingsystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginResponseDTO {

    private Long userId;
    private String userName;
    private Role role;
    private String accountNumber;
    private String email;
    private AccountType accountType;
    private AccountStatus accountStatus;


}
