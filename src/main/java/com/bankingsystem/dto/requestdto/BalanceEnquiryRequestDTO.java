package com.bankingsystem.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
<<<<<<<< HEAD:src/main/java/com/bankingsystem/dto/requestdto/AccountCredentialsDTO.java
public class AccountCredentialsDTO {
========
public class BalanceEnquiryRequestDTO {
>>>>>>>> c9da899 (Refactor DTO structure and implement secure login):src/main/java/com/bankingsystem/dto/requestdto/BalanceEnquiryRequestDTO.java

    @NotBlank(message = "Account number is required")
    @Size(min = 10, max = 10, message = "Account number must be exactly 10 digits")
    @Pattern(regexp = "\\d+", message = "Account number must contain only digits")
    private String accountNumber;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;



}
